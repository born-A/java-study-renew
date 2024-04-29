package chapter04;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

	public static void main(String[] args) {
		Date now = new Date();
		System.out.println(now);
		
		printDate01(now);
		printDate02(now);
	}

	private static void printDate02(Date now) {
		// 년도(+1900)
		int year = now.getYear();
		
		// 월(0~11, +1)
		int month = now.getMonth();
		
		// 일
		int date = now.getDate();
		
		// 시
		int hours = now.getHours();
		
		//분
		int minutes = now.getMinutes();
		
		// 초
		int seconds = now.getSeconds();
		
		System.out.println(
				(year + 1900) + "-" +
				(month + 1) + "-" +
				date + " " +
				hours + ":" +
				minutes + ":" +
				seconds);
		
	}

	private static void printDate01(Date now) {
		// 2024-04-29 13:10:20
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = sdf.format(now);
		System.out.println(date);
	}

}
