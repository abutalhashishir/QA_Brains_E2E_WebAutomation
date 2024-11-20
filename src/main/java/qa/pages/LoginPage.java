package qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='after']//span[@class='btn-text'][normalize-space()='Sign']")
	private WebElement signinBtn;

	@FindBy(xpath = "//input[@name='email']")
	private WebElement emaialField;

	@FindBy(xpath = "//input[@name='password']")
	private WebElement passwordField;

	@FindBy(xpath = "//span[@class='after']//span[@class='btn-text'][normalize-space()='Sign']")
	private WebElement loginBtn;

	@FindBy(xpath = "//small[normalize-space()='Email is a required field']")
	private WebElement emailEmptydWarning;

	@FindBy(xpath = "//small[normalize-space()='Password is a required field']")
	private WebElement passwordEmptydWarning;

	@FindBy(xpath = "//div[contains(text(),'Successfully login')]")
	private WebElement loginSuccessPopup;

	@FindBy(xpath = "//div[contains(text(),'Your email or password is incorrect.')]")
	private WebElement loginWithInvalidCredentialsPopup;

	@FindBy(xpath = "//small[normalize-space()='Email must be a valid email']")
	private WebElement loginWithInvalidEmailPopup;

	@FindBy(xpath = "//small[normalize-space()='Email is a required field']")
	private WebElement emptyEmailPopup;

	@FindBy(xpath = "//small[normalize-space()='Password is a required field']")
	private WebElement emptyPasswordPopup;

	public void loginValidCredentials(String ValidEmailText, String ValidPasswordText) throws InterruptedException {
		signinBtn.click();
		Thread.sleep(2000);
		emaialField.sendKeys(ValidEmailText);
		Thread.sleep(2000);
		passwordField.sendKeys(ValidPasswordText);
		Thread.sleep(2000);
		loginBtn.click();
		Thread.sleep(3000);
	}

	public String retrieveLoinSuccessHeading() {
		// Retrieve the Valid login success heading text
		String loinSuccessHeadingText = loginSuccessPopup.getText();
		return loinSuccessHeadingText;
	}

	public void loginWithInvalidCredentials(String InvalidEmailText, String ValidPasswordText)
			throws InterruptedException {
		signinBtn.click();
		Thread.sleep(2000);
		emaialField.sendKeys(InvalidEmailText);
		Thread.sleep(2000);
		passwordField.sendKeys(ValidPasswordText);
		Thread.sleep(2000);
		loginBtn.click();
		Thread.sleep(3000);
	}

	public String retrieveLoinWithInvalidCredentialsHeading() {
		// Retrieve the Invalid login success heading text
		String loginWithInvalidCredentialsText = loginWithInvalidCredentialsPopup.getText();
		return loginWithInvalidCredentialsText;
	}

	public void loginWithInvalidEmail(String InvalidEmialFormatText, String ValidPasswordText)
			throws InterruptedException {
		signinBtn.click();
		Thread.sleep(2000);
		emaialField.sendKeys(InvalidEmialFormatText);
		Thread.sleep(2000);
		passwordField.sendKeys(ValidPasswordText);
		Thread.sleep(2000);
		loginBtn.click();
		Thread.sleep(3000);
	}

	public String retrieveLoinWithInvalidEmailHeading() {
		// Retrieve the invalid email login success heading text
		String loginWithInvalidEmailText = loginWithInvalidEmailPopup.getText();
		return loginWithInvalidEmailText;
	}

	public void EmptyLoginCredintialsFields() throws InterruptedException {
		signinBtn.click();
		Thread.sleep(2000);
		loginBtn.click();
		Thread.sleep(2000);
	}

	public boolean displayStatusOfWarningMessages(String expectedEmptyEmailWarning,
			String expectedEmptyPasswordWarning) {
		boolean emptyEmailWarningStatus = emptyEmailPopup.getText().contains(expectedEmptyEmailWarning);
		boolean emptyPasswordWarningStatus = emptyPasswordPopup.getText().equals(expectedEmptyPasswordWarning);
		return emptyEmailWarningStatus && emptyPasswordWarningStatus;
	}
}
