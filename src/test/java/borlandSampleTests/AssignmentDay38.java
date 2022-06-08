package borlandSampleTests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import util.BrowserDriver;
import util.ExcelRead;

public class AssignmentDay38 {
	//tagname[@attribute='value']
	WebDriver driver = null;
	BrowserDriver obj = new BrowserDriver();

	@BeforeTest
	public void beforeTest(ITestContext contextObj) throws IOException {

		FileReader reader = new FileReader("config.properties");
		Properties p = new Properties();
		p.load(reader);
		driver = obj.getDriver(p.getProperty("Browser"));
		obj.navigateTo(p.getProperty("url"));
		contextObj.setAttribute("url",p.getProperty("url").toString() );

	}

	@Test
	public void placeOrder(ITestContext contextObj) throws InterruptedException, IOException {
		HashMap<Integer, List<String>> rows;
		List<String> row;
		
		rows = ExcelRead.readRowsTest("C:\\Selenium Workspace\\SeleniumMaven\\Billing Info.xlsx");
		for (Map.Entry<Integer, List<String>> e : rows.entrySet()) {
			
			row = e.getValue();
		
		if (row!=null) {
			driver.findElement(By.name("bSubmit")).click();
			Thread.sleep(2000);
			System.out.println("Title of page : "+driver.getTitle());
			driver.findElement(By.name("QTY_TENTS")).clear(); //clear 0 from text box
			driver.findElement(By.name("QTY_TENTS")).sendKeys("5");
			Thread.sleep(2000);
			driver.findElement(By.name("bSubmit")).click();
			Thread.sleep(2000);
			System.out.println("Title of page : "+driver.getTitle());
			driver.findElement(By.xpath("//input[@value='Proceed With Order']")).click();
			Thread.sleep(2000);
			
			driver.findElement(By.name("billName")).sendKeys(row.get(0));
			Thread.sleep(2000);
			obj.navigateTo(contextObj.getAttribute("url").toString());
			Thread.sleep(2000);
		}
		
		
		}
		
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

}
