package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	
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
		
		// Khởi tạo
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().window().setSize(new Dimension(1366, 768));
		
		// Khởi tạo Wait
		
		explicitWait = new WebDriverWait(driver, 30);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		// Speed Dropdown
		selectItemInCustomDropdown("span#speed-button", "ul#speed-menu div", "Slow");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Slow");
		
		selectItemInCustomDropdown("span#speed-button", "ul#speed-menu div", "Faster");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Faster");
		
		// File Dropdown
		selectItemInCustomDropdown("span#files-button", "ul#files-menu div", "jQuery.js");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button>span.ui-selectmenu-text")).getText(), "jQuery.js");
		
		selectItemInCustomDropdown("span#files-button", "ul#files-menu div", "Some unknown file");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button>span.ui-selectmenu-text")).getText(), "Some unknown file");

		
		// Number Dropdown		
		selectItemInCustomDropdown("span#number-button", "ul#number-menu div", "5");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "5");
		
		selectItemInCustomDropdown("span#number-button", "ul#number-menu div", "7");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "7");
		
		// Title Dropdown
		selectItemInCustomDropdown("span#salutation-button", "ul#salutation-menu div", "Mrs.");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Mrs.");
		
		selectItemInCustomDropdown("span#salutation-button", "ul#salutation-menu div", "Other");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Other");
		
	}

	@Test
	public void TC_02_Honda() {
		driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");
		scrollToElement("div.carousel-item");
		sleepInSecond(2);
		
		selectItemInCustomDropdown("button#selectize-input","button#selectize-input+div>a","CITY L");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("button#selectize-input")).getText(), "CITY L");
		
		scrollToElement("div.container");
		sleepInSecond(2);
		
		Select select = new Select(driver.findElement(By.cssSelector("select#province")));
		select.selectByVisibleText("Đà Nẵng");
		sleepInSecond(2);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Đà Nẵng");
		
		select = new Select(driver.findElement(By.cssSelector("select#registration_fee")));
		select.selectByVisibleText("Khu vực II");
		sleepInSecond(2);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Khu vực II");	
	}

	@Test
	public void TC_03_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		selectItemInCustomDropdown("div.dropdown","div.menu span.text","Matt");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Matt");
		
		selectItemInCustomDropdown("div.dropdown","div.menu span.text","Elliot Fu");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Elliot Fu");
		
		selectItemInCustomDropdown("div.dropdown","div.menu span.text","Jenny Hess");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Jenny Hess");
	}
	
	@Test
	public void TC_04_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		selectItemInCustomDropdown("li.dropdown-toggle","ul.dropdown-menu a","First Option");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");
		
		selectItemInCustomDropdown("li.dropdown-toggle","ul.dropdown-menu a","Second Option");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
		
		selectItemInCustomDropdown("li.dropdown-toggle","ul.dropdown-menu a","Third Option");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");
	
	}
	
	@Test
	public void TC_05_ReactJS_Select() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		selectItemInCustomDropdown("div.dropdown","div.menu span.text","Algeria");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Algeria");
		
		selectItemInCustomDropdown("div.dropdown","div.menu span.text","Bahrain");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Bahrain");
	}
	
	@Test
	public void TC_06_ReactJS_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		enterItemInCustomDropdown("input.search","div.menu span.text","Australia");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Australia");
		
		enterItemInCustomDropdown("input.search","div.menu span.text","Bahamas");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Bahamas");
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void scrollToElement (String Locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector(Locator)));
	}
	
	// Sleep cứng (Static wait)
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void selectItemInCustomDropdown(String parentLocator, String childLocator, String textExpectedItem) {
//		1 - Click vào 1 phần tử nào đó thuộc dropdown để cho nó xổ ra
		driver.findElement(By.cssSelector(parentLocator)).click();
		sleepInSecond(1);
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
				
//				Khi đã tìm thấy va thỏa mãn điều kiện rồi thì ko cần duyệt tiếp nữa
				break;
			}
		}
	}
	
	public void enterItemInCustomDropdown(String parentLocator, String childLocator, String textExpectedItem) {
		driver.findElement(By.cssSelector(parentLocator)).clear();
		driver.findElement(By.cssSelector(parentLocator)).sendKeys(textExpectedItem);	
		sleepInSecond(1);
		
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		
		List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));

		for(WebElement item : allItems) {
			String textItem = item.getText();
			
			if(textItem.equals(textExpectedItem)) {
				item.click();
				break;
			}
		}
	}
}