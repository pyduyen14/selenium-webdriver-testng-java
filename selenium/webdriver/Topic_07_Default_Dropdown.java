package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.seleniumhq.jetty9.servlet.listener.ELContextCleaner;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_07_Default_Dropdown {
	WebDriver driver;	
	Select select;
	Random rand;
	
	
	// Khai báo và khởi tạo
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name"); 

	@BeforeClass
	public void beforeClass() {
		if(osName.contains("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver");
		}
		//System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");	
		//System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		
		// Khởi tạo driver
		driver = new FirefoxDriver();
		
		rand = new Random();
	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Default_Dropdown() {
		driver.get("https://demo.nopcommerce.com/");
		
		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("John");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Biden");
		
		// Khởi tạo select để thao tác vs Day dropdown
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
		
		// Chọn item có text = 13
		select.selectByVisibleText("13");
		
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
		select.selectByVisibleText("May");
		
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
		select.selectByVisibleText("1995");
		
		String emailAddress = "johnbiden" + rand.nextInt(9999) + "@hotmail.net";
		
		driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
		
		driver.findElement(By.cssSelector("input#Company")).sendKeys("White House");
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
		
		driver.findElement(By.cssSelector("button#register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
	
		driver.findElement(By.cssSelector("a.ico-account")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"), "John");
		Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"), "Biden");
		
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "13");
		
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "May");
		
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1995");
		
		Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute("value"), emailAddress);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#Company")).getAttribute("value"), "White House");
	}

	@Test
	public void TC_02_Default_Dropdown() {
		driver.get("https://www.rode.com/wheretobuy");
		
		select = new Select(driver.findElement(By.cssSelector("select#country")));
		select.selectByValue("Vietnam");
		sleepInSecond(3);
		
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");
		
		List<WebElement> delears = driver.findElements(By.cssSelector("div#map h4")); 
		
		for (WebElement element: delears) {
			System.out.print(element.getText());	
		}
	}

	@Test
	public void TC_03_() {
	
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	// Sleep cứng (Static wait)
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}