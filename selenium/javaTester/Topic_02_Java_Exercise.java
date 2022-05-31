package javaTester;

import org.testng.annotations.Test;

public class Topic_02_Java_Exercise {
	
	@Test
	public void TC_01_Tong_Hieu_Tich_Thuong_Cua_2_So_Nguyen() {
		int a = 6;
		int b = 2;
		
		System.out.println("Tong = " + (a + b));
		System.out.println("Hieu = " + (a - b));
		System.out.println("Tich = " + (a * b));
		System.out.println("Thuong = " + (a / b));
	}
	
	@Test
	public void TC_02_DienTichHinhChuNhat() {
		float width = 7.5f;
		float height = 3.8f;
		
		System.out.println("S of rectangle = " + (width * height));
	}
	
	@Test
	public void TC_03_InChuoi_RaManHinh() {
		String name = "Automation Testing";
		
		System.out.println("Hello " + name);
	}

	

}
