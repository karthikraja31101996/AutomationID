package framework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.ReusableUtilities.ReusableUtilities;

public class CartPage extends ReusableUtilities {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".infoWrap div:first-of-type")
	List<WebElement> cartProducts;
	
	@FindBy(css="button[type='button']:first-child")
	WebElement checkOutButton;
	
	public Boolean verifyProduct(String productName)
	{
		Boolean match = cartProducts.stream().anyMatch(cartProduct->
		cartProduct.findElement(By.cssSelector(".cartSection h3")).getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage goToCheckOut()
	{
		checkOutButton.click();
		return new CheckOutPage(driver);
	}

	

}
