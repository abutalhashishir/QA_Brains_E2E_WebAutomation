package qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

    public static ExtentReports generateExtentReport() {
        ExtentReports extentReport = new ExtentReports();
        File extentReportFile = new File(System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentReport.html");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);

        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("QA Forum Test Automation Results Report");
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

        extentReport.attachReporter(sparkReporter);

        Properties configProp = new Properties();
        File configPropFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\qa\\config\\config.properties");

        try (FileInputStream fisConfigProp = new FileInputStream(configPropFile)) {
            configProp.load(fisConfigProp);
        } catch (IOException e) {
            e.printStackTrace(); // Optionally, use a logger here
        }

        extentReport.setSystemInfo("Application URL", configProp.getProperty("url", "N/A"));
        extentReport.setSystemInfo("Browser Name", configProp.getProperty("browserName", "N/A"));
        extentReport.setSystemInfo("Email", configProp.getProperty("validEmail", "N/A"));
        extentReport.setSystemInfo("Password", configProp.getProperty("validPassword", "N/A"));
        extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
        extentReport.setSystemInfo("Username", System.getProperty("user.name"));
        extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));

        return extentReport;
    }
}
