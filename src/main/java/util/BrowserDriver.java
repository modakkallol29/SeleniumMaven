package util;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserDriver {

	private static WebDriver driver = null;

	public WebDriver getDriver(String browserName) {

		String name = browserName.toUpperCase();
		switch (name) {
		case "CHROME":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
//			Dimension d = new Dimension(300,1080);
//			driver.manage().window().setSize(d);
			break;

		case "EDGE":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		case "FIREFOX":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	public void navigateTo(String url)
	{
		driver.get(url);
	}
	public void closeBrowser()
	{
		driver.close();
	}
	public void quitBrowser()
	{
		driver.quit();
	}
}
