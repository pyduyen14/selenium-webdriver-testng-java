package javaTester;

import java.util.Scanner;

import org.testng.annotations.Test;

public class Topic_04_Java_Exercise {

	@Test
	public void TC_01_assign() {
		int age = 23;
		String name = "Tuan";
		
		System.out.println("After 15 years, age of " + name + " will be " + (age+15));
	}
	
	@Test
	public void TC_02_swapNumber() {
		int a = 3;
		int b = 4;
		System.out.println("Before swapping the a = " + a + ", b = " + b);
		
		a = a + b;
		b = a - b;
		a = a - b;
		
		System.out.println("After swapping the a = " + a + ", b = " + b);
	}
	
	@Test
	public void TC_03_condition() {
		int a = 5;
		int b = 4;
		
		boolean result = (a > b) ? true : false;
		
		System.out.println(result);
		
		a = 3;
		
		boolean result1 = (a > b) ? true : false;

		System.out.println(result1);
	}
}
