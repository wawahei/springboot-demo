package com.wawahei.demo.interceptor;

import com.wawahei.demo.model.DigestAuthInfo;
import com.wawahei.demo.util.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-18 13:35
 **/
public class RequireAuthInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequireAuthInterceptor.class);

    // 为了 测试Digest nc 值每次请求增加
    private int nc = 0;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            Object object = handlerMethod.getMethodAnnotation(RequireAuth.class);
            /* 方法没有 @RequireAuth 注解， 放行 */
            if(object == null){
                return true;
            }

            /* 方法有 @RequireAuth 注解，需要拦截校验 */
            // 没有 Authorization 请求头，或者 Authorization 认证信息验证不通过，拦截
            if(!isAuth(request,response)){
                // 验证不通过，拦截
                return false;
            }

            // 验证通过，放行
            return true;
        }

        // 请求目标不是 mehod of controller, 放行
        return true;
    }

    private boolean isAuth(HttpServletRequest request, HttpServletResponse response) {
        String authStr = request.getHeader("Authorization");
        LOGGER.info("请求Authorization 的内容："+authStr);

        if(authStr == null || authStr.length() <= 7){
            // 没有 Authorization 请求头，开启质询
            return challenge(response);
        }

        DigestAuthInfo authObject = DigestUtils.getAuthInfoObject(authStr);


//        服务器的校验逻辑就包括校验 Authorization 请求头里面的 response 的值。
//        可以看到，response 算法里面有个 password 参数，浏览器通过弹框用户输入密码的值得到，服务器是通过用户名从数据库查到的。
//        摘要验证主要就是通过上面的HASH比较的步骤避免掉了基本验证中的安全性问题。

//        生成 response 的算法：
//        response = MD5(MD5(username:realm:password):nonce:nc:cnonce:qop:MD5(<request-method>:url))

        // 这里密码固定为 123456, 实际应用需要根据用户名查询数据库或缓存获得
        String HA1 = DigestUtils.MD5(authObject.getUsername()+":"+authObject.getRealm()+":123456");
        String HD = String.format(authObject.getNonce()+":"+authObject.getNc()+":"+authObject.getCnonce()+":"+authObject.getQop());
        String HA2 = DigestUtils.MD5(request.getMethod()+":"+authObject.getUri());
        String responseValid = DigestUtils.MD5(HA1+":"+HD+":"+HA2);

        // 如果 Authorization 中的 response（浏览器生成的） 与期望的 response（服务器计算的） 相同，则验证通过
        LOGGER.info("Authorization 中的 response: "+authObject.getResponse());
        LOGGER.info("期望的 response: "+responseValid);

        if(responseValid.equals(authObject.getResponse())){
            /* 判断 nc 的值，用来防重放攻击 */
            // 判断此次请求的 Authorization 请求头里面的 nc 值是否大于之前保存的 nc 值
            // 大于，替换旧值，然后 return true
            // 否则，return false

            // 测试代码 start
            int newNc = Integer.parseInt(authObject.getNc(),16);
            LOGGER.info("old nc:"+this.nc+",new nc:"+newNc);
            if(newNc>this.nc){
                this.nc = newNc;
                return true;
            }
            return false;
            // 测试代码 end
        }

        // 验证不通过，重复质询
        return challenge(response);
    }

    /**
    * @Description:  质询：返回状态码 401 和 WWW-Authenticate 响应头
     * @param response 返回false，则表示拦截器拦截请求
    * @Author: yanghailang
    * @Date: 2020/12/18  13:44
    */
    private boolean challenge(HttpServletResponse  response) {
        // 质询前，重置或删除保存的与该用户关联的 nc 值（nc：nonce计数器，是一个16进制的数值，表示同一nonce下客户端发送出请求的数量）
        // 将 nc 置为初始值 0， 这里代码省略
        // 测试代码 start
        this.nc = 0;
        // 测试代码 end

        response.setStatus(401);
        String str = MessageFormat.format("Digest realm={0},nonce={1},qop={2}","\"no auth\"",
                "\""+ DigestUtils.generateToken()+"\"","\"auth\"");
        response.addHeader("WWW-Authenticate",str);
        return false;
    }
}