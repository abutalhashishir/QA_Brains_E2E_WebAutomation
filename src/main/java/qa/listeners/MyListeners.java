package qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import qa.utils.ExtentReporter;
import qa.utils.Utilities;

public class MyListeners implements ITestListener {

    private ExtentReports extentReport;
    private ExtentTest extentTest;

    @Override
    public void onStart(ITestContext context) {
        extentReport = ExtentReporter.generateExtentReport();
        System.out.println("Test suite execution started: " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extentReport.createTest(result.getMethod().getMethodName());
        extentTest.log(Status.INFO, result.getMethod().getMethodName() + " started executing");
        System.out.println("Test started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS, result.getMethod().getMethodName() + " executed successfully");
        System.out.println("Test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.log(Status.FAIL, result.getMethod().getMethodName() + " failed");
        extentTest.log(Status.INFO, result.getThrowable());
        System.out.println("Test failed: " + result.getMethod().getMethodName());

        WebDriver driver = null;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        if (driver != null) {
            String screenshotPath = Utilities.captureScreenshot(driver, result.getMethod().getMethodName());
            extentTest.addScreenCaptureFromPath(screenshotPath);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.log(Status.SKIP, result.getMethod().getMethodName() + " was skipped");
        extentTest.log(Status.INFO, result.getThrowable());
        System.out.println("Test skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReport.flush();
        System.out.println("Test suite execution finished: " + context.getName());

        String reportPath = System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentReport.html";
        File extentReportFile = new File(reportPath);
        if (extentReportFile.exists()) {
            try {
                Desktop.getDesktop().browse(extentReportFile.toURI());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
