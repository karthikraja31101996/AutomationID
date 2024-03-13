package framework.tests;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String productName = "ADIDAS ORIGINAL";
		
		//Chrome browser setup
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		//Implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Hit the URL
		driver.get("https://rahulshettyacademy.com/client/");
		
		//Locators
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("dummy1997@gmail.com");
		driver.findElement(By.cssSelector("input#userPassword")).sendKeys("Star@1996");
		driver.findElement(By.id("login")).click();
		
		//Explicit wait
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		
		//Getting the products into the list
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.col-sm-10")));
		List<WebElement> products = driver.findElements(By.cssSelector("div.col-sm-10"));
		
		//Applying Java Streams
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		
		//Locating Add to cart for the specific product and clicking
		prod.findElement(By.cssSelector("div.card-body button:last-of-type")).click();
		
	
		//Explicit wait
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		//Wait for animation to disappear
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		
		//Add to cart button
		driver.findElement(By.xpath("(//button[@class='btn btn-custom'])[3]")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".infoWrap div:first-of-type"));
		
		Boolean match = cartProducts.stream().anyMatch(cartProduct->
		cartProduct.findElement(By.cssSelector(".cartSection h3")).getText().equalsIgnoreCase(productName));
		
		Assert.assertTrue(match);
		
		//Checkout button
		driver.findElement(By.cssSelector("button[type='button']:first-child")).click();
		
		//Entering country
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
		
		driver.findElement(By.xpath("(//span[@class='ng-star-inserted'])[2]")).click();
		
		//Clicking place order
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement
				(By.xpath("//a[@class='btnn action__submit ng-star-inserted']"))).click().build().perform();
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
	}

}
