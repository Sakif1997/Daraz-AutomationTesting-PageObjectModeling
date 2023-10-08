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
