package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.event.Result;
import com.aventstack.extentreports.Status;
import pages.SendEmail;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class ExtendCucumberReport implements ReportEvents {
    private static ExtentReports extent;
    private static ExtentTest test;
    private static ExtentHtmlReporter htmlReporter;
    Scenario scenario;
    @Override
    public void testRunStart() {
        htmlReporter = new ExtentHtmlReporter("target/Extent-Report/extent.html");
        htmlReporter.config().setDocumentTitle("Automation Test Report");
        htmlReporter.config().setReportName("Cucumber Test Report");
        htmlReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "Test User");

    }

    @Override
    public void testScenarioStart(String scenarioName, List<String> tags) {
        test = extent.createTest(scenarioName);
        for (String tag : tags) {
            test.assignCategory(tag);
        }
    }

    @Override
    public void testStepStart(String stepName) {
        test.log(com.aventstack.extentreports.Status.PASS,stepName);
    }

    @Override
    public void testStepFinish(Result result) {

    }

    @Override
    public void testScenarioFinish() {
        extent.flush();

    }
    public void attachToReport(String mediaType, String content, String name) throws IOException {
        test.log(Status.INFO, "Attached: " + name);
        if (mediaType.equals("image/png")) {
            test.addScreenCaptureFromPath(content, name);
        } else {
            test.log(Status.INFO, content);
        }
    }

    @Override
    public void testRunFinish() throws InterruptedException {
        //String reportPath = "D:\\Demo\\target\\cucumber-reports\\cucumber.html";
//        Thread.sleep(3000);
//        String targetFolder = "D:\\Demo\\target\\Extent-Report\\";
//        String reportName = "extent.html";
//        String reportPath = new File(targetFolder, reportName).getAbsolutePath();
//        SendEmail.sendEmailWay(reportPath);
    }
}
