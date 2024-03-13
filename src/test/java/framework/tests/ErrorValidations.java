package framework.tests;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import framework.TestComponents.BaseTest;
import framework.TestComponents.Retry;
import framework.pageobjects.CartPage;
import framework.pageobjects.CheckOutPage;
import framework.pageobjects.ConfirmationPage;
import framework.pageobjects.ProductCatalogue;

public class ErrorValidations extends BaseTest {
	
	ExtentReports extent;

	@Test(groups = {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException
		{
			String productName = "ADIDAS ORIGINAL";
			loginPage.loginApplication("dummy1997@gmail.com", "Star996"); //Entering Username and password
			
			Assert.assertEquals("Incorrect email  password.", loginPage.getErrorMessage());
		}
	
	@Test
	public void ProductErrorValidation() throws IOException
{
	String productName = "IPHONE 13 PRO";
	
	ProductCatalogue productCatalogue = loginPage.loginApplication("dummy1998@gmail.com", "Star@1996"); //Entering Username and password
	
	List<WebElement> products = productCatalogue.getProductList();
	productCatalogue.addProductToCart(productName);
	CartPage cartPage = productCatalogue.goToCart(); //Add to cart button

	Boolean match = cartPage.verifyProduct(productName);
	Assert.assertTrue(match);
	
}
}
	


