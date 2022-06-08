package GKV.SeleniumMaven;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.TestNG;
import org.testng.annotations.*;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;
import dataProvidesPackage.*;
public class sampleTestNG2 {

	WebDriver driver;
	boolean flag = true;

	@BeforeTest 
	public void sampleBeforeTest() {
		System.out.println("Before Test was run before all the @test are executed");
		driver = initiateDriver();
	}

	@AfterTest
	public void sampleAfterTest() {
		System.out.println("After Test was run after all @Test are executed");
		driver.close();
		System.out.println("After Test was run line 30");
	}

	@BeforeMethod
	public void sampleBeforeMethod() {
		System.out.println("This before  method will run before EACH test");
	}

	@AfterMethod
	public void sampleAfterMethod() {
		System.out.println("This after method will run after EACH test");
	}

	@Test(priority = 1)
	public void sampleTest1() {

		driver.get("https://www.google.com/");
		System.out.println("Opened Google");
	}

	@Test(priority = 2, description = "This is a description for sampleTest2")
	public void sampleTest2() {
		driver.get("https://www.yahoo.com/");
		System.out.println("Opened Yahoo");
	}

	@Test(priority = 4, dataProviderClass = DataProviderSample.class, dataProvider = "getData")
	public void checkAssert(String a1, String b1) {
		System.out.println("Srting A ==="+a1);
		driver.get("https://www.yahoo.com/");
		System.out.println("Opened Yahoo");
		String a = "XYZ";
		String b = "XYZ";
		assertEquals(a, b);
	}

	@Test(priority = 3, description = "Test to check softassersion")
	public void checkSoftAssert() {
		driver.get("https://testng.org/doc/");
		SoftAssert softassert = new SoftAssert();
		softassert.assertEquals("AB", "DT");
		softassert.assertNotEquals("rt", "rt");
		softassert.assertEquals("This actual that is displayed on website", "This was expected",
				"This is error message that should display");
		softassert.assertEquals("FT", "FT");
		softassert.assertAll();

	}

	@Test(priority = 5, dependsOnMethods = "checkAssert")
	public void sampleSkipTestCase() {
		if (flag) {
			throw new SkipException("=====this test was skipped on pupose=====");
		}
		System.out.println("This test should have been skipped");
	}

	@Test(priority = 6, dependsOnMethods = { "checkAssert" })
	public void sampleCheckDependsOn() {
		System.out.println("This will only be executed if depends on is true/passed");
	}

	@Test(priority = 8)
	public void sampleIContext(ITestContext contextObj) {
		contextObj.setAttribute("UserName", "kallol29");
		System.out.println("Username passed in ITestContext " + contextObj.getAttribute("UserName"));
	}

	@Test(priority = 9)
	public void sampleIContext2(ITestContext contextObj) {
		contextObj.setAttribute("SearchText", "iPhone 12");
		System.out.println(
				"sampleIContext2 : Has : Username passed in ITestContext " + contextObj.getAttribute("UserName"));

	}

	@Test(priority = 10)
	public void sampleIContext3(ITestContext contextObj) {
		System.out.println(
				"sampleIContext3 : Has : Username passed in ITestContext " + contextObj.getAttribute("UserName"));
		String searchText = (String) contextObj.getAttribute("SearchText");
		System.out.println("sampleIContext3 : Has : Search Test passed in ITestContext " + searchText);
	}

	public WebDriver initiateDriver() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		return driver;
	}
}
