package qa.pages;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.nio.file.Files;
import java.util.Base64;

public class CreatePostPage {

    WebDriver driver;
    
    @FindBy(xpath = "/html/body/div[1]/div/header/div/nav/div/div[2]/div[1]/button")
    private WebElement writeBtn;
    
    @FindBy(xpath = "//button[normalize-space()='Blog']")
    private WebElement BlogBtn;

    @FindBy(xpath = "//input[@type='file']") 
    private WebElement fileInput;
  
    @FindBy(xpath = "//input[@name='title']") 
    private WebElement blogTittleField;
    
    @FindBy(xpath = "//*[@class=\"chooseCategory__placeholder css-1jqq78o-placeholder\"]") 
    private WebElement selectTopicBtn;
    
    @FindBy(xpath = "//div[@class='ql-editor ql-blank']") 
    private WebElement writeContentField;
  
    @FindBy(xpath = "//div[@class='ql-editor']") 
    private WebElement dropPhotoEditField;
    
    @FindBy(xpath = "//input[@placeholder='Add a tag']") 
    private WebElement tagField;

    @FindBy(xpath = "//*[name()='path' and contains(@d,'M7 24h2v-2')]") 
    private WebElement scroll1;
  
    @FindBy(xpath = "//button[@type='submit']") 
    private WebElement submitBlogBtn;

    @FindBy(xpath = "//h4[@class='title fw-semi fs-4 font-oswald mb-0']") 
    private WebElement blogUploadSuccessHeading;
    
    // Constructor
    public CreatePostPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void writeBlog(String blogTittleText, String writeContentText, String tag1Text, String tag2Text, String tag3Text, String tag4Text) throws InterruptedException, IOException {

        writeBtn.click();
        Thread.sleep(2000);
        BlogBtn.click();
        Thread.sleep(40000);
        
        String photoPath = new File("src\\main\\java\\qa\\config\\CoverPhotoForBlog.png").getAbsolutePath();
        fileInput.sendKeys(photoPath);
        blogTittleField.sendKeys(blogTittleText);
        
        Thread.sleep(2000);
        selectTopicBtn.click();
        Thread.sleep(2000);
        
        WebElement option = driver.findElement(By.xpath("//*[@id='choose-blog-category']/div/div[1]/div/div/span[text()='WEB TESTING']"));
        option.click();
        
        Thread.sleep(2000);
        writeContentField.sendKeys(writeContentText);
        
        Thread.sleep(5000);
        
        Path imagePath = new File("src\\main\\java\\qa\\config\\pgoto.png").toPath();
        String base64Image = Base64.getEncoder().encodeToString(Files.readAllBytes(imagePath));
        String base64ImageSrc = "data:image/png;base64," + base64Image;

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('.ql-editor').innerHTML += '<img src=\"" + base64ImageSrc + "\" />';");
        Thread.sleep(5000); 
        
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scroll1);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Thread.sleep(2000);
        tagField.sendKeys(tag1Text + Keys.ENTER);
        Thread.sleep(2000);
        tagField.sendKeys(tag2Text + Keys.ENTER);
        Thread.sleep(2000);
        tagField.sendKeys(tag3Text + Keys.ENTER);
        Thread.sleep(2000);
        tagField.sendKeys(tag4Text + Keys.ENTER);
        Thread.sleep(2000);
        submitBlogBtn.click();
        Thread.sleep(30000);
    }
    
    public String retrieveCountryWarning() {
		String countryWarningText = blogUploadSuccessHeading.getText();
		return countryWarningText;
	}
}
