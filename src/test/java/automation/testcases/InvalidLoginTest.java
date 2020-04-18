package automation.testcases;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import techfios.utilities.ExcelReader;
import techfios.utilities.TestUtil;
import automation.base.testBase;

public class InvalidLoginTest extends testBase {
	
	@Test
	public void InvalidloginTest() throws InterruptedException
	
	{	
		log.debug("inside login page");
		WebElement logo=driver.findElement(By.xpath(OR.getProperty("logo")));
		Assert.assertTrue(logo.isDisplayed());
		log.debug("logo displayed");
		
	
		
//  Test case #3 (verify error message when trying to log in 
//	without entering username and password)
		
		WebElement loginbutton=driver.findElement(By.xpath(OR.getProperty("loginbutton")));
		loginbutton.click();		
		WebElement errormessage=driver.findElement(By.xpath(OR.getProperty("errormessage")));
		String message =errormessage.getText();				
		Assert.assertEquals(message,OR.getProperty("error"));
		log.debug("first assertion passed");

		
		
//Test case #5(verify error message when trying to log in without
// entering a password)
		
		WebElement username=driver.findElement(By.id(OR.getProperty("username")));
		username.sendKeys("Hello");
		loginbutton.click();		
		WebElement errormessage1=driver.findElement(By.xpath(OR.getProperty("errormessage")));
		String message1 =errormessage1.getText();				
		Assert.assertEquals(message1,OR.getProperty("passworderror"));
		log.debug("assertion for no password passed");
		
		
//Test case #8(verify error message when entering  
//invalid username and invalid password)
		
		WebElement password =driver.findElement(By.id(OR.getProperty("password")));
		password.sendKeys("world");
		loginbutton.click();		
		WebElement errormessage2=driver.findElement(By.xpath(OR.getProperty("errormessage")));	
		Assert.assertEquals(errormessage2.getText(),OR.getProperty("displayedmessage"));
		log.debug("assertion for invalid username and password passed");
		
	
//Test case #9 { verify error message  when entering a locked out user information

		
		username.click();
		username.clear();		
		username.sendKeys("locked_out_user");
		password.click();
		password.clear();
		password.sendKeys("secret_sauce");
		loginbutton.click();	
		WebElement errormessage3=driver.findElement(By.xpath(OR.getProperty("errormessage")));	
		Assert.assertEquals(errormessage3.getText(),OR.getProperty("message"));
		log.debug("assertion for username locked_out_user passed");
		
		driver.close();
	}
	
	
	
} 


