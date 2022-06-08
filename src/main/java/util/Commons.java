package util;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Commons {
	/*
	 * https://yandex.com/ ========= 
	 * https://pixabay.com/
	 * https://opensource-demo.orangehrmlive.com/
	 */
	WebDriverWait wait = null;

	public WebElement getWebElement(String path, String Type, WebDriver driver) {
		WebElement webEelement = null;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		FluentWait fluentWait = new FluentWait(driver);
		fluentWait.withTimeout(Duration.ofSeconds(10));
		fluentWait.pollingEvery(Duration.ofMillis(500));
		Type = Type.toUpperCase();
		switch (Type) {
		case "XPATH":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));
	        // Scrolling down the page till the element is found		
			webEelement = driver.findElement(By.xpath(path));
	        js.executeScript("arguments[0].scrollIntoViewIfNeeded();", webEelement);
			break;
		case "ID":
			fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(path)));
			webEelement = driver.findElement(By.id(path));
			js.executeScript("arguments[0].scrollIntoView(true);", webEelement);
			break;
		case "NAME":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(path)));
			webEelement = driver.findElement(By.name(path));
			js.executeScript("arguments[0].scrollIntoView(true);", webEelement);
			break;
		}
		return webEelement;
	}

}
