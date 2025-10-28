package testBase;

import java.time.Duration;

import org.apache.commons.text.RandomStringGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {

	public WebDriver driver;  //Log4j
	public Logger logger;  //Log4j

	// ===============================
	// Setup Method
	// ===============================
	/**
	 * Method annotated with @BeforeClass runs once before all test methods in this
	 * class. Typically used to initialize WebDriver and open the application URL.
	 */
	@BeforeClass
	public void setup() {

		logger = LogManager.getLogger(this.getClass());

		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();

	}

	// ===============================
	// Tear Down Method
	// ===============================
	/**
	 * Method annotated with @AfterClass runs once after all test methods in this
	 * class. Typically used to close the browser and clean up resources.
	 */

	@AfterClass
	public void tearDown() {
		driver.quit();

	}

	public String randomString() {

		// Create random string generator for lowercase letters
		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('a', 'z').build();

		// Generate random string using constant length
		String randomStr = generator.generate(5);
		return randomStr;

	}

	public String randomNumber() {
		// Generator for digits '0' to '9'
		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', '9').build();

		// Generate a 10-digit random numeric string
		String randomNumber = generator.generate(10);
		return randomNumber;

	}

	public String randomAlphaNumeric() {
		// Generator for 3 random letters
		RandomStringGenerator letterGenerator = new RandomStringGenerator.Builder().withinRange('a', 'z').build();
		String letters = letterGenerator.generate(3); // optional: uppercase

		// Generator for 3 random digits
		RandomStringGenerator numberGenerator = new RandomStringGenerator.Builder().withinRange('0', '9').build();
		String numbers = numberGenerator.generate(3);

		// Combine letters and numbers
		return letters + "@" + numbers;
	}

}
