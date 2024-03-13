package framework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.ReusableUtilities.ReusableUtilities;

public class LoginPage extends ReusableUtilities{
	//Local Variable
	WebDriver driver;
	
	public LoginPage(WebDriver driver) //Foreign Variable
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//PageFactory
	//-----E-mail------
	@FindBy(xpath="//input[@id='userEmail']")
	WebElement userEmail;
	
	//----Password----
	@FindBy(css="input#userPassword")
	WebElement userPassword;
	
	//----Login Button----
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(xpath="//div[@aria-label='Incorrect email or password.']")
	WebElement errorMessage;
	
	public ProductCatalogue loginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		login.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String getErrorMessage() throws InterruptedException
	{
		waitForElementToAppear(errorMessage);
		errorMessage.getText();
		return errorMessage.getText();
	}

}
