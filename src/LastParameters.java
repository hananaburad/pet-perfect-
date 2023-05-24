import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.Random;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

public class LastParameters {

	WebDriver driver = new ChromeDriver();
	//WebDriver driver = new EdgeDriver();
	SoftAssert Assert = new SoftAssert();
	Random rand = new Random();
	WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofMinutes(2));

	static String Cart_Url = "https://ecom-pet-store.myshopify.com/cart";
	static String dogs_page = "https://ecom-pet-store.myshopify.com/collections/frontpage";
	static String url = "https://ecom-pet-store.myshopify.com/";
	static String CONTACT_URL = "https://ecom-pet-store.myshopify.com/pages/contact";
	static String EXPECTED_MSG = "Thanks for contacting us. We'll get back to you as soon as possible.";

	String URL = "https://ecom-pet-store.myshopify.com/";
	String InformationURL = "https://ecom-pet-store.myshopify.com/checkouts/c/bcbe79b43cf850fa05495951398175ad/information";

}
