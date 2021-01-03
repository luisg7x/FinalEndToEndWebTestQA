package finaltc;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.HomeObjects;
import resources.Base;

public class AddingMultipleISingleItemsToCart extends Base{
	
	public WebDriver driver;
	
	@BeforeTest
	public void getConnection() throws Exception {
		this.driver = initializerDriver();
		driver.get(proper.getProperty("urlHome"));
	}
	
	@Test
	public void addingMultipleIOneItemsToCart() throws SQLException, Exception {
		HomeObjects home = new HomeObjects(driver);
		
		String products[][] = getProducts();
		
		for (int i = 0; i < home.allProducts().size(); i++) {
			String productName = home.allProducts().get(i).getText().split("-")[0].trim(); 
			
			
			for (int a = 0; a<products.length; a++ ) {
				
				if (products[a][0].equalsIgnoreCase((productName))) {

					home.allAddButtons().get(i).click();;

				}
				
			}
		}
		
		home.ShoppingCartButton().click();
		
		//verify if all items added correct
		for (int a = 0; a<products.length; a++ ) {
			
			Assert.assertTrue(home.productExistInShoppingCart(products[a][0]));
			
		}	
		
	}
	
	@AfterTest
	public void close()  {
		driver.close();
	}
}
