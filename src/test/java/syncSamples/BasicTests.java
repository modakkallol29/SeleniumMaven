package syncSamples;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import util.BrowserDriver;

public class BasicTests {
	// a[text()='Sign in']
	// input[@id='text-auth-email']
	// input[@id='text-auth-password']
	// button[@id='btn-auth-login']
	// span[text()='asolanki@sync.com ']

	WebDriver driver = null;
	BrowserDriver obj = new BrowserDriver();

	@BeforeTest
	public void beforeTest(ITestContext context) throws IOException {

		FileReader reader = new FileReader("config.properties");
		Properties p = new Properties();
		p.load(reader);
		driver = obj.getDriver(p.getProperty("Browser"));
		obj.navigateTo(p.getProperty("sync_url"));
		context.setAttribute("username", p.getProperty("sync_username"));
		context.setAttribute("password", p.getProperty("sync_password"));
	}

	@Test
	public void loginTest(ITestContext context) throws InterruptedException {
		WebElement signIn, usernameTextBox, passwordTextBox, loginSubmitButton, settings, accountSettingsText,
				emailIDinNAV = null;
		String username = "// span[text()='" + context.getAttribute("username").toString() + ' ' + "']"; // needed to add extra space to find xpath
		SoftAssert softassert = new SoftAssert();
		signIn = driver.findElement(By.xpath("// a[text()='Sign in']"));
		signIn.click();
		Thread.sleep(3000);
		usernameTextBox = driver.findElement(By.id("text-auth-email"));
		usernameTextBox.sendKeys(context.getAttribute("username").toString());
		passwordTextBox = driver.findElement(By.id("text-auth-password"));
		passwordTextBox.sendKeys(context.getAttribute("password").toString());
		Thread.sleep(1000);
		loginSubmitButton = driver.findElement(By.id("btn-auth-login"));
		loginSubmitButton.click();
		Thread.sleep(8000);
		emailIDinNAV = driver.findElement(By.xpath(username));  
		softassert.assertEquals(emailIDinNAV.getText(), context.getAttribute("username").toString()); //verifying the username in NAV bar after login
		emailIDinNAV.click();
		Thread.sleep(1000);
		settings = driver.findElement(By.xpath("// a[text()='Settings']"));
		settings.click();
		Thread.sleep(3000);
		accountSettingsText = driver.findElement(By.xpath("// div[text()='Account settings']"));
		softassert.assertEquals(accountSettingsText.getText(), "Account settings");
		softassert.assertAll();
		Thread.sleep(3000);

	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

}
