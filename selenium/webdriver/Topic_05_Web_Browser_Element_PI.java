package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_Element_PI {
	// Khai báo 
	WebDriver driver;
	WebElement element;
	
	// Khai báo và khởi tạo
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	public void beforeClass() {	
		if(osName.contains("Mac")) { // Mac
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		else { // Window
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver");
		}
		//System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		
		// Khởi taoj
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void TC_01_Browser() {
		// Các hàm tương tác và Browser sẽ thông qua biến driver
		
		// Đóng  tab/ window đang active
		driver.close(); //**
		
		// Đóng  browser
		driver.quit(); //**
		
		// Tìm ra 1 element (single)
		WebElement loginButton = driver.findElement(By.cssSelector("")); //**
		
		// Tìm ra nhiều element (multiple)
		List<WebElement> links = driver.findElements(By.cssSelector("")); //**
		
		// Mở ra cái Url truyền vào
		driver.get("https://www.facebook.com/"); //**
		
		// Trả về 1 Url tại tab đang đứng
		String gamePageUrl= driver.getCurrentUrl();
		
		String gamePageTitle = driver.getTitle();
		
		// Source code của page hiện tại
		String gamePageSourceCode = driver.getPageSource();
		
		// Lấy ra cái ID của Tab/ Window đang đứng/ active (Window/ Tab)
		driver.getWindowHandle(); // 1 //**
		driver.getWindowHandles(); // Tất cả  //**
		
		
		driver.manage().getCookies(); // Cookies Framework
		driver.manage().logs().getAvailableLogTypes(); // Log Framework
		
		driver.manage().window().fullscreen(); //**
		driver.manage().window().maximize(); //**
		
		
		//Test GUI (Graphic User Interface)
		// Font/ Size/ Color/ Position/ Location/...
		// Ưu tiên làm chức năng trước (Functional UI)
		// Ưu tiên làm UI sau (Graphic UI)
		
		
		//Chờ cho element được tìm thấy trong khoảng time xx giây (WebDriverWait)
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); //**
		
		//Chờ cho page load thành công sau khoảng xx giây
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		
		//Chờ cho script được inject thành công vào browser/ element sau khoảng xx giây (JavascriptExecutor)
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		driver.navigate().to("https://www.facebook.com/");
		
		
		// Alert/ Frame (Iframe)/ Window (tab)
		driver.switchTo().alert(); //**
		
		driver.switchTo().frame(0); //**
		
		driver.switchTo().window(""); //**
	}
	
	@Test
	public void TC_02_Element() {
		driver.get("https://www.facebook.com/");
		// Các hàm tương tác và Element sẽ thông qua cái class WebElement (biến nào đó)
		
		// Xóa dữ liệu trong 1 field dạng editable (có thể nhập)
		// Textbox/ Text Area/ Editable Dropdown
		element.clear();
		
		//Nhập dữ liệu vào field dạng editable
		element.sendKeys("duyen@gmail.com");
		element.sendKeys(Keys.ENTER);
		
		// Trả về giá trị nằm trong cái attribute của element
		element.getAttribute("placeholder");
		
		//Email addres or phone number
		driver.findElement(By.id("firstname")).getAttribute("value");
		
		// Trả về thuộc tính Css của element này
		// Font/ size/ color
		
		// Trả về màu nền của element
		element.getCssValue("background-color");
		
		// Trả về màu nền của element
		element.getCssValue("font-size");
		
		// Test GUI: Point/ Rectangle/ Size (Visualize Testing)
		element.getLocation();
		element.getRect(); // Tọa độ
		element.getSize();
		
		// Chụp hình và attach vào HTML Report
		element.getScreenshotAs(OutputType.FILE);
		
		// Trả về tên thẻ HTML của element
		WebElement elmailAddressTextbox = driver.findElement(By.xpath("//input[@id='email']"));
		elmailAddressTextbox = driver.findElement(By.cssSelector("#email"));
		element.getTagName();
		// input
		
		// Trả về text của 1 element của 1 the input(Link/ Header/ Message Lỗi/ Message success)
		element.getText();
		
		// Trả về giá trị đúng hoặc sai của 1 element có Hiển thì hoặc ko
		element.isDisplayed();
		// Hiện thị : true
		// Ko hiển thị: false
		
		// Trả về giá trị đúng hoặc sai của 1 element có thao tác được hay ko
		// Có bị disabled ko
		element.isEnabled();
		// Enable : true
		// Bị Disable : false
		
		// Trả về giá trị đúng hoặc sai của 1 element đã được chọn rồi hay chưa
		// Checkbox/ Radio 
		element.isSelected();
		// Chọn rồi : true
		// Chưa chọn : false
		
		// Dropdown: có 1 thư viện riêng để xử lý (select)
		
		// Chi làm việc được với form (register/ login/ search/ ...)
		// Submit = ENTER ở 1 field nào đó
		// Submit vào 1 field nào đó trong form
		element.submit();
	}
	
	@Test
	public void TC_03_() {
		
	}
	
	public void afterClass() {
		driver.quit();
	}
	
}
