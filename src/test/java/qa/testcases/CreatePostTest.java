package qa.testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import qa.base.Base;
import qa.pages.CreatePostPage;
import qa.pages.LoginPage;

public class CreatePostTest extends Base {

	LoginPage loginPage;
	CreatePostPage createPostPage;

	public CreatePostTest() {
		super();
	}

	public WebDriver driver;

	@BeforeClass
	public void setup() throws InterruptedException {

		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		loginPage = new LoginPage(driver);
		createPostPage = new CreatePostPage(driver);
		loginPage.loginValidCredentials(dataProp.getProperty("Email"), dataProp.getProperty("Password"));
		Thread.sleep(5000);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void BlogPostTest() throws InterruptedException, IOException {
		createPostPage.writeBlog(dataProp.getProperty("blogTittle"), dataProp.getProperty("writeContent"),
				dataProp.getProperty("tag1"), dataProp.getProperty("tag2"), dataProp.getProperty("tag3"),
				dataProp.getProperty("tag4"));
		Assert.assertEquals(createPostPage.retrieveCountryWarning(), dataProp.getProperty("blogUploadSuccessHeading"),
				"Blog upload popup is not displayed");

	}

}
