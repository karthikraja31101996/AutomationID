package framework.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import framework.TestComponents.BaseTest;
import framework.pageobjects.CartPage;
import framework.pageobjects.CheckOutPage;
import framework.pageobjects.ConfirmationPage;
import framework.pageobjects.LoginPage;
import framework.pageobjects.OrderPage;
import framework.pageobjects.ProductCatalogue;

public class ActualTest extends BaseTest {
	
	   String productName = "ADIDAS ORIGINAL";

		@Test(dataProvider="getData",groups= {"Purchase"})
		public void ActualTest(HashMap<String,String> input) throws IOException, InterruptedException
	{
			
		ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"),input.get("password")); //Entering Username and password
		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCart(); //Add to cart button

		Boolean match = cartPage.verifyProduct(input.get("product"));
		Assert.assertTrue(match);
		
		//Checkout button
		CheckOutPage checkout = cartPage.goToCheckOut();
		
		//Entering country
		checkout.enterCountry("ind");
		checkout.selectCountry();
		
		//Clicking place order
		ConfirmationPage confirmPage = checkout.placeOrderButton();
		
		String confirmMessage = confirmPage.confirmMessage();
		AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
			
		//To Verify ADIDAS original is displayed in the product file
		
		@Test(dependsOnMethods= {"ActualTest"})
		public void OrderHistoryTest()
		{
			ProductCatalogue productCatalogue = loginPage.loginApplication("dummy1997@gmail.com", "Star@1996"); //Entering Username and password
			OrderPage orderPage = productCatalogue.ordersButton();
			Assert.assertTrue(orderPage.verifyOrder(productName));
		}
		
		@DataProvider
		public Object[][] getData() throws IOException
		{
			List<HashMap<String,String>>data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\framework\\data\\PurchaseOrder.json");
			return new Object[][] {{data.get(0)},{data.get(1)}};
			
		}
		
}
	


