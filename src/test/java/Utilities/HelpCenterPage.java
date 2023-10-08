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
		//next page visible howar jonno oi page ar title use korte pari
		WaitElementVisible(HELPCENTERPAGE);
		//takeScreenshot("Help Center Page");
	}
	

}
