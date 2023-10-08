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
 