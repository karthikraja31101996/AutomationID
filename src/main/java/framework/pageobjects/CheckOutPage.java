package framework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.ReusableUtilities.ReusableUtilities;

public class CheckOutPage extends ReusableUtilities {
	
	WebDriver driver;

	public CheckOutPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement enterCountry;
	
	@FindBy(xpath="(//span[@class='ng-star-inserted'])[2]")
	WebElement selectCountry;
	
	
	public void enterCountry(String countryName)
	{
		enterCountry.sendKeys(countryName);
	}
	
	public void selectCountry()
	{
		selectCountry.click();
	}
	
	public ConfirmationPage placeOrderButton()
	{
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement
				(By.xpath("//a[@class='btnn action__submit ng-star-inserted']"))).click().build().perform();
		
		return new ConfirmationPage(driver);
	}

}
