package GKV.SeleniumMaven;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import util.BrowserDriver;

public class SampleDriverCalling {
	WebDriver driver = null;
	BrowserDriver obj = new BrowserDriver();
	@BeforeTest
	public void getBrowserDriver() throws IOException
	{
		//driver = obj.getDriver("Chrome");
		
		/*Using property file*/
		FileReader reader = new FileReader("config.properties");

		Properties p = new Properties();
		p.load(reader);
		driver = obj.getDriver(p.getProperty("Browser"));
	}
	
	@Test
	public void openGoogle() throws InterruptedException
	{
		obj.navigateTo("https://google.com/");
		Thread.sleep(1000);
	}
	
	@AfterTest
	public void closeBrowser()
	{
		obj.closeBrowser();
	}
	

}
