package qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import qa.base.Base;
import qa.pages.HomePage;

public class HomePageTest extends Base {

	HomePage homePage;

	public HomePageTest() {
		super();
	}

	public WebDriver driver;

	@BeforeMethod
	public void setup() throws InterruptedException {

		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		homePage = new HomePage(driver);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	private void TrendingPostViewTest() throws InterruptedException {
		homePage.trendingPostView();
		Assert.assertEquals(homePage.trendingPostViewSuccessHeading(),
				dataProp.getProperty("trendingPostSuccesHeading"), "Trending Post page is not displayed");
	}

	@Test(priority = 2)
	private void PopularPostViewTest() throws InterruptedException {
		homePage.popularPostView();
		Assert.assertEquals(homePage.popularPostViewSuccessHeading(), dataProp.getProperty("popularPostSuccesHeading"),
				"Popular Post page is not displayed");
	}

	@Test(priority = 3)
	private void ExplorePostViewTest() throws InterruptedException {
		homePage.ExplorePostView();
		Assert.assertEquals(homePage.ExplorePostViewSuccessHeading(), dataProp.getProperty("explorePostSuccesHeading"),
				"Explore Post page is not displayed");
	}

	@Test(priority = 4)
	private void trendingDiscussionPostViewTest() throws InterruptedException {
		homePage.trendingDiscussionView();
		Assert.assertEquals(homePage.trendingDiscussionViewSuccessHeading(),
				dataProp.getProperty("trendingDiscussionSuccesHeading"),
				"Trending DiscussionSucces Post page is not displayed");
	}
}
