package automation.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import techfios.utilities.ExcelReader;
import techfios.utilities.ExtentManager;


public class testBase {
	
	public static WebDriver driver;
	public static Properties config= new Properties();
    public static Properties OR= new Properties();
	public static FileInputStream Fis;
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\TestDataForAutomation.xlsx");
	public static WebDriverWait wait;
	public static Logger log=Logger.getLogger("devpinoyLogger");
	public ExtentReports rep=ExtentManager.getInstance();
	public static ExtentTest test;
	
	
	@BeforeSuite 

	public void SetUp() //we initialize everything
	
	{
		if(driver==null)
		{
		
			try {
				Fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				config.load(Fis);
				log.debug("config done");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			try {
				Fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				OR.load(Fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(config.getProperty("browser").equals("chrome"))
			{
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
				driver=new ChromeDriver();							
			}
			
			else if(config.getProperty("browser").equals("firefox"))
			{
				System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\geckodriver.exe");
				driver=new FirefoxDriver() ;
			}
			
			else if(config.getProperty("browser").equals("ie"))
			{
				System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\IEDriverServer.exe");
				driver=new InternetExplorerDriver();
			}
			
			log.debug("browser initialized");
		    driver.get(config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("wait")),TimeUnit.SECONDS);//integer.parseInt is used to convert string to numeric value
		}
		
	}
	
	
	public boolean isElementPresent(By by)
	{
		try
		{
			driver.findElement( by);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

 
 
	//to read data from excel
@DataProvider

	public Object[][] getData() throws Exception
	{
		String sheetName="LoginInfo";
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		Object[][] data = new Object[rows-1][cols];
		
		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			
			for (int colNum = 0; colNum < cols; colNum++) {
				
				data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
			}
		}
		return data;
	}



@AfterSuite
	
	public void TearDown() //we quit everything	
	{
		if(driver!=null)
		driver.quit();
		log.debug("browser closed");
	}
}
