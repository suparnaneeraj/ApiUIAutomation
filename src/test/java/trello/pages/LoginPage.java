package trello.pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	@FindBy(xpath = "//a[text()='Log in']")
	WebElement login;
	@FindBy(id = "user")
	WebElement email;
	@FindBy(id = "login")
	WebElement continueButton;
	@FindBy(id = "password")
	WebElement passwordField;
	@FindBy(id = "login-submit")
	WebElement submitLogin;
	@FindBy(css = "button#onetrust-accept-btn-handler")
	WebElement acceptCookiesButton;
	WebDriverWait wait;

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	// Here the user logs in with the provided credentials
	public void login(String username, String password) {
		login.click();
		email.sendKeys(username);
		continueButton.click();
		wait.until(ExpectedConditions.elementToBeClickable(passwordField));
		passwordField.sendKeys(password);
		submitLogin.click();
	}

	// Below method is to accept the cookies in the cookies pop up displayed during
	// the application launch
	public void acceptCookies() throws InterruptedException {
		// wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton));
		Thread.sleep(3000);
		acceptCookiesButton.click();
	}

}