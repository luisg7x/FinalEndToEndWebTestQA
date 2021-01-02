package pageObjects;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomeObjects {
	
	WebDriver driver;
	

	public HomeObjects (WebDriver driver) {
	
		this.driver=driver;
	}
	
	private By items = By.cssSelector("h4.product-name");
	private By addButtoms = By.xpath("//div[@class='product-action']/button");
	private By plusButtons = By.cssSelector("a.increment");
	private By priceText = By.cssSelector("p.product-price");
	private By ShoppingCartButton = By.cssSelector("img[alt='Cart']");
	private By AllItemsShoppingCart = By.cssSelector("div.cart-preview.active div div ul.cart-items li.cart-item div.product-info  p.product-name");
	private By TotalPriceHomePage = By.xpath("//tbody/tr[2]/td[3]/strong");
	private By emptyCartMessage = By.cssSelector("div.cart-preview.active div div div.empty-cart h2");
	private By productsRemove = By.cssSelector("a.product-remove");
	private By checkoutButton = By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]");
	
	public WebElement[] products(String item) {
		
		//List<WebElement> finded = (List<WebElement>) this.items.stream().flatMap(f-> items.stream().map(m -> f.getText())).collect(Collectors.toList());
		List<WebElement> list = driver.findElements(items);
		WebElement element[] = new WebElement[2];
		for (int i = 0; i < list.size(); i++) {
			String[] productName = list.get(i).getText().split("-"); 
			
			if (item.equalsIgnoreCase((productName[0].trim()))) {
				element[0] = driver.findElements(addButtoms).get(i);
				element[1] = driver.findElements(plusButtons).get(i);
				element[3] = driver.findElements(priceText).get(i);
				break;
			}

		}
		
		return element;
	}
	
	public WebElement ShoppingCartButton() {
		return driver.findElement(ShoppingCartButton);
	}
	
	public List<WebElement> FindItemShippingCart(String value) {
		return driver.findElements(AllItemsShoppingCart).stream().filter(p->p.getText().equalsIgnoreCase(value)).collect(Collectors.toList());
	}
	
	public List<WebElement> getAllItemShippingCart() {
		return driver.findElements(AllItemsShoppingCart);
	}
	
	public WebElement TotalPriceHomePage() {
		return driver.findElement(TotalPriceHomePage);
	}
	
	public WebElement emptyCartMessage() {
		return driver.findElement(emptyCartMessage);
	}
	
	public List<WebElement> productsRemove() {
		return driver.findElements(productsRemove);
	}
	
	public WebElement checkoutButton() {
		return driver.findElement(checkoutButton);
	}
	
}
