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
