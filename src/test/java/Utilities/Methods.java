package Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Allure;

import static com.bd.Aug.BrowserSetup.getDriver;

import java.io.ByteArrayInputStream;
import java.time.Duration;

public class Methods {	
	public WebElement getElement(By locator) {
		//Important information [import static PackageNameOfBrowsersetup.BrowserSetup.getDriver;] lagbe
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
		action.build().perform();
	}
	public void clearField() {
		Actions action = new Actions(getDriver());
		action.keyDown(Keys.CONTROL);
		action.sendKeys("a");
		action.keyDown(Keys.BACK_SPACE);
		action.build().perform();		
	}
	//for allure report
	public void takeScreenshot(String name) {
		Allure.addAttachment(name, new ByteArrayInputStream(((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BYTES)));
	}
	public void DropDownSelectByVisibleText(By locator, String text) {
		WebElement dropDownField = getElement(locator);
		Select select = new Select(dropDownField);
		select.selectByVisibleText(text);
	}
	//get text from locator
	public String getText(By locator) {
		WebElement element =getElement(locator);
		return element.getText();
		
	}
	

}
