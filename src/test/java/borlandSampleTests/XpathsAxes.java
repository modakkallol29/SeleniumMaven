package borlandSampleTests;
import util.*;
import util.BrowserDriver.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.TestNG.*;
import org.testng.annotations.*;



public class XpathsAxes {
	
	//tagname[@attribute='value']//parent::tagname
	//tagname[@attribute='value']//child::tagname
	//tagname[@attribute='value']//self::tagname  selects the current node itself
	/*
	 * https://yandex.com/ 
	 * =========
	 * https://pixabay.com/
	 * https://opensource-demo.orangehrmlive.com/
	 * //button[@type='submit']//following-sibling::input   (Search bar)
	 * 
	 * //button[@type='button']/div[text()='Images'] (image)
	 * //form[@method='get']//label[text()='Illustrations']  (Illustrations under dropdown)
	 * 
	 * //button[@type='button']//following-sibling::div//label[text()='Music']  (https://pixabay.com/illustrations/search/lion/)
	 * 
	 * Sync
	 * //li[@uisrefactive='active']//a[text()='Users']  NAV bar Users
	 * //a[text()='+ Add user'] "Add users" in Users page
	 * 
	 * //input[@id='multiuser-add-action-new']  Xpath to "I'd like to add new Sync users" in popup but Use ID
	 * //button[text()='Next'] Next button in popup
	 * 
	 * */
	WebDriver driver = null;
	WebDriverWait wait = null;
	BrowserDriver obj = new BrowserDriver();
	Commons commonObj = new Commons();
	@BeforeTest
	public void initialize(ITestContext context) throws IOException
	{
		FileReader reader = new FileReader("config.properties");
		Properties p = new Properties();
		p.load(reader);
		driver = obj.getDriver(p.getProperty("Browser").toString());	
		context.setAttribute("gmail_url", p.getProperty("gmail_url").toString());
		context.setAttribute("gmail_username", p.getProperty("gmail_username").toString());
		context.setAttribute("gmail_password", p.getProperty("gmail_password").toString());
		
		
	}
	@Test
	public void testPixabay() throws InterruptedException
	{
		WebElement soundEffectNAV, searchTextBoxSoundsPage, cartoonJumpPlayButton, exploreinNAV, exploreVideoNAV, searchBarVideoPage = null;
		obj.navigateTo("https://pixabay.com/");
		soundEffectNAV = driver.findElement(By.xpath("//div[contains(@class, 'navigationContainer')]//child::a[text()='Sound Effects']"));
		soundEffectNAV.click();
		Thread.sleep(2000);
		searchTextBoxSoundsPage =driver.findElement(By.xpath("//div[@class='audio-free-text-search-container']//child::input[@type='search']"));
		searchTextBoxSoundsPage.sendKeys("Funny");
		searchTextBoxSoundsPage.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		cartoonJumpPlayButton=driver.findElement(By.xpath("//a[@class='name' and text()='Cartoon jump']//parent::div//..//child::button[1]/span[1]"));
		/*
		 * Better xpath for cartoonJumpPlayButton
		 * //div[@data-track-id='6462']//button/span[1]  
		 */
		cartoonJumpPlayButton.click();
		Thread.sleep(2000);
		exploreinNAV = driver.findElement(By.xpath("//i[@class='dropdown_arrow hide-xs']//parent::a"));
		exploreinNAV.click();
		Thread.sleep(3000);
		exploreVideoNAV =driver.findElement(By.xpath("//li[contains(@class,'open')]//a[@href='/videos/search/']"));
		/* //li[contains(@class,'open')]//div[@id='mobile_menu_top']//following-sibling::div//a[text()='Videos']
		 * Better option for  exploreVideoNAV
		 * //li[contains(@class,'open')]//a[text()='Videos']
		*/
		exploreVideoNAV.click();
		Thread.sleep(2000);
		System.out.println( driver.getCurrentUrl());
		
		
	}
	@Test
	public void sendFromGmail(ITestContext context) throws InterruptedException
	{
		WebElement usernameText, nextButton, passwordText, nextButtonPassword , composeButton, toEmailID, subject, emailBody, sendButton =null;
		double randomText1;
		double randomText2;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		FluentWait fluentWait = new FluentWait(driver);
		fluentWait.withTimeout(Duration.ofSeconds(30));
		fluentWait.pollingEvery(Duration.ofSeconds(1));
		
		obj.navigateTo(context.getAttribute("gmail_url").toString());
		//Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@autocomplete='username']")) );
		usernameText = driver.findElement(By.xpath("//input[@autocomplete='username']"));
		usernameText.sendKeys(context.getAttribute("gmail_username").toString());		
		nextButton =driver.findElement(By.xpath("//span[text()='Next']"));
		nextButton.click();
		//Thread.sleep(2000);
		
//		passwordText = driver.findElement(By.xpath("//input[@type='password']"));
//		passwordText.sendKeys(context.getAttribute("gmail_password").toString());
//		//Thread.sleep(2000);
		passwordText = commonObj.getWebElement("//input[@type='password']", "Xpath", driver);  // Using functions
		passwordText.sendKeys(context.getAttribute("gmail_password").toString());
		nextButtonPassword = commonObj.getWebElement("//div[@id='passwordNext']", "Xpath", driver); //Using functions
		nextButtonPassword.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Compose' and @role='button']")));
		composeButton = driver.findElement(By.xpath("//div[text()='Compose' and @role='button']"));
		composeButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@aria-label='To']")));
		toEmailID = driver.findElement(By.xpath("//textarea[@aria-label='To']"));
		toEmailID.sendKeys("asolanki+user1@sync.com");
		subject = commonObj.getWebElement("//input[@name='subjectbox']", "Xpath", driver);  //using function
		randomText1 = Math.random();
		subject.sendKeys("Generated using selenuum"+randomText1);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Message Body']")));
		emailBody = driver.findElement(By.xpath("//div[@aria-label='Message Body']"));
		randomText2 = Math.random();
		emailBody.sendKeys("Message generted from selenium " +randomText2);
		sendButton = commonObj.getWebElement("//div[@role='button' and @data-tooltip='Send ‪(Ctrl-Enter)‬']", "Xpath", driver); //Using functions
		sendButton.click();
		

		String s = "//td[@role='gridcell']//div//div//span[contains(text(),"+randomText1+")]";
		fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(s)));
		Thread.sleep(10000);
	}
	@AfterTest
	public void closeBrowser()
	{
		obj.closeBrowser();
	}
	

}
