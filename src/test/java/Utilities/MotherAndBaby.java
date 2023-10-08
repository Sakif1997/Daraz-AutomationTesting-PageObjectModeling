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
