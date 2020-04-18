package techfios.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import automation.base.testBase;

public class TestUtil extends testBase{
	public static String screenshotPath;
	public static String screenshotName;

	public static void captureScreenshots() throws IOException {
		
	   File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);		
		Date d = new Date();
		screenshotName=d.toString().replace(":", "_").replace(" ","_")+".jpg";
		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"\\test-output\\html\\"+screenshotName));
	}
}
