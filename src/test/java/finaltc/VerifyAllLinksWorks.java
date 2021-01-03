package finaltc;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.HomeObjects;
import resources.Base;

public class VerifyAllLinksWorks extends Base{
	
	public WebDriver driver;
	
	@BeforeTest
	public void getConnection() throws Exception {
		this.driver = initializerDriver();
		driver.get(proper.getProperty("urlHome"));
	}
	
	@Test
	public void verifyAllLinksWorks() throws MalformedURLException, IOException  {
		HomeObjects home = new HomeObjects(driver);
		

		
		SoftAssert sa = new SoftAssert();
		
		//for like on swift.. for recipe in recipes{}
		for(WebElement link : home.allHrefNavegables()) {	
			String url = link.getAttribute("href");
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setRequestMethod("HEAD");
			conn.connect();
			int respCode = conn.getResponseCode(); 
			sa.assertTrue(respCode<400, "The link with text: " + link.getText() + " with status: " + respCode +" failed: " + url);
		
		}
		sa.assertAll();
		
	}
	
	@AfterTest
	public void close()  {
		driver.close();
	}
}
