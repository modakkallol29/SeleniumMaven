package dataProvidesPackage;

import java.lang.reflect.Method;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class DataProviderSample {

	/*
	 * https://yandex.com/ ========= 
	 * https://pixabay.com/
	 * https://opensource-demo.orangehrmlive.com/
	 */
	
	@DataProvider
	public static Object[][] getData(Method m) {
		// Method m can be used to know which Test is calling this data provider
		System.out.println("Name of Test that called this data provider " + m.getName());
		Object[][] data = null;
		data = new Object[2][2];
		// row 1
		data[0][0] = "https://www.yahoo.com/";
		data[0][1] = "Chrome";

		// row 2
		data[1][0] = "https://www.google.com/";
		data[1][1] = "Chrome";

		return data;
	}
}
