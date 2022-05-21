package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Xpath_Login {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, fullName, emailAddress, password;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		firstName = "Automation"; 
		lastName= "FC";
		fullName = firstName + " " + lastName;
		emailAddress = "afc" + generateRandomNumber() + "@hotmail.vn"; 
		password = "12345678";
	}

	@Test
	public void Login_01_Login_With_Empty_Email_And_Password() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.name("login[password]")).clear();
		
		driver.findElement(By.cssSelector("button[id='send2']")).click();
		
		// So sánh 1 chuỗi tìm được với 1 chuỗi mong muốn
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
	}
	
	@Test
	public void Login_02_Login_With_Invalid_Email() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("123@456.789");
		driver.findElement(By.name("login[password]")).sendKeys("123456");
		
		driver.findElement(By.cssSelector("button[id='send2']")).click();
		
		// So sánh
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
	}
	
	@Test
	public void Login_03_Login_With_Password_Less_Than_6_Characters() {
		// Mở app
		driver.get("http://live.techpanda.org/");
				
		// Click vào link My Account
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
				
		// Nhập dữ liệu valid vào email
		driver.findElement(By.id("email")).sendKeys("automationfc@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123");
				
		// Click vào nút login
		driver.findElement(By.cssSelector("button[id='send2']")).click();
				
		// Kiểm tra message từ password
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	@Test
	public void Login_04_Login_With_Incorrect_Email_And_Password() {
		// Mở app
		driver.get("http://live.techpanda.org/");
						
		// Click vào link My Account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
						
		// Nhập dữ liệu valid vào email
		driver.findElement(By.id("email")).sendKeys("automationfc@gmail.com");
						
		// Nhập dữ liệu invalid vào password
		driver.findElement(By.name("login[password]")).sendKeys("123123123");
						
		// Click vào nút login
		driver.findElement(By.cssSelector("button[id='send2']")).click();
						
		// Kiểm tra message từ password
		Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(), "Invalid login or password.");
	}

	@Test
	public void Login_05_Create_New_Account() {
		// Mở app
		driver.get("http://live.techpanda.org/");
						
		// Click vào link My Account
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		
		// Click vào nút Create An Account 
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
						
		// Nhập dữ liệu valid các fields
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		
		// Nhập random dữ liệu cho email
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
						
		// Click vào nút login
		driver.findElement(By.xpath("//button[@title='Register']")).click();
						
		// So sánh tuyệt đối
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");
		
		// So sánh tương đối
		String contactInfoText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div//following-sibling::div/p")).getText();
		Assert.assertTrue(contactInfoText.contains(fullName));
		Assert.assertTrue(contactInfoText.contains(emailAddress));
		
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();		
	}

	@Test
	public void Login_06_Login_With_Valid_Email_And_Password() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys(emailAddress);
		driver.findElement(By.name("login[password]")).sendKeys(password);
						
		driver.findElement(By.cssSelector("button[id='send2']")).click();
		
		String contactInfoText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div//following-sibling::div/p")).getText();
		Assert.assertTrue(contactInfoText.contains(fullName));
		Assert.assertTrue(contactInfoText.contains(emailAddress));

	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int generateRandomNumber() {
		Random rand =  new Random();		
		return rand.nextInt(99999);
	}
}