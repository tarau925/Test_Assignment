package automation.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automation.base.testBase;
import techfios.utilities.ExcelReader;
import techfios.utilities.TestUtil;


public class ValidLoginTest extends testBase {
	
	@Test(dataProvider="getData")
	
	public void ValidloginTest(String username, String password) throws InterruptedException, IOException
	
	{	
		
//verifying the logo and the image in the login page
		log.debug("inside login page");
		WebElement logo=driver.findElement(By.xpath(OR.getProperty("logo")));
		Assert.assertTrue(logo.isDisplayed());
		log.debug("verified logo");
		
		WebElement image=driver.findElement(By.xpath(OR.getProperty("image")));
		Assert.assertTrue(image.isDisplayed());
		log.debug("image is displayed");

//fifth test case(entering valid user name and password and logging
//in successfully
		
		WebElement userName=driver.findElement(By.id(OR.getProperty("username")));
		userName.sendKeys(username);
		WebElement passWord =driver.findElement(By.id(OR.getProperty("password")));
		passWord.sendKeys(password);
	    driver.findElement(By.xpath(OR.getProperty("loginbutton"))).click();
		log.debug("inside login page");	
		driver.close();
				
	}
}


