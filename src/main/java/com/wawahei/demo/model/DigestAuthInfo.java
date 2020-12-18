package com.wawahei.demo.model;

import lombok.Data;
import lombok.ToString;
import org.apache.tomcat.util.http.parser.Authorization;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-18 13:51
 **/
@Data
@ToString
public class DigestAuthInfo {

//    WWW-Authentication：用来定义使用何种方式（Basic、Digest、Bearer等）去进行认证以获取受保护的资源
//    Authorization-Info：用于返回一些与授权会话相关的附加信息
//    nextnonce：下一个服务端随机数，使客户端可以预先发送正确的摘要
//    rspauth：响应摘要，用于客户端对服务端进行认证
//    stale：当密码摘要使用的随机数过期时，服务器可以返回一个附带有新随机数的401响应，并指定stale=true，表示服务器在告知客户端用新的随机数来重试，而不再要求用户重新输入用户名和密码了

    private String username;
    //表示Web服务器中受保护文档的安全域（比如公司财务信息域和公司员工信息域），用来指示需要哪个域的用户名和密码
    private String realm;
//    服务端向客户端发送质询时附带的一个随机数，这个数会经常发生变化。客户端计算密码摘要时将其附加上去，使得多次生成同一用户的密码摘要各不相同，用来防止重放攻击
    private String nonce;
    private String uri;
//    这是由用户代理软件计算出的一个字符串，以证明用户知道口令
    private String response;
//    保护质量，包含auth（默认的）和auth-int（增加了报文完整性检测）两种策略，（可以为空，但是）不推荐为空值
    private String qop;
//    nonce计数器，是一个16进制的数值，表示同一nonce下客户端发送出请求的数量。例如，在响应的第一个请求中，客户端将发送“nc=00000001”。这个指示值的目的是让服务器保持这个计数器的一个副本，以便检测重复的请求
    private String nc;
//    客户端随机数，这是一个不透明的字符串值，由客户端提供，并且客户端和服务器都会使用，以避免用明文文本。这使得双方都可以查验对方的身份，并对消息的完整性提供一些保护
    public String cnonce;
}