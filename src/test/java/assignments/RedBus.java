package assignments;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.*;
import util.*;

public class RedBus {

	WebDriver driver = null;
	BrowserDriver obj = new BrowserDriver();
	Commons commonsObj = new Commons();
	WebDriverWait wait = null;

	@BeforeTest
	public void initialize() throws IOException {
		FileReader reader = new FileReader("config.properties");
		Properties p = new Properties();
		p.load(reader);
		driver = obj.getDriver(p.getProperty("Browser").toString());

	}

	@Test(priority = 1)
	public void searchBus(ITestContext context) throws InterruptedException {
		WebElement fromCity, fromAutoSelect, toCity, toAutoSelect, selectCurrectDate, searchButton, modify, selectDate,
				dateSelector, searchButtonModify, viewSeat = null;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		String date;
		int newDate;
		obj.navigateTo("https://www.redbus.in/");
		fromCity = commonsObj.getWebElement("//input[@data-message='Please enter a source city']", "Xpath", driver);
		fromCity.sendKeys("Dehradun");
		fromAutoSelect = commonsObj.getWebElement(
				"//input[@data-message='Please enter a source city']//parent::div//li[text()='Dehradun']", "Xpath",
				driver);
		fromAutoSelect.click();
		toCity = commonsObj.getWebElement("//input[@data-message='Please enter a destination city']", "Xpath", driver);
		toCity.sendKeys("Delhi");
		toAutoSelect = commonsObj.getWebElement(
				"//input[@data-message='Please enter a destination city']//parent::div//li[contains(text(), 'Kashmiri Gate')]",
				"Xpath", driver);
		toAutoSelect.click();
		selectCurrectDate = commonsObj.getWebElement("//td[@class='current day']", "xPath", driver);
		selectCurrectDate.click();
		searchButton = commonsObj.getWebElement("search_btn", "Id", driver);
		searchButton.click();

		modify = commonsObj.getWebElement("//div[text()='Modify']", "Xpath", driver);
		modify.click();
		selectDate = driver.findElement(By.xpath("//input[@id='onward_cal']"));
		date = selectDate.getAttribute("value").toString();
		selectDate.click();
		dateSelector = commonsObj.getWebElement("//div[@id='rb-calendar']  ", "xpath", driver);
		newDate = Integer.parseInt(date.substring(0, 2)) + 2;
		List<WebElement> days = dateSelector
				.findElements(By.xpath("//div[@id='rb-calmiddle']//child::ul[@class='rb-calendar-days']//li"));
		for (WebElement d : days) {
			if (d.getText().equals(String.valueOf(newDate))) {
				d.click();
				break;
			}
		}
		searchButtonModify = commonsObj.getWebElement("//button[text()='SEARCH']", "Xpath", driver);
		searchButtonModify.click();
		
//		if(driver.findElement(By.xpath("//span[text()='Ok, got it']")) != null) {
//			
//			driver.findElement(By.xpath("//span[text()='Ok, got it']")).click();
//		}
		viewSeat = commonsObj.getWebElement("//ul[@class='bus-items']//li[@id='12456137']//div [text()='View Seats']", "Xpath", driver);
		viewSeat.click();
		
		Thread.sleep(6000);

		/*
		 * To select specif date WebElement dateWidget = driver.findElement(your
		 * locator); List<WebElement> columns=dateWidget.findElements(By.tagName("td"));
		 * 
		 * for (WebElement cell: columns){ //Select 13th Date
		 * if(cell.getText().equals("13")) {
		 * cell.findElement(By.linkText("13")).click(); break; }
		 */

	}

	@Test
	public void linksOnFacebookPage() {
		WebElement footerSection = null;
		int count = 0;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		obj.navigateTo("https://www.facebook.com/");
		footerSection = driver.findElement(By.xpath("//div[@data-testid='page_footer']"));
		List<WebElement> anchor = footerSection.findElements(By.tagName("a"));
		for (WebElement anchorTags : anchor) {
			System.out.println("Link text  " + anchorTags.getText());
			System.out.println("Title text  " + anchorTags.getAttribute("title"));
			count++;
		}
		System.out.println("==========Total links in footer=========  " + count);
	}

	@AfterTest
	public void closeBowser() {
		obj.closeBrowser();
	}

}
