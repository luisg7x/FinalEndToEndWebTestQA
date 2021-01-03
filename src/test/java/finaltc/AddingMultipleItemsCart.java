package finaltc;

import java.sql.SQLException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.HomeObjects;
import resources.Base;

public class AddingMultipleItemsCart extends Base{
	
	public WebDriver driver;
	
	@BeforeTest
	public void getConnection() throws Exception {
		this.driver = initializerDriver();
		driver.get(proper.getProperty("urlHome"));
	}
	
	@Test
	public void addingMultipleMoreThanOneItemsCart() throws SQLException, Exception {
		HomeObjects home = new HomeObjects(driver);
		
		String products[][] = getProducts();
		
		for (int i = 0; i < home.allProducts().size(); i++) {
			String productName = home.allProducts().get(i).getText().split("-")[0].trim(); 
			
			
			for (int a = 0; a<products.length; a++ ) {
				
				if (products[a][0].equalsIgnoreCase((productName))) {
					
					for (int q = 1; q< Integer.parseInt(products[a][1]); q++) {
						home.allPlusButtons().get(i).click();
					}

					home.allAddButtons().get(i).click();;

				}
				
			}
		}
		
		home.ShoppingCartButton().click();
		
		//verify if all items with quantity are added correct
		for (int a = 0; a<products.length; a++ ) {
			
			WebElement[] shopCart = home.actionAndValuesItemsShopCart(products[a][0]);
			
			Assert.assertEquals(products[a][1], shopCart[1].getText().split("N")[0].trim());
			
		}	
		
	}
	
	@AfterTest
	public void close()  {
		driver.close();
	}
}
