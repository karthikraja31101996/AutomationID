package framework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.ReusableUtilities.ReusableUtilities;

public class ProductCatalogue extends ReusableUtilities{
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Getting the products into the list
	@FindBy(css="div.col-sm-10")
	List<WebElement> products;
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(By.cssSelector("div.col-sm-10"));
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName)
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(By.cssSelector("div.card-body button:last-of-type")).click();
		waitForElementToAppear(By.cssSelector("#toast-container"));
		waitForElementToDisappear(driver.findElement(By.cssSelector(".ng-animating")));	
	}

}
