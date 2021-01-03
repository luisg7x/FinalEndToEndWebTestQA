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

public class ValidateAddingQuantityToTheItemShoppingCart extends Base{
	
	public WebDriver driver;
	
	@BeforeTest
	public void getConnection() throws Exception {
		this.driver = initializerDriver();
		driver.get(proper.getProperty("urlHome"));
	}
	
	@Test
	public void addingQuantityProductsToCart() throws SQLException {
		HomeObjects home = new HomeObjects(driver);
		
		String products[] = getFirstProduct();
		
		WebElement[] itemsProduct = home.products(products[0]);
		
		for (int i = 1; i < Integer.parseInt(products[1]); i ++) {
			itemsProduct[1].click();
		}

		itemsProduct[0].click();
		
		home.ShoppingCartButton().click();

		WebElement[] shopCart = home.actionAndValuesItemsShopCart(products[0]);
		
		Assert.assertEquals(products[1], shopCart[1].getText().split("N")[0].trim());
		
		Assert.assertFalse(home.FindItemShippingCart(products[0]).isEmpty());
	}
	
	@AfterTest
	public void close()  {
		driver.close();
	}
}
