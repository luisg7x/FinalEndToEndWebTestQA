package finaltc;

import java.sql.SQLException;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.HomeObjects;
import resources.Base;

public class ValidateAddingSingleItemShoppingCart extends Base{
	
	public WebDriver driver;
	
	@BeforeTest
	public void getConnection() throws Exception {
		this.driver = initializerDriver();
		driver.get(proper.getProperty("urlHome"));
	}
	
	@Test
	public void AddingProductToCart() throws SQLException {
		HomeObjects home = new HomeObjects(driver);
		
		String product = getFirstProduct()[0];
		home.products(product)[0].click();
		
		home.ShoppingCartButton().click();
		home.FindItemShippingCart(product);

		Assert.assertFalse(home.FindItemShippingCart(product).isEmpty());
	}
	
	@AfterTest
	public void close()  {
		driver.close();
	}
}
