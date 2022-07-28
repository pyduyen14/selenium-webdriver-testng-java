package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
	
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {		
		//System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		//driver = new ChromeDriver();
		
		if(osName.contains("Mac")) { // Mac
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		else { // Window
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver");
		}
		
		// Khởi tao driver
		driver = new FirefoxDriver();
		
		// Khởi tạo Wait
		
		explicitWait = new WebDriverWait(driver, 30);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		// Speed Dropdown
		selectItemInCustomDropdown("span#speed-button", "ul#speed-menu div", "Slow");
		sleepInSecond(3);
		
		selectItemInCustomDropdown("span#speed-button", "ul#speed-menu div", "Faster");
		sleepInSecond(3);
		
		// File Dropdown
		selectItemInCustomDropdown("span#files-button", "ul#files-menu div", "jQuery.js");
		sleepInSecond(3);
		
		selectItemInCustomDropdown("span#files-button", "ul#files-menu div", "Some unknown file");
		sleepInSecond(3);
		
		// Number Dropdown		
		selectItemInCustomDropdown("span#number-button", "ul#number-menu div", "5");
		sleepInSecond(3);
		
		selectItemInCustomDropdown("span#number-button", "ul#number-menu div", "5");
		sleepInSecond(3);
		
		// Title Dropdown
		selectItemInCustomDropdown("span#salutation-button", "ul#salutation-menu div", "Mrs.");
		sleepInSecond(3);
		
		selectItemInCustomDropdown("span#salutation-button", "ul#salutation-menu div", "Other");
		sleepInSecond(3);
		
	}

	@Test
	public void TC_02_() {
		
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
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void selectItemInCustomDropdown(String parentLocator, String childLocator, String textExpectedItem) {
//		1 - Click vào 1 phần tử nào đó thuộc dropdown để cho nó xổ ra
		driver.findElement(By.cssSelector(parentLocator)).click();
		
//		2 - Chờ cho tất cả các item trong dropdown được load ra xong
/*      Lưu ý: Ko dùng sleep cứng 
		Phải có 1 hàm wait nào đẻ nó linh động:
		NẾu như chưa tìm thấy thì sẽ tìm lại trong khoảng time được set
		Nếu như tìm thấy rồi thì ko cần phải chờ hết khoảng time này
		Bắt được 1 locatỏ để đại diện cho tát cả các item 
*/
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		
//		3.1 - Nếu item cần chọn đang hiển thị
//		3.2 - Nếu item cần chọn ko hiển thị thì cần cuộn chuột xuống - scroll down
//		4 - Kiểm tra text của item - nếu đúng vs cái mình cần thì click vào
//		Viết code để duyệt qua từng item và kiểm tra theo điều kiện
		
		// Lưu trữ tất cả các item lại thì mới duyệt qua được
		List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));
		
//		Duyệt qua từng item 
//		Vòng lặp foreach
		
		for(WebElement item : allItems) {
//			DÙng biến item để thao tác trong vòng lặp for
			
//			Lấy ra text
			String textItem = item.getText();
			
//			Kiểm tra nếu nó bằng với text mình mong muốn
			if(textItem.equals(textExpectedItem)) {
//				Nó sẽ nhận vào 1 điều kiện là boolean (true/false)
//				Nếu như điều kiện mà đúng thì mới vào trong hàm if
//				Điều kiện mà sai thì bỏ qua
//				
//				Thì click vào
				item.click();
			}
		}
	}
}