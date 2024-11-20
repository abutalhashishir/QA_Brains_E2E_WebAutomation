package qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import qa.base.Base;
import qa.pages.LoginPage;

public class LoginTest extends Base {

	LoginPage loginPage;

	public LoginTest() {
		super();
	}

	public WebDriver driver;

	@BeforeMethod
	public void setup() throws InterruptedException {

		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		loginPage = new LoginPage(driver);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void LoginWithValidCredentials() throws InterruptedException {
		loginPage.loginValidCredentials(dataProp.getProperty("Email"), dataProp.getProperty("Password"));
		Assert.assertEquals(loginPage.retrieveLoinSuccessHeading(), dataProp.getProperty("loinSuccessHeading"),
				"Login Success page is not displayed");
	}

	@Test(priority = 2)
	public void LoginWithInvalidCredentials() throws InterruptedException {
		loginPage.loginWithInvalidCredentials(dataProp.getProperty("InvalidEmail"), dataProp.getProperty("Password"));
		Assert.assertEquals(loginPage.retrieveLoinWithInvalidCredentialsHeading(),
				dataProp.getProperty("loginWithInvalidCredentialsPopup"), "Invalid login popup is not displayed");
	}

	@Test(priority = 3)
	public void LoginWithInvalidEmial() throws InterruptedException {
		loginPage.loginWithInvalidEmail(dataProp.getProperty("InvalidEmialFormat"), dataProp.getProperty("Password"));
		Assert.assertEquals(loginPage.retrieveLoinWithInvalidEmailHeading(),
				dataProp.getProperty("loginWithInvalidemailPopup"), "Invalid email popup is not displayed");
	}
	@Test(priority = 4)
	public void verifyLoginAccountWithEmptyField() throws InterruptedException {
		loginPage.EmptyLoginCredintialsFields();
		Thread.sleep(2000);
		Assert.assertTrue((loginPage.displayStatusOfWarningMessages(dataProp.getProperty("emptyEmailWarning"),dataProp.getProperty("emptyPasswordWarning"))));
				
	}

}
