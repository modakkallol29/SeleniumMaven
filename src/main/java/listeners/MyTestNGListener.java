package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyTestNGListener implements ITestListener {
	
	public void onTestFailure(ITestResult results)
	{
		System.out.println("====**********Test Failed**********====");
		System.out.println("Name : "+ results.getName());
		//System.out.println("Context of test 'User name' :" + results.getTestContext().getAttribute("UserName"));
		System.out.println("Throwable error message that was generated "+results.getThrowable().getMessage());
		System.out.println("Get status "+ results.getStatus()); 
	}
	public void onTestSuccess(ITestResult results)
	{
		System.out.println("+++++Test Passed ++++++++  " +results.getName());
	}
}
