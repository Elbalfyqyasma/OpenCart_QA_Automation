package testBase;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.text.RandomStringGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public WebDriver driver; 
	public Logger logger; // Log4j
	public Properties p;

	// ===============================
	// Setup Method
	// ===============================
	/**
	 * Method annotated with @BeforeClass runs once before all test methods in this
	 * class. Typically used to initialize WebDriver and open the application URL.
	 * @throws FileNotFoundException 
	 */
	@BeforeClass
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException {
		
        //Loading config.properties file
		FileReader file = new FileReader("./src//resources//config.properties");
		p= new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass());

		switch (br.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;

		case "firefox":
			driver = new FirefoxDriver();
			break;

		case "edge":
			driver = new EdgeDriver();
			break;

		default:
			System.out.println("Browser not supported");
			driver = null;
			return;
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appUrl2"));
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
