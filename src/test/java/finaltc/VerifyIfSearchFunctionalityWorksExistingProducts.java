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

public class VerifyIfSearchFunctionalityWorksExistingProducts extends Base {
	
	public WebDriver driver;
	
	@BeforeTest
	public void getConnection() throws Exception {
		this.driver = initializerDriver();
		driver.get(proper.getProperty("urlHome"));
	}
	
	@DataProvider
	public Object[][] dataProvider() throws IOException {
		DataDriven d=new DataDriven();
		ArrayList data = d.getData("searchPositive","testdata");
		
		//-1 because count the column name
		int count = data.size()-1;

		Object[][] dataSend = new Object[count][1];
		
		//removing column name, also can do that with the name
		data.remove(0);
		
		for(int i = 0; i < count; i++) {

			dataSend[i][0] = data.get(i);
			
		}
		
		/*dataSend[0][0] = data.get(1);
		dataSend[1][0] = data.get(2);
		dataSend[2][0] = data.get(3);
		dataSend[3][0] = data.get(4);
		dataSend[4][0] = data.get(5);*/
		
		return dataSend;
	}
	
	@Test(dataProvider="dataProvider")
	public void verifyIfSearchFunctionalityWorksExistingProducts(String search) throws InterruptedException {
			
			HomeObjects home = new HomeObjects(driver);
			
			Thread.sleep(1500);
			
			List<WebElement> productsHome = home.allProducts();
			
			int allItemsMatchedShowedHome = 0;
			for(int i = 0; i<productsHome.size(); i++) {
				
				if (productsHome.get(i).getText().toLowerCase().contains(search.toLowerCase())) {
					allItemsMatchedShowedHome ++;
				}
				
			}
			
			home.searchBox().sendKeys(search);
			home.searchButton().click();
			
			Thread.sleep(1500);
			
			List<WebElement> products = home.allProducts();
			
			int findedAfterSearch = 0;
			for(int i = 0; i<products.size(); i++) {
				
				if (products.get(i).getText().toLowerCase().contains(search.toLowerCase())) {
					findedAfterSearch ++;
				}
				
			}
			
			for (int i = 0; i<search.length(); i++) {
				home.searchBox().sendKeys(Keys.BACK_SPACE);
			}
			
			Assert.assertEquals(findedAfterSearch, allItemsMatchedShowedHome);
			
			//System.out.println(findedAfterSearch + " " +  allItemsMatchedShowedHome);
		
		//Actions ac = new Actions(driver);
		//ac.doubleClick(home.searchBox()).perform();
		//home.searchBox().sendKeys(Keys.BACK_SPACE);
		//ac.moveToElement(home.searchBox()).doubleClick().sendKeys(Keys.BACK_SPACE).build().perform();
		
	}
	
	@AfterTest
	public void close()  {
		driver.close();
	}

}
