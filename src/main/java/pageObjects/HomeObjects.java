package pageObjects;

import java.util.List;
import java.util.Optional;
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
	
	private By allProducts = By.cssSelector("h4.product-name");
	private By allAddButtons = By.xpath("//div[@class='product-action']/button");
	private By allPlusButtons = By.cssSelector("a.increment");
	private By priceText = By.cssSelector("p.product-price");
	private By ShoppingCartButton = By.cssSelector("img[alt='Cart']");
	private By allItemsShoppingCart = By.cssSelector("div.cart-preview.active div div ul.cart-items li.cart-item div.product-info p.product-name");
	private By TotalPriceHomePage = By.xpath("//tbody/tr[2]/td[3]/strong");
	private By emptyCartMessage = By.cssSelector("div.cart-preview.active div div div.empty-cart h2");
	private By checkoutButton = By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]");
	private By productsRemove = By.cssSelector("a.product-remove");
	private By quantitysByItemsShoppingCart = By.cssSelector("div.cart-preview.active div div ul.cart-items li.cart-item div.product-total p.quantity");
	private By allHrefNavegables = By.cssSelector("a[href*='/']");
	private By searchBox = By.cssSelector("input[type='search']");
	private By searchButton = By.cssSelector("button.search-button");
	private By notMatchedSearchMessage = By.xpath("//h2[contains(text(),'Sorry, no products matched your search!')]");
	
	public WebElement[] products(String item) {
		
		//List<WebElement> finded = (List<WebElement>) this.items.stream().flatMap(f-> items.stream().map(m -> f.getText())).collect(Collectors.toList());
		List<WebElement> list = driver.findElements(allProducts);
		WebElement element[] = new WebElement[4];
		for (int i = 0; i < list.size(); i++) {
			String[] productName = list.get(i).getText().split("-"); 
			
			if (item.equalsIgnoreCase((productName[0].trim()))) {
				element[0] = driver.findElements(allAddButtons).get(i);
				element[1] = driver.findElements(allPlusButtons).get(i);
				element[2] = driver.findElements(priceText).get(i);
				break;
			}

		}
		
		return element;
	}
	
	public WebElement ShoppingCartButton() {
		return driver.findElement(ShoppingCartButton);
	}
	//Gold
	public Optional<WebElement> FindItemShippingCart(String value) {
		return driver.findElements(allItemsShoppingCart).stream().filter(p->{
			String x = p.getText();
			x = x.split("-")[0].trim();
			return x.equalsIgnoreCase(value);
		}).findFirst();
	}
	//Gold
	public boolean productExistInShoppingCart(String value) {
		return driver.findElements(allItemsShoppingCart).stream().anyMatch(p->{
			String x = p.getText();
			x = x.split("-")[0].trim();
			return x.equalsIgnoreCase(value);
		});
	}
	
	public WebElement[] actionAndValuesItemsShopCart(String item) {
		
		//List<WebElement> finded = (List<WebElement>) this.items.stream().flatMap(f-> items.stream().map(m -> f.getText())).collect(Collectors.toList());
		List<WebElement> list = driver.findElements(allItemsShoppingCart);
		WebElement element[] = new WebElement[2];
		for (int i = 0; i < list.size(); i++) {
			String[] productName = list.get(i).getText().split("-"); 
			
			if (item.equalsIgnoreCase((productName[0].trim()))) {
				element[0] = driver.findElements(productsRemove).get(i);
				element[1] = driver.findElements(quantitysByItemsShoppingCart).get(i);
				break;
			}

		}
		
		return element;
	}
	
	public List<WebElement> allProducts() {
		return driver.findElements(allProducts);
	}
	
	public List<WebElement> getAllItemShippingCart() {
		return driver.findElements(allItemsShoppingCart);
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
	
	public List<WebElement> allAddButtons() {
		return driver.findElements(allAddButtons);
	}
	
	public List<WebElement> allPlusButtons() {
		return driver.findElements(allPlusButtons);
	}
	
	public List<WebElement> allHrefNavegables() {
		return driver.findElements(allHrefNavegables);
	}
	
	public WebElement searchBox() {
		return driver.findElement(searchBox);
	}
	
	public WebElement searchButton() {
		return driver.findElement(searchButton);
	}
	
	public WebElement notMatchedSearchMessage() {
		return driver.findElement(notMatchedSearchMessage);
	}
	
}
