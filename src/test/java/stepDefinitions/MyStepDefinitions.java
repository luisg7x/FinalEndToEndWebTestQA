package stepDefinitions;

import java.util.List;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import pageObjects.HomeObjects;


@RunWith(Cucumber.class)
public class MyStepDefinitions {

	private WebDriver driver;
	private HomeObjects ho;
	private String[][] items;
	private static int priceMultipleItems;
	
	@Given("^User is on the home page$")
    public void user_is_on_the_home_page() throws Throwable {
		System.setProperty("webdriver.edge.driver", "C:\\Users\\luisg\\Desktop\\Selenium\\msedgedriver.exe");
		driver = new EdgeDriver();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
    }

    @When("^User add \"([^\"]*)\" to shopping cart$")
    public void user_add_something_to_shopping_cart(String strArg1) throws Throwable {
    	//items[0][0] = strArg1;
    	Thread.sleep(5500);
    	 	ho = new HomeObjects(driver);
    		ho.products(strArg1)[0].click();
    
    }

    @When("^User add \"([^\"]*)\" \"([^\"]*)\" to shopping cart$")
    public void user_add_something_something_to_shopping_cart(String quantity, String item) throws Throwable {
    	try {
    		WebElement[] items = ho.products(item);
    		for (int i = 0; i<Integer.parseInt(quantity); i++) {
    			items[1].click();
    		}
    		items[0].click();
    	}catch(Exception e) {
    		Assert.assertNull("Item no finded" + e, null);
    	}
    }

    @When("^User add \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" to shopping cart$")
    public void user_add_something_something_something_something_to_shopping_cart(String it1, String it2, String it3, String it4) throws Throwable {
    	items[0][1] = it1;
    	items[0][2] = it2;
    	items[0][3] = it3;
    	items[0][4] = it4;
    	
    	try {
    		ho.products(it1)[0].click();
    		ho.products(it2)[0].click();
    		ho.products(it3)[0].click();
    		ho.products(it4)[0].click();
    	}catch(Exception e) {
    		Assert.assertNull("Item no finded" + e, null);
    	}	
    }

    @When("^User add (.+) (.+) to shopping cart$")
    public void user_add_to_shopping_cart(String item, String quantity) throws Throwable {
    	
    	try {
    		WebElement[] items = ho.products(item);
    		for (int i = 0; i<Integer.parseInt(quantity); i++) {
    			items[1].click();
    		}
    		items[0].click();
    		priceMultipleItems += Integer.parseInt(quantity) * Integer.parseInt(items[3].getText());
    	}catch(Exception e) {
    		Assert.assertNull("Item no finded" + e, null);
    	}
    }

    @Then("^Shopping cart show only that item added$")
    public void shopping_cart_show_only_that_item_added() throws Throwable {
    	ho.ShoppingCartButton().click();
       Assert.assertFalse(ho.FindItemShippingCart(items[0][0]).isEmpty());
    }

    @Then("^Shopping cart show that items added$")
    public void shopping_cart_show_that_items_added() throws Throwable {
    	ho.ShoppingCartButton().click();
    	Assert.assertFalse(ho.FindItemShippingCart(items[0][1]).isEmpty());
    	Assert.assertFalse(ho.FindItemShippingCart(items[0][2]).isEmpty());
    	Assert.assertFalse(ho.FindItemShippingCart(items[0][3]).isEmpty());
    	Assert.assertFalse(ho.FindItemShippingCart(items[0][4]).isEmpty());
    }

    @Then("^Shopping cart show the correct sum of the item in the price $")
    public void shopping_cart_show_the_correct_sum_of_the_item_in_the_price() throws Throwable {
    	ho.ShoppingCartButton().click();
    	Assert.assertEquals(Integer.parseInt(ho.TotalPriceHomePage().getText()), priceMultipleItems);
    }

    @Then("^Shopping cart show message \"([^\"]*)\"$")
    public void shopping_cart_show_message_something(String strArg1) throws Throwable {
    	ho.ShoppingCartButton().click();
    	Assert.assertTrue(ho.emptyCartMessage().getText().contains(strArg1));
    }

    @And("^That button for add items show text ADDED$")
    public void that_button_for_add_items_show_text_added() throws Throwable {
       
    }

    @And("^User refresh website$")
    public void user_refresh_website() throws Throwable {
        driver.navigate().refresh();
    }

    @And("^User open the shopping cart and click clear buttom on all items$")
    public void user_open_the_shopping_cart_and_click_clear_buttom_on_all_items() throws Throwable {
    	ho.ShoppingCartButton().click();
    	for (int i = 0; i < ho.getAllItemShippingCart().size(); i++) {
			 ho.productsRemove().get(i).click();
    	}
    }

    @And("^Button to proceed to checkout is disabled$")
    public void button_to_proceed_to_checkout_is_disabled() throws Throwable {
        Assert.assertFalse(ho.checkoutButton().isEnabled());
    }

}
