package finaltc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomeObjects;
import resources.Base;
import resources.DataDriven;

public class VerifyIfSearchFunctionalityWorksNonExistingProducts extends Base {
	
	public WebDriver driver;
	
	@BeforeTest
	public void getConnection() throws Exception {
		this.driver = initializerDriver();
		driver.get(proper.getProperty("urlHome"));
	}
	
	@DataProvider
	public Object[][] dataProvider() throws IOException {
		DataDriven d=new DataDriven();
		ArrayList data = d.getData("searchNegative","testdata");
		
		//-1 because count the column name
		int count = data.size()-1;

		Object[][] dataSend = new Object[count][1];
		
		//removing column name, also can do that with the name
		data.remove(0);
		
		for(int i = 0; i < count; i++) {

			dataSend[i][0] = data.get(i);
			
		}
		
		return dataSend;
	}
	
	@Test(dataProvider="dataProvider")
	public void verifyIfSearchFunctionalityWorksNonExistingProducts(String search) throws InterruptedException {
			
			HomeObjects home = new HomeObjects(driver);
			
			home.searchBox().sendKeys(search);
			home.searchButton().click();
			
			Thread.sleep(1500);
			
			Assert.assertTrue(home.notMatchedSearchMessage().isDisplayed());
			
			for (int i = 0; i<search.length(); i++) {
				home.searchBox().sendKeys(Keys.BACK_SPACE);
			}

	}
	
	@AfterTest
	public void close()  {
		driver.close();
	}

}
