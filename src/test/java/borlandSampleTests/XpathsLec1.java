package borlandSampleTests;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import util.BrowserDriver;

public class XpathsLec1 {
	
	//tagname[@attribute='value']
	//img[@alt='Google']
	//input[@value='Google Search'][1]
	//button[@data-testid='royal_login_button']  FB
	
	//tagname[contains(@attribute,'value')]
	//tagname[starts-with(@attribute,'value')]
	//tagname[ends-with(@attribute,'value')]
	//tagname[X-Path expression1 or X-Path expression2]
	//example //input[@value='Log in' or @type='submit']
	
	//tagname[X-Path expression1 and X-Path expression2]
	//example button on FB //input[@type='submit' and @id='u_0_19']
	
	//tagname[text()='text_value']
	//div[text()='Recent login']
	
	WebDriver driver = null;
	BrowserDriver obj = new BrowserDriver();

	@BeforeTest
	public void beforeTest() throws IOException {

		FileReader reader = new FileReader("config.properties");
		Properties p = new Properties();
		p.load(reader);
		driver = obj.getDriver(p.getProperty("Browser"));
		//obj.navigateTo(p.getProperty("url"));
		obj.navigateTo("https://www.flipkart.com/");

	}
	@Test
	public void search() throws InterruptedException
	{
		WebElement searchBox=null;
		searchBox = driver.findElement(By.xpath("//input[contains(@title,'Search for products')]"));
		searchBox.sendKeys("mobile");
		searchBox.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
	}
	@AfterTest
	public void afterTest() {
		driver.close();
	}

}
