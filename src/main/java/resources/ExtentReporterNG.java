package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	static ExtentReports extend;
	
	//converting to static, for call the method without create object for do that, the variables shloud be static too
	public static ExtentReports getReportObject() {
		//creaty propertie to generate file on the location of the project
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		//settings customs configs (opcional)
		reporter.config().setReportName("Custom Name test Automatitation");
		reporter.config().setDocumentTitle("Custom tittle test results");
				
		//resposable create and consolidate all testcases execution 
	    extend = new ExtentReports();
	    extend.attachReporter(reporter);
	    extend.setSystemInfo("Tester", "Luis Gabriel");
		
		return extend;
	}
}
