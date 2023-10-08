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
