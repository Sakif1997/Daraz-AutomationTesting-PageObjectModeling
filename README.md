
# Daraz Website Testing with Page Object Model
Test site: https://www.daraz.com.bd/



The following instructions will help you navigate those testing pages. We will create some packages. At the package level, there is a list of classes where you can create methods, use methods for particular pages, and run and test the testing pages. We can also perform tests on the Daraz login Page by filling in fields in an automated way.






## Target/Goals

Test Some pages of Daraz website and the daraz login page
the following tasks:
1. Set Environment
i) pom.xml [dependencies set]
ii) BrowserSetup [create a separate package ]

2. Page Object Model: create methods, using methods for separate page and create test cases of those pages
a) Methods
b) Utitilites [Page objects]
c) TestCase [Particular Pages ]

3. Create Allure report 

##  Set Environment
Create a Maven Project
Set pom.xml file 
pom.xml file Code:
Set Under Dependencies
```ruby
  <dependencies>
      <!-- https://mvnrepository.com/artifact/org.testng/testng -->
<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>7.6.1</version>
    <scope>test</scope>
</dependency>
<!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.6.0</version>
</dependency>
<!-- https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager -->
<dependency>
    <groupId>io.github.bonigarcia</groupId>
    <artifactId>webdrivermanager</artifactId>
    <version>5.3.0</version>
</dependency>
  </dependencies>
</project>
```

