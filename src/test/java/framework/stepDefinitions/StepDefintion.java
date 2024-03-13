package framework.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import framework.TestComponents.BaseTest;
import framework.pageobjects.CartPage;
import framework.pageobjects.CheckOutPage;
import framework.pageobjects.ConfirmationPage;
import framework.pageobjects.LoginPage;
import framework.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefintion extends BaseTest{
	
	public LoginPage loginPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmPage;
	
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		loginPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username,String password)
	{
		productCatalogue = loginPage.loginApplication(username,password); //Entering Username and password
	}
	
	@When("^I add the product (.+) to cart$")
	public void I_add_the_product_to_cart(String productName)
	{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName)
	{
		CartPage cartPage = productCatalogue.goToCart(); //Add to cart button

		Boolean match = cartPage.verifyProduct(productName);
		Assert.assertTrue(match);
		
		//Checkout button
		CheckOutPage checkout = cartPage.goToCheckOut();
		
		//Entering country
		checkout.enterCountry("ind");
		checkout.selectCountry();
		
		//Clicking place order
		confirmPage = checkout.placeOrderButton();
	}
	
	@Then("verify {string} is displayed on Confirmation page.")
	public void message_displayed_confirmationPage(String string)
	{
		String confirmMessage = confirmPage.confirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void message_is_displayed(String strArg1) throws InterruptedException
	{
		Assert.assertEquals(strArg1, loginPage.getErrorMessage());
		driver.close();
	}
}
