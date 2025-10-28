package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    // ===============================
    // Constructor
    // ===============================

	public HomePage(WebDriver driver) {
		super(driver);

	}

	 // ===============================
    // Page Elements
    // ===============================
	
	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement lnkMyaccount;

	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement lnkRegister;

	
	// ===============================
    // Page Actions / Methods
    // ===============================

	public void clickAccount() {
		lnkMyaccount.click();
	}

	public void clickRegister() {
		lnkRegister.click();
	}
	 
	 
}
