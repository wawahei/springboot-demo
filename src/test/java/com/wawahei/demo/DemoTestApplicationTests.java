package com.wawahei.demo;

import com.wawahei.demo.bean.User;
import com.wawahei.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@SpringBootTest
class DemoTestApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	void test1() {
		User user = userService.getInfo("a");
		System.out.println(user);
	}

	@Test
	void test2(){
		List names = new ArrayList<>();
		names.add("a");
		names.add("b");
		names.add("c");
		names.add("d");
		names.forEach(System.out::println);
	}


	private static void eval(List<Integer> list, Predicate<Integer> predicate){
		for(Integer n:list){
			if(predicate.test(n)){
				System.out.print(n + " ");
			}
		}
	}

	@Test
	void test3(){
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		System.out.println("输出所有数据:");
		eval(list,n->true);
		System.out.println("");
		System.out.println("输出所有偶数:");
		eval(list,n->n%2==0);
		System.out.println("");
		System.out.println("输出大于 3 的所有数字:");
		eval(list,n->n>3);
	}


	@Test
	void test4(){
		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
		List<String> filterList = strings.stream().filter(s->!s.isEmpty()).collect(Collectors.toList());
		filterList.forEach(System.out::println);
	}

	@Test
	void test5(){
		Random random = new Random();
		random.ints().limit(10).forEach(System.out::println);
	}

	@Test
	void test6(){
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		List<Integer> list = numbers.stream().map(i->i*i).distinct().collect(Collectors.toList());
		list.stream().forEach(System.out::println);
	}

	@Test
	void test7(){
		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
		long count = strings.stream().filter(s->s.isEmpty()).count();
		System.out.println(count);
	}

	@Test
	void test8(){
		Random random = new Random();
		random.ints().limit(10).sorted().forEach(System.out::println);
	}

	@Test
	void test9(){
		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
		long count = strings.parallelStream().filter(s->s.isEmpty()).count();
		System.out.println(count);
	}

	@Test
	void test10(){
		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
		List<String> list = strings.stream().filter(s->!s.isEmpty()).collect(Collectors.toList());
		list.stream().forEach(System.out::println);

		String merge = strings.stream().filter(s->!s.isEmpty()).collect(Collectors.joining(","));
		System.out.println(merge);
	}

	@Test
	void test11(){
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		IntSummaryStatistics statistics = numbers.stream().mapToInt(x->x).summaryStatistics();
		System.out.println(statistics.getMax());
		System.out.println(statistics.getMin());
		System.out.println(statistics.getSum());
		System.out.println(statistics.getAverage());
		System.out.println(statistics.getCount());
	}

	@Test
	void test12(){
		Integer a = null;
		Integer b = new Integer(10);
		Optional<Integer> optional1 = Optional.ofNullable(a);
		Optional<Integer> optional2 = Optional.ofNullable(b);

		System.out.println("a:"+optional1.isPresent());
		System.out.println("b:"+optional2.isPresent());

		Integer value1 = optional1.orElse(0);
		Integer value2 = optional2.get();

		System.out.println("value1:"+value1);
		System.out.println("value2:"+value2);

	}

	@Test
	void test13(){
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine nashorn = manager.getEngineByName("nashorn");

		String name = "hello";
		Integer result = null;
		try {
			nashorn.eval("print('"+name+"')");
			result = (Integer) nashorn.eval("10+2");
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		System.out.println(result);
	}

	@Test
	void test14(){
		LocalDateTime currentTime = LocalDateTime.now();
		System.out.println(currentTime);

		LocalDate date1 = currentTime.toLocalDate();
		System.out.println(date1);

		Month month = currentTime.getMonth();
		int day = currentTime.getDayOfMonth();
		int second = currentTime.getSecond();
		System.out.println("月："+month);
		System.out.println("日："+day);
		System.out.println("秒："+second);

		LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
		System.out.println(date2);

		LocalDate date3 = LocalDate.of(2014,Month.DECEMBER,12);
		System.out.println(date3);

		LocalTime date4=LocalTime.of(22,15);
		System.out.println(date4);

		LocalTime date5 = LocalTime.parse("20:10:10");
		System.out.println(date5);
	}

	@Test
	void test15(){
		ZonedDateTime date1 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
		System.out.println("date1: " + date1);
		ZoneId id = ZoneId.of("Europe/Paris");
		System.out.println("ZoneId: " + id);
		ZoneId currentZone = ZoneId.systemDefault();
		System.out.println("当期时区: " + currentZone);
	}

	@Test
	void test16() throws UnsupportedEncodingException {
		// 使用基本编码
		String base64encodedString = Base64.getEncoder().encodeToString("runoob?java8".getBytes("utf-8"));
		System.out.println("Base64 编码字符串 (基本) :" + base64encodedString);
		// 解码
		byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);
		System.out.println("原始字符串: " + new String(base64decodedBytes, "utf-8"));
		base64encodedString = Base64.getUrlEncoder().encodeToString("TutorialsPoint?java8".getBytes("utf-8"));
		System.out.println("Base64 编码字符串 (URL) :" + base64encodedString);
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < 10; ++i) {
			stringBuilder.append(UUID.randomUUID().toString());
		}
		byte[] mimeBytes = stringBuilder.toString().getBytes("utf-8");
		String mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
		System.out.println("Base64 编码字符串 (MIME) :" + mimeEncodedString);
	}

	@Test
	void test17(){

	}

	@Test
	void test18(){

	}

	@Test
	void test19(){

	}


}