Create Some class under Packages  
![picture1](https://github.com/Sakif1997/Automation_testing_WebSite/assets/45315685/d3806a7f-0b4f-4422-be51-a389f3b8b4b0)



One of the packages will hold BorwserSetup, in which we run the automation Code
Inside BorwserSetup Class:

It will hold four drivers; use according to your preferences 
I found some difficulties running Chrome Browser, which is why I used Edge Driver to run my code.
```ruby
package com.bd.Aug;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserSetup {
	private static String BrowserName = System.getProperty("browser", "Edge");
	private static final ThreadLocal<WebDriver> DRIVER_LOCAL = new ThreadLocal<>();
	public static WebDriver getDriver() {
		return DRIVER_LOCAL.get();
	}
	public static void setDriver(WebDriver driver) {
		BrowserSetup.DRIVER_LOCAL.set(driver);
	}
	public static WebDriver getBrowser(String BrowserName) {
		switch (BrowserName.toLowerCase()) {
		case "chrome":
			ChromeOptions option1 = new ChromeOptions();
			option1.addArguments("--remote-allow-origins=*");
			WebDriverManager.chromedriver().setup();
			return new ChromeDriver(option1);
		case "edge":
			EdgeOptions option2 = new EdgeOptions();
			option2.addArguments("--remote-allow-origins=*");
			WebDriverManager.edgedriver().setup();
			return new EdgeDriver(option2);
		case "firefox":
			FirefoxOptions option3 = new FirefoxOptions();
			option3.addArguments("--remote-allow-origins=*");
			WebDriverManager.firefoxdriver().setup();
			return new FirefoxDriver(option3);
		default:
			throw new RuntimeException("Browser Not found");
		}
	}
	@BeforeSuite
	public static synchronized void setBrowser() {
		WebDriver webDriver = getBrowser(BrowserName);
		webDriver.manage().window().maximize();
		setDriver(webDriver);
	}
	@AfterSuite
	public static synchronized void quitBrowser() {
		getDriver().quit();
	}
}


```

## Page Object model

Create a package which include classes like method and other testing Page

method class includes methods of getElement, click, hover, getText, field fillup, visible wait element, click wait element, Press ok in the popup, press cancel in the popup, Scroll up, scroll down, copy all, paste, next field, take screenshot

```ruby
package Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Allure;

import static com.bd.Aug.BrowserSetup.getDriver;

import java.io.ByteArrayInputStream;
import java.time.Duration;

public class Methods {	
	public WebElement getElement(By locator) {
		//driver.findElement(By.xpath())
		return getDriver().findElement(locator);//getDriver() = driver
	}
	public void clickElement(By locator) {
		getElement(locator).click();
	}
	public void clickWaitElement(By locator) {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
		//getElement(locator).click();
		WebElement element= wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();

	}
	public void WaitElementVisible(By locator) {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	public String gettext(By locator) {
		return getElement(locator).getText();
	} 
	// field entry
	public void FieldValue(By locator,String text) {
		getElement(locator).sendKeys(text);
	}
	public void Hover(By locator) {
		Actions action = new Actions(getDriver());
		//action.moveToElement(driver.findElement(locator)).perform();
		action.moveToElement(getElement(locator)).perform();;
	}
	//alert or tost message ok and cancel
	public void PressOk() {
		//driver.switchTo().alert().accept();
		getDriver().switchTo().alert().accept();
	}
	public void PressCancel() {
		getDriver().switchTo().alert().dismiss();
	}
	public void ScrollUp() {
		JavascriptExecutor js =(JavascriptExecutor)getDriver();
		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}
	public void ScrollDown() {
		JavascriptExecutor js = (JavascriptExecutor)getDriver();
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	public void CopyAll() {
		Actions action = new Actions(getDriver());
		action.keyDown(Keys.CONTROL);
		action.sendKeys("a");
		action.keyUp(Keys.CONTROL);
		action.build().perform();
		action.keyDown(Keys.CONTROL);
		action.sendKeys("c");
		action.keyUp(Keys.CONTROL);
		action.build().perform();	
	}
	public void Paste(){
		Actions action = new Actions(getDriver());
		action.keyDown(Keys.CONTROL);
		action.sendKeys("v");
		action.keyUp(Keys.CONTROL);
		action.build().perform();
	}
	//you can use it multiple times back to back
	public void NextField() {
		Actions action = new Actions(getDriver());
		action.sendKeys(Keys.TAB);
		action.build().perform();;
	}
	//for allure report
	public void takeScreenshot(String name) {
		Allure.addAttachment(name, new ByteArrayInputStream(((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BYTES)));
	}

}
```

Using those methods to create test page objects
Page directory image:

 ------------------testing page image--------------
![helpcenterOption](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/a2bed84e-e7c6-4108-98c6-265e257e1632)

 Page Objects Creation
```ruby
package Utilities;
import org.openqa.selenium.By;
public class HelpCenterPage extends Methods{
	public By HELPSUPPORTBUTTON = By.xpath("//span[contains(text(),'Help & Support')]");
	public By HTITLE= By.xpath("//span[@class='helpText__foZwJBMm']");
	public By HELPCENTERPAGE = By.xpath("//a[contains(text(),'Help Center')]");
	public String HelpPagetitle = "Help Center";
	
	public void GoHelpCenterPage() {
		Hover(HELPSUPPORTBUTTON);
		clickWaitElement(HELPCENTERPAGE);
		WaitElementVisible(HELPCENTERPAGE);
	}
}
```
Page directory image:

-------------Testing page image---------------
![kidsToy](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/8d7a0956-0d37-41e2-88b3-2e07e59477da)

 Page Objects Creation
```ruby
package Utilities;
import org.openqa.selenium.By;
public class MotherAndBaby extends Methods{
	public By MOTHERBABAY = By.xpath("//span[normalize-space()='Mother & Baby']");
	public By KIDSTOY = By.xpath("(//span[contains(text(),'Kids Toys')])[1]");
	public By ACTIONFIGURES = By.xpath("//img[@alt='Action Figures & Collectibles']");
	public By CURRENTPAGETITLE = By.xpath("//title[contains(text(),'Action Figures - Buy Action Figures at Best Price ')]");
	public String PAGETITLE = "Action Figures - Buy Action Figures at Best Price in Bangladesh | www.daraz.com.bd";
	
	public void MotherAndBabyCategory() throws InterruptedException{
		Hover(MOTHERBABAY);
		Thread.sleep(2000);
		Hover(KIDSTOY);
		Thread.sleep(2000);
		clickElement(ACTIONFIGURES);
		takeScreenshot("Action Figure Page Overview");
	}

}
```

Page directory image:

-------------Login testing page---------------
![loginPage1](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/80a8a5ea-5335-46e8-a246-9757a57577aa)

 Page Objects Creation
```ruby
package Utilities;
import org.openqa.selenium.By;
public class LoginPage extends Methods{
	public By LONGINPAGE =By.xpath("//a[contains(text(),'Login')]");
	public String loginPageTitle ="Daraz.com.bd: Online Shopping Bangladesh - Mobiles, Tablets, Home Appliances, TV, Audio &amp; More";
	public By USERNAMEFIELD = By.xpath("//input[@type ='text']");
	public By PASSWORDFIELD = By.xpath("//input[@type ='password']");
	public By LOGINBUTTON = By.xpath("//button[contains(text(),'LOGIN')]");
	public void DoLoginPage(String email, String password) throws InterruptedException{
		clickElement(LONGINPAGE);
		Thread.sleep(5000);
		FieldValue(USERNAMEFIELD, email);
		FieldValue(PASSWORDFIELD, password);
		clickElement(LOGINBUTTON);	
	}
}
```





## Test Cases

Create Test Case Package
Create Sepeate class for separate page 

i) For Help Center page

