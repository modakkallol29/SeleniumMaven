package assignments;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.openxml4j.opc.internal.ZipContentTypeManager;
import org.hamcrest.DiagnosingMatcher;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import com.github.dockerjava.api.model.Driver;

import util.*;

public class naukriDay54 {
	Commons commonObj = new Commons();
	BrowserDriver obj = new BrowserDriver();
	WebDriverWait wait = null;
	WebDriver driver = null;

	@BeforeTest
	public void initialize() {
		driver = obj.getDriver("Chrome");
		obj.navigateTo("https://www.naukri.com/");
	}

	//@Test
	public void handleWindows() throws InterruptedException {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement jobsNav, companiesNav, servicesNav = null;
		Set<String> openTabs = null;
		Iterator<String> it = null;
		String parentWindow, childWindow = null;
		parentWindow = driver.getWindowHandle();
		jobsNav = commonObj.getWebElement("//div[text()='Jobs']", "xpath", driver);
		jobsNav.click();
		companiesNav = commonObj.getWebElement("//div[text()='Companies']", "Xpath", driver);
		companiesNav.click();
		servicesNav = commonObj.getWebElement("//div[text()='Services']", "xpath", driver);
		servicesNav.click();
		openTabs = driver.getWindowHandles();
		it = openTabs.iterator();
		while (it.hasNext()) {
			childWindow = it.next();
			if (parentWindow != childWindow) {
				driver.switchTo().window(childWindow);
				Thread.sleep(1000);

			}
			System.out.println("Title :  " + driver.getTitle() + "    URL  :  " + driver.getCurrentUrl());
		}

		Thread.sleep(1000);
	}

	//@Test
	public void salesForceTabs() throws InterruptedException {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		obj.navigateTo("https://www.salesforce.com/");
		Actions actionObj = new Actions(driver);
		WebElement productNav, partnerEcoSystem, resourcesNav, analystReport, dreamforce, blog = null;
		SoftAssert softAssert = new SoftAssert();
		String parentWindow, childWindow =null;
		Set<String> opensTabs =null;
		parentWindow = driver.getWindowHandle();
		productNav = commonObj.getWebElement("//li[@id='products_menu_item']//span[text()='Products']", "xpath", driver);
		actionObj.moveToElement(productNav).perform();

		partnerEcoSystem = commonObj.getWebElement("//li[@id='products_menu_item']//span[text()='Partner Ecosystem ']//..", "xpath", driver);
        actionObj.moveToElement(partnerEcoSystem).perform();
		partnerEcoSystem.click();
		driver.switchTo().window(parentWindow);
		assertEquals(driver.getCurrentUrl(), "https://www.salesforce.com/in/?ir=1");
		
		resourcesNav = commonObj.getWebElement("//li[@id='solutions_menu_item']//span[text()='Resources']","xpath", driver);
		actionObj.moveToElement(resourcesNav).perform();
		
		analystReport = commonObj.getWebElement("//li[@id='solutions_menu_item']//a[@href='/company/recognition/analyst-reports/']", "xpath", driver);
		actionObj.moveToElement(analystReport).perform();
		analystReport.click();
		
		driver.switchTo().window(parentWindow);
		blog = commonObj.getWebElement("//li[@id='solutions_menu_item']//a[@href='https://www.salesforce.com/in/blog']", "xpath", driver);
		actionObj.moveToElement(resourcesNav).perform();
		actionObj.moveToElement(blog).perform();
		actionObj.moveToElement(blog).click();
		
		driver.switchTo().window(parentWindow);
		dreamforce =commonObj.getWebElement("//li[@id='solutions_menu_item']//a[@href='https://www.salesforce.com/dreamforce/']", "Xpath", driver);
		actionObj.moveToElement(resourcesNav).perform();
		actionObj.moveToElement(dreamforce).perform();
		actionObj.moveToElement(dreamforce).click();
		

		opensTabs = driver.getWindowHandles();
		for (String tabs : opensTabs)
		{
			if (parentWindow!=tabs)
			{
				driver.switchTo().window(tabs);
				System.out.println(" Title of Page  :  "+driver.getTitle() );
				System.out.println(" URL  :  "+driver.getCurrentUrl());
			}
		}
		
			Thread.sleep(5000);

	}
	
	@Test
	public void day55Saska() throws InterruptedException
	{
		obj.navigateTo("https://www.sakraworldhospital.com/request-appointment");
		String parentWin, temp =null;
		ArrayList tabs = null;
		WebElement drSadiq, drManoj,drManojDetails,drShilpi,drShilpiDetails, virtualConsutationRadio,consultationBtn =null;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		parentWin = driver.getWindowHandle();
		drShilpi = commonObj.getWebElement("//a[contains(@href,4995) and text()='Dr. Shilpi Saraswat']", "Xpath", driver);
		drShilpi.click();
		tabs = new ArrayList(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1).toString()); //switch to newly opened tab
		drShilpiDetails = driver.findElement(By.xpath("//div[@class='align-self-center slot-doctor-details']//label[@title='Dr. Shilpi Saraswat']"));
		temp = drShilpiDetails.getText();
		System.out.println("Value =  "+temp);
		driver.switchTo().window(parentWin);
		js.executeScript("window.scrollBy(0,50)", "");
		drManoj = commonObj.getWebElement("//a[contains(@href,4781) and text()='Dr. Manoj Kumar R']", "Xpath", driver);
		drManoj.click();
		tabs=null;
		tabs = new ArrayList(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(2).toString());
		System.out.println(driver.getCurrentUrl());
		drManojDetails = commonObj.getWebElement("//div[@class='align-self-center slot-doctor-details']//label[@title='Dr. Manoj Kumar R']", "Xpath", driver);
		System.out.println(drManojDetails.getText());
		virtualConsutationRadio = commonObj.getWebElement("//label[@for='inlineRadio2']", "Xpath", driver);
		wait.until(ExpectedConditions.visibilityOf(virtualConsutationRadio));
		//Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(virtualConsutationRadio)).click();
//		Thread.sleep(3000);
		//virtualConsutationRadio.click();

		driver.switchTo().window(parentWin);
		js.executeScript("window.scrollBy(0,-50)", "");
		//div//h4[text()=' Department of Gastrointestinal Surgery and Liver Transplantation']//following-sibling::a[@data-toggle='modal' and text()='Dr. Sadiq Saleem Sikora']
		drSadiq = commonObj.getWebElement("//div//h4[contains(text(),' Gastrointestinal')]//following-sibling::a[@data-toggle='modal' and text()='Dr. Sadiq Saleem Sikora']", "Xpath", driver);
		drSadiq.click();
		consultationBtn = commonObj.getWebElement("//div[@id='hv-vc-appointment-pop-up20']//a[text()='Consultation']", "xpath", driver);
		consultationBtn.click();
		
		Thread.sleep(3000);

		
	}
	@AfterTest
	public void close() {
		obj.closeBrowser();
		obj.quitBrowser();
	}
}
