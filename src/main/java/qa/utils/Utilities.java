package qa.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {

    public static final int IMPLICIT_WAIT_TIME = 30;
    public static final int PAGE_LOAD_TIME = 100;

    public static String generateEmailWithTimeStamp() {
        Date date = new Date();
        String timestamp = date.toString().replace(" ", "_").replace(":", "_");
        return "shishir" + timestamp + "@example.com";
    }

    public static boolean isFontSizeAcceptable(String fontSize) {
        fontSize = fontSize.replaceAll("[^\\d.]", "");
        double size = Double.parseDouble(fontSize);
        double minFontSize = 0.0;
        double maxFontSize = 150.0;
        return size >= minFontSize && size <= maxFontSize;
    }

    public static String captureScreenshot(WebDriver driver, String testName) {
        File srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotsDir = System.getProperty("user.dir") + "\\Screenshots\\";
        new File(screenshotsDir).mkdirs(); // Ensure directory exists
        String destinationScreenshotPath = screenshotsDir + testName + ".png";

        try {
            FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
        } catch (IOException e) {
            e.printStackTrace(); // Optionally, use a logger here
        }

        return destinationScreenshotPath;
    }
}
