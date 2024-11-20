package qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	@FindBy(xpath = "//h4[normalize-space()='Engagement']")
	private WebElement scroll1;

	@FindBy(xpath = "//a[@href='/trending-post']//span[@class='after pb-0']")
	private WebElement viewTrendingPostBtn;

	@FindBy(xpath = "//h1[@class='section-title font-oswald pb-3']")
	private WebElement trendingPostSuccesHeading;
	
	//////////////////////////////////
	
	@FindBy(xpath = "//section[@class='trending-post section-padding pt-3']")
	private WebElement scroll2;

	@FindBy(xpath = "//a[@href='/popular-post']//span[@class='after pb-0']")
	private WebElement viewPopularPostBtn;

	@FindBy(xpath = "//h1[@class='section-title font-oswald pb-3']")
	private WebElement popularPostSuccesHeading;
	
	/////////////////////////////////////////////////////////
	
	@FindBy(xpath = "//section[@class='section-padding popular-post']")
	private WebElement scroll3;

	@FindBy(xpath = "//*[@id=\"categoryExplore\"]/div/div[1]/a/span[2]")
	private WebElement viewExplorePostBtn;

	@FindBy(xpath = "//button[normalize-space()='All Topics']")
	private WebElement ExplorePostSuccesHeading;
	
	/////////////////////////////////////////////////////////////////////////
	
	@FindBy(xpath = "//h3[normalize-space()='CASE STUDY']")
	private WebElement scroll4;

	@FindBy(xpath = "//*[@id=\"home-page\"]/section[6]/div/div[1]/a/span[2]")
	private WebElement trendingDiscussionPostBtn;

	@FindBy(xpath = "//h1[contains(@class,'section-title font-oswald pb-3')]")
	private WebElement trendingDiscussionSuccesHeading;

	public HomePage(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	public void trendingPostView() throws InterruptedException {
	    ((JavascriptExecutor) driver).executeScript("document.body.style.zoom='80%'");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scroll1);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Thread.sleep(2000);
		viewTrendingPostBtn.click();
		Thread.sleep(5000);
	}

	public String trendingPostViewSuccessHeading() {
		String trendingPostSuccesHeadingText = trendingPostSuccesHeading.getText();
		return trendingPostSuccesHeadingText;
	}
	public void popularPostView() throws InterruptedException {
	    ((JavascriptExecutor) driver).executeScript("document.body.style.zoom='80%'");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scroll2);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Thread.sleep(2000);
		viewPopularPostBtn.click();
		Thread.sleep(5000);
	}

	public String popularPostViewSuccessHeading() {
		String popularPostSuccesHeadingText = popularPostSuccesHeading.getText();
		return popularPostSuccesHeadingText;
	}
	public void ExplorePostView() throws InterruptedException {
	    ((JavascriptExecutor) driver).executeScript("document.body.style.zoom='80%'");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scroll3);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Thread.sleep(2000);
		viewExplorePostBtn.click();
		Thread.sleep(5000);
	}

	public String ExplorePostViewSuccessHeading() {
		String ExplorePostSuccesHeadingText = ExplorePostSuccesHeading.getText();
		return ExplorePostSuccesHeadingText;
	}
	public void trendingDiscussionView() throws InterruptedException {
	    ((JavascriptExecutor) driver).executeScript("document.body.style.zoom='80%'");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scroll4);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Thread.sleep(2000);
		trendingDiscussionPostBtn.click();
		Thread.sleep(5000);
	}

	public String trendingDiscussionViewSuccessHeading() {
		String trendingDiscussionHeadingText = trendingDiscussionSuccesHeading.getText();
		return trendingDiscussionHeadingText;
	}
}
