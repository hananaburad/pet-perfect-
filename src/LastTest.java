import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class LastTest extends LastParameters {

	@BeforeTest
	public void BeforTest() {
		driver.get(URL);
		driver.manage().window().maximize();

	}

	@Test(priority = 1)
	public void search_for_dog1() throws InterruptedException {

		driver.get(dogs_page);
//create a list to select the products randomly 
		List<WebElement> Products = driver.findElements(By.className("grid-product__meta"));
		int productRandInd = rand.nextInt(Products.size());
		Products.get(productRandInd).click();

//  Create a list to store all available options for sizes and colors of the products

		List<WebElement> colors = driver.findElements(By.cssSelector("#ProductSelect-option-0 label"));

		List<WebElement> sizes = driver.findElements(By.cssSelector("#ProductSelect-option-1 label"));

		int colorRandInd = rand.nextInt(colors.size());

		int sizeRandInd = rand.nextInt(sizes.size());

		// select color & size of products randomly

		colors.get(colorRandInd).click();
		sizes.get(sizeRandInd).click();
		driver.findElement(By.className("btn__text")).click();
	}
	
	
	
	

	@Test(priority=2)

	public void check_out() throws IOException, InterruptedException {

		driver.get(Cart_Url);

		
		WebElement checkout_Button = driver.findElement(By.className("cart__checkout"));
		checkout_Button.click();

		// declare Web Elements for check out

		WebElement email_Field = driver.findElement(By.id("email"));

		WebElement last_Namel_Field = driver.findElement(By.name("lastName"));

		WebElement address_Field = driver.findElement(By.name("address1"));

		WebElement city_Field = driver.findElement(By.name("city"));

		WebElement postal_code_Field = driver.findElement(By.name("postalCode"));

		WebElement continue_to_shipping = driver.findElement(By.cssSelector(
               "#Form0 > div:nth-child(1) > div > div.VheJw > div.oQEAZ.WD4IV > div:nth-child(1) > button"));

		email_Field.sendKeys("aa@gmail.com");

		last_Namel_Field.sendKeys("testUser");

		address_Field.sendKeys("Amman");

		city_Field.sendKeys("amman");

		postal_code_Field.sendKeys("13181");

		continue_to_shipping.click(); 

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.findElement(By.xpath("//*[@id=\"shipping_methods\"]/div[2]/div[2]/label")).click();
		driver.findElement(By.className("QT4by")).click();

		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File screenshotFile = scrShot.getScreenshotAs(OutputType.FILE);
		String filePath = ".//test.png";
		FileUtils.copyFile(screenshotFile, new File(filePath));
		
		
		//Assert.assertTrue(driver.findElement(By.id("step-section-primary-header")).isDisplayed());

	}

	
	
	
	
	
	
	@Test(priority=3)
	public void search_for_cat() {
		// Selecting the Search icon
		WebElement searchIcon = driver.findElement(By.cssSelector("#AccessibleNav > li:nth-child(4) > a"));
		// Click Search icon
		searchIcon.click();
		// Select the search input field
		WebElement searchBar = driver.findElement(By.className("input-group-field"));
		// Send the word Cat
		searchBar.sendKeys("cat" + Keys.ENTER);
		// Get title of each element
		List<WebElement> searchResulTitles = driver.findElements(By.className("grid-product__title"));
		// Define a boolean flag to track the condition
		boolean flagIfTitleContainsCat = false;
//Creating a for loop to pass across all titles
		for (int i = 0; i < searchResulTitles.size(); i++) {
			// Define a string called "title" to store the formatted title
			String title = searchResulTitles.get(i).getText().toLowerCase();
			System.out.println(title);
			// Building an if condition structure to verify the existence of the word cat
			// then apply changes on the boolean flag, using the method "contains"
			if (title.contains("cat")) {
				flagIfTitleContainsCat = true;
			} else {
				flagIfTitleContainsCat = false;
				break;
			}
		}
		// Doing an assertion for the flag to be true, which means all titles contains
		// the word "cat"
		Assert.assertEquals(flagIfTitleContainsCat, true);
	}

	
	
	
	
	
	@Test(priority=4)
	public void verify_selecting_random_animal() {
		// Navigating to the specified URL
		driver.get(url);
		// Finding a list of pet types on the webpage
		List<WebElement> pet_type = driver.findElement(By.cssSelector("div.grid:nth-child(2)"))
				.findElements(By.className("collection-grid__item-link"));
		// Generating a random index to select a pet type
		int random_pet_type = rand.nextInt(0, pet_type.size() - 2);
		// Clicking on a randomly selected pet type
		pet_type.get(random_pet_type).click();
		// Finding the grid of pet items
		WebElement items_grid = driver.findElement(By.className("grid-collage"));
		// Finding all the pet items in the grid
		List<WebElement> pet_items = items_grid.findElements(By.className("product--image"));
		// Asserting that the number of pet items is either 10 or 12
		Assert.assertTrue(pet_items.size() == 10 || pet_items.size() == 12,
				"The actual number of items is not as expected");
	}

	
	
	
	
	
	@Test(priority=5)
	public void verify_filling_contact_form_and_submit() {

		// Navigating to the required information

		driver.get(CONTACT_URL);
		driver.findElement(By.id("ContactFormName")).sendKeys("Carl");
		driver.findElement(By.id("ContactFormEmail")).sendKeys("a@vmb.com");
		driver.findElement(By.id("ContactFormPhone")).sendKeys("55292500611");
		driver.findElement(By.id("ContactFormMessage")).sendKeys("I want my order wrapped as a gift");

		// Clicking the submit button on the Contact Form
		driver.findElement(By.className("btn")).click();

		// Waiting for the success message element to be visible
		WebElement success_msg = webDriverWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"contact_form\"]/p")));

		// Asserting that the success message matches the expected message
		Assert.assertEquals(EXPECTED_MSG, success_msg.getText());
	}

	@AfterTest
	public void AfterTest() {
		// Asserting all the assertions made in the test
		Assert.assertAll();

		// Quitting the driver
		//driver.quit();

	}

}