```ruby
package TestCases;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import com.bd.Aug.BrowserSetup;
import Utilities.HelpCenterPage;

public class HelpCenterPageTest extends BrowserSetup {
	HelpCenterPage helpcenter = new HelpCenterPage();
	@Test(description = "Help Center Page Test")
	public void HTest() throws InterruptedException{
		getDriver().get("https://www.daraz.com.bd/");
		Thread.sleep(2000);
		helpcenter.GoHelpCenterPage();
		Thread.sleep(2000);
		assertEquals(getDriver().getTitle(), helpcenter.HelpPagetitle);
		System.out.println(getDriver().getTitle());
		helpcenter.takeScreenshot("Help Center Page");
	}
}

```
ii) For Login page

```ruby
package TestCases;
import org.testng.annotations.Test;
import com.bd.Aug.BrowserSetup;
import Utilities.LoginPage;
import io.qameta.allure.Description;
public class LoginPagetest extends BrowserSetup{
	LoginPage loginpage = new LoginPage();
	@Test
	@Description ("Login Page Test")
	public void loginSignUp() throws InterruptedException{
		getDriver().get("https://www.daraz.com.bd/");
		Thread.sleep(2000);
		loginpage.DoLoginPage("sakifrockz@gmail.com", "sakif@006");
		Thread.sleep(2000);
		//driver.getTitle() = that page title
		System.out.println(getDriver().getTitle());
		loginpage.takeScreenshot("Login Page Overview");
	}

}
```
i) For Daraz menu check  [Mother & Baby menu page]

```ruby
package TestCases;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import com.bd.Aug.BrowserSetup;
import Utilities.MotherAndBaby;
public class MotherAndBabyCategoryTest extends BrowserSetup{
	MotherAndBaby motherandbaby = new MotherAndBaby();
	@Test
	public void TestMotherAndBabyCategory() throws InterruptedException{
		getDriver().get("https://www.daraz.com.bd/");
		Thread.sleep(2000);
		motherandbaby.MotherAndBabyCategory();
		assertEquals(getDriver().getTitle(), motherandbaby.PAGETITLE);
		System.out.println(getDriver().getTitle());
	}

}
```

## Allure Report Creation

To create an allure report, 
first set dependency in the pom.xml file.
<dependency>
		<groupId>io.qameta.allure</groupId>
		<artifactId>allure-testng</artifactId>
		<version>2.19.0</version>
</dependency>
2. create another pom.xml file by running whole test togather
file name would be testing.xml
--Whole Testpackage convert testng
![Allure1](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/933054d5-3b52-4739-9918-1c09cea2a1d8)

-- Creating test.xml file procedure
![CreateTestPageRunTOgather](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/929567a0-6f86-4372-aaa5-7beeefd91a2d)
--Test.xml file look like
![TestingXml](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/16e27e58-63fc-4cb2-8fcc-aa3b52aa5621)

3. then run the testing.xml file 
4. then refresh the whole package and see a "allure-results" file created under Maven Dependencies
![Whole Package refresh](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/1cdf2cab-e77f-4062-9c55-e956c8023544)
-before refresh
![before Refresh](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/99813437-def0-4527-bf0b-22bdb9f48fb9)
-after refresh
![after refresh](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/ec8966cd-d210-4326-9a71-fbd44c687b81)


5. open the whole package terminal
![whole Package Terminal open](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/3842d5e0-5f9e-4391-9334-c97736b1e458)

6. then write in terminal to clean previous files> ""allure generate ./allure-result --clean""
7. then write in terminal to create allure report> ""allure open ./allure-report""
8. terminal gives us http to show us an allure report file directory
---------6,7,8 terminal looklike-------------
![Allure report Creation Final](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/27484a34-56a4-4ded-872d-c2147667d138)

9. Create some methods for allure report (like allure ScreenShot)
10. method add:
```ruby
public void takeScreenshot(String name) {
		Allure.addAttachment(name, new ByteArrayInputStream(((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BYTES)));
	}
```
### Allure report file
http://192.168.0.3:62310/index.html

Login Page Report:
![Login Page Allure](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/71089c28-2cbf-43a2-a6e4-f3514e4c35c1)

Help Center Page Report:
![HelpCenterPage Report](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/46f2dc32-790a-45b9-a46e-7f0b9096c120)

Menu to target page Report:
![Category report](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/a86e2949-97bc-478b-8b4e-9fbcd4dbaa1e)

