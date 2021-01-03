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

public class VerifyCheckoutButtonDisabledWhenCarEmpty extends Base{
	
	public WebDriver driver;
	
	@BeforeTest
	public void getConnection() throws Exception {
		this.driver = initializerDriver();
		driver.get(proper.getProperty("urlHome"));
	}
	
	@Test
	public void verifyCheckoutButtonDisabledWhenCarEmpty() throws SQLException, Exception {
		HomeObjects home = new HomeObjects(driver);
		
		int itemCountCart = home.getAllItemShippingCart().size();
		
		if (itemCountCart>1) {
			
			for (int i = 0; i < itemCountCart; i++) {
				home.productsRemove().get(i).click();
			}
		}
		
		home.ShoppingCartButton().click();
		
		Assert.assertFalse(home.checkoutButton().isEnabled());
		
	}
	
	@AfterTest
	public void close()  {
		driver.close();
	}
}
