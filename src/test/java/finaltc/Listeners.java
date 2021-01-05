package finaltc;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import resources.Base;
import resources.ExtentReporterNG;

public class Listeners extends Base implements ITestListener {

	private ExtentReports extent = ExtentReporterNG.getReportObject();
	private ExtentTest test;
		//creating for run extent report on parallel, and avoid override with parallel, this ibject will store the objects and prevent the override
	private ThreadLocal /*asigni what class:*/ <ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());
	    //part of fixing parallel run, sending a object to the pool:
	    extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().pass("I successfully Pass: " + result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		//screenshot code if failed
		//response if API is failed
		
		//extent report
		extentTest.get().fail(result.getThrowable());
		
		WebDriver driver = null;
		//getting driver, you only replace word driver with other variable if you need get other and make sure that variable is public.. get(result.getInstance() reprensents the @test methods as instance
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			//replace all catches with Exception e
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//getting and sending test name:
		try {
			//extent report and taking a screenshot:
			
			extentTest.get().addScreenCaptureFromPath(getScreenShotPath(result.getMethod().getMethodName(), driver),result.getMethod().getMethodName());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().skip(result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		//notify that test is done
		extent.flush();
	}

}
