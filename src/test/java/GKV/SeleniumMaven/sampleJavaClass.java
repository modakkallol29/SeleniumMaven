package GKV.SeleniumMaven;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class sampleJavaClass {

	public static void main(String[] args) throws InterruptedException {

		/*
		 * https://yandex.com/ ========= 
		 * https://pixabay.com/
		 * https://opensource-demo.orangehrmlive.com/
		 */
		
		//System.setProperty("webdriver.chrome.driver", "C:\\Selenium Driver\\chromedriver_win32\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
		driver.get("https://www.google.com/");
		Thread.sleep(8000);
		driver.close();
	}

}
