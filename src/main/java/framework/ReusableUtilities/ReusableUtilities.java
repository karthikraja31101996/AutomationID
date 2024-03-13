package framework.ReusableUtilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.pageobjects.CartPage;
import framework.pageobjects.OrderPage;

public class ReusableUtilities {
	
	WebDriver driver;
	
	public ReusableUtilities(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "(//button[@class='btn btn-custom'])[3]")
	WebElement cartHeader;
	
	@FindBy(xpath="(//button[@class='btn btn-custom'])[2]")
	WebElement orderButton;

	public void waitForElementToAppear(By findBy)
	{
	//Explicit wait
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	//Getting the products into the list
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToAppear(WebElement findBy)
	{
	//Explicit wait
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	//Getting the products into the list
	wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementToDisappear(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public CartPage goToCart()
	{
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage ordersButton() 
	{
		orderButton.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}

}
