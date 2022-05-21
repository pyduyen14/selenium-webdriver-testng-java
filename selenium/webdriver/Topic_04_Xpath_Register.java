package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Xpath_Register {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		
//		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
//		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void Register_01_Empty_Data() {
		// Mở app ra
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		// Click nút Đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		// Kiểm tra 1 điều kiện trả về là đúng
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");
	}

	@Test
	public void Register_02_Invalid_Email() { 
		// Mở app ra
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		// Nhập liệu vào email field
		driver.findElement(By.id("txtFirstname")).sendKeys("Duyên Phạm");
		driver.findElement(By.id("txtEmail")).sendKeys("123@456@789");
		driver.findElement(By.id("txtCEmail")).sendKeys("123@456@789");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0907761668");		
		
		// Click nút Đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();					
				
		// Kiểm tra 1 điều kiện trả về là đúng
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
	}

	@Test
	public void Register_03_Incorrect_Confirm_Email() {
		// Mở app ra
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
				
		// Nhập liệu vào email field
		driver.findElement(By.id("txtFirstname")).sendKeys("Duyên Phạm");
		driver.findElement(By.id("txtEmail")).sendKeys("duyenpham@gmail.net");
		driver.findElement(By.id("txtCEmail")).sendKeys("duyenpham@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0907761668");				
				
		// Click nút Đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();					
						
		// Kiểm tra 1 điều kiện trả về là đúng
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");		
	}
	
	@Test
	public void Register_04_Password_Less_Than_6_Characters() {
		// Mở app ra
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
				
		// Nhập liệu vào các fields
		driver.findElement(By.id("txtFirstname")).sendKeys("Duyên Phạm");
		driver.findElement(By.id("txtEmail")).sendKeys("duyenpham@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("duyenpham@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123");
		driver.findElement(By.id("txtCPassword")).sendKeys("123");
		driver.findElement(By.id("txtPhone")).sendKeys("0907761668");			
				
		// Click nút Đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();					
						
		// Kiểm tra 1 điều kiện trả về là đúng
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");	
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
	}

	@Test
	public void Register_05_Incorrect_Confirm_Password() { 
		// Mở app ra
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
				
		// Nhập liệu vào các fields
		driver.findElement(By.id("txtFirstname")).sendKeys("Duyên Phạm");
		driver.findElement(By.id("txtEmail")).sendKeys("duyenpham@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("duyenpham@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("1234567");
		driver.findElement(By.id("txtPhone")).sendKeys("0907761668");
				
		// Click nút Đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();					
						
		// Kiểm tra 1 điều kiện trả về là đúng
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");			
	}

	@Test
	public void Register_06_Invalid_Phone() {
		// Mở app ra
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
				
		// Nhập liệu vào các fields
		driver.findElement(By.id("txtFirstname")).sendKeys("Duyên Phạm");
		driver.findElement(By.id("txtEmail")).sendKeys("duyenpham@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("duyenpham@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("090776166");		
				
		// Click nút Đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();					
						
		// Kiểm tra message từ phone number 
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
		
		// Xóa dữ liệu đã có ở phone number
		driver.findElement(By.id("txtPhone")).clear();
		
		// Nhập liệu lại vào phone
		driver.findElement(By.id("txtPhone")).sendKeys("123456");
		
		// Click nút Đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();
				
		// Kiểm tra message từ phone number 
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}