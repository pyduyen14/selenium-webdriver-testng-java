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


public class Topic_06_Textbox_TextArea {
	WebDriver driver;	
	String firstName, lastName, employeeId, editFirstName, editLastName;
	String immgrationNumber, comments;
	
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
		
		firstName = "Luis";
		lastName = "Suarez";
		editFirstName = "Mohammed";
		editLastName = "Salah";
		immgrationNumber = "774703475";
		comments = "79 Madeira Way\nMadeira Beach\nFL 33708 USA";
	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_DropDown() {
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		// Input ID and Password
		driver.findElement(By.cssSelector("input#txtUsername")).sendKeys("Admin");
		driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("admin123");
		
		// CLick on Login button
		driver.findElement(By.cssSelector("input#btnLogin")).click();
		
		sleepInSecond(5);
		
		// Click on Add Employee button
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/pim/addEmployee");
		
		// Input valid first name and last Name fields
		driver.findElement(By.cssSelector("input#firstName")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input#lastName")).sendKeys(lastName);
		
		
		// lấy giá trị của Employee Id và gán vào biến employeId
		employeeId = driver.findElement(By.cssSelector("input#employeeId")).getAttribute("value");
		
		// Click on Save button
		driver.findElement(By.cssSelector("input#btnSave")).click();
		
		// Tab: Gõ ra 1 phần của hàm
		// Endter: gõ ra 1 phần rồi Enter -> Lấy ra toàn bộ tên hàm
		
		// Verify the fields are disabled
		Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmployeeId")).isEnabled());
		
		// Verify actual value the same expect value
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmployeeId")).getAttribute("value"), employeeId);
		
		// Click on Edit button
		driver.findElement(By.cssSelector("input#btnSave")).click();
		sleepInSecond(3);
		
		// Verify the fields are enabled
		Assert.assertTrue(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).isEnabled());
		Assert.assertTrue(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).isEnabled());
		Assert.assertTrue(driver.findElement(By.cssSelector("input#personal_txtEmployeeId")).isEnabled());
		
		driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).clear();
		driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).sendKeys(editFirstName);
		driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).clear();
		driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).sendKeys(editLastName);
		
		// Click on Save button
		driver.findElement(By.cssSelector("input#btnSave")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).getAttribute("value"), editFirstName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).getAttribute("value"), editLastName);
		
		// Click on Save button
		driver.findElement(By.cssSelector("input#btnSave")).click();
		
		// Verify textbox First Name/ Last Name/ Employee ID are disabled
		Assert.assertTrue(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).isEnabled());
		Assert.assertTrue(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).isEnabled());
		Assert.assertTrue(driver.findElement(By.cssSelector("input#personal_txtEmployeeId")).isEnabled());
		
		
		// Verify actual value the same expect value
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).getAttribute("value"), editFirstName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).getAttribute("value"), editLastName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmployeeId")).getAttribute("value"), employeeId);
		
		// Open Immgration tab
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		
		// Click on Save button
		driver.findElement(By.cssSelector("input#btnAdd")).click();
		
		// Input value for Number textbox, Comments textarea fields
		driver.findElement(By.cssSelector("input#immigration_number")).sendKeys(immgrationNumber);
		driver.findElement(By.cssSelector("textarea#immigration_comments")).sendKeys(comments);
		sleepInSecond(5);
		
		// Click on Save button
		driver.findElement(By.cssSelector("input#btnSave")).click();
		sleepInSecond(3);
		
		// Click on Passport link
		driver.findElement(By.xpath("//a[text()='Passport']")).click();
		
		// Verify actual value the same expect value
		Assert.assertEquals(driver.findElement(By.cssSelector("input#immigration_number")).getAttribute("value"), immgrationNumber);
		Assert.assertEquals(driver.findElement(By.cssSelector("textarea#immigration_comments")).getAttribute("value"), comments);
	}

	@Test
	public void TC_02_() {
		
		
	}

	@Test
	public void TC_03_() {
	
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
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