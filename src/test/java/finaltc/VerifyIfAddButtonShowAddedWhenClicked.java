package finaltc;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.HomeObjects;
import resources.Base;

public class VerifyIfAddButtonShowAddedWhenClicked extends Base{

public WebDriver driver;
	
	@BeforeTest
	public void getConnection() throws Exception {
		this.driver = initializerDriver();
		driver.get(proper.getProperty("urlHome"));
	}
	
	@Test
	public void verifyAddedTextOnAddedButton() throws SQLException {
		HomeObjects home = new HomeObjects(driver);
		
		String product = getFirstProduct()[0];
		home.products(product)[0].click();
		
		Assert.assertTrue(home.products(product)[0].getText().contains("ADDED"));
	
	}
	
	@AfterTest
	public void close()  {
		driver.close();
	}
}
