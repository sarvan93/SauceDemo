
package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentManager {
    private static ExtentReports extent;
    public static ExtentTest test;

    public static void initReport() {
        ExtentSparkReporter  htmlReporter = new ExtentSparkReporter("Report/ExtentReport.html");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    public static void createTest(String testName)
    {
        test = extent.createTest(testName);
    }

    public static void flushReport() {
        if (extent != null) extent.flush();
    }
}