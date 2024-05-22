package step_definitions;

import io.cucumber.java.Scenario;
import pages.*;
import report.ExtendCucumberReport;


public class ServiceClass extends BaseClass {
    Scenario scenario = null;
    ExtendCucumberReport report = new ExtendCucumberReport();

    @io.cucumber.java.Before
    public void setup(Scenario scenario) {
        this.scenario = scenario;
    }
    @io.cucumber.java.Before
    public void initiateBrowser() throws Exception {
        //initializeBrowser();
        DriverFactory.initialiseBrowser(scenario);
    }
    @io.cucumber.java.After
    public void after() throws Exception {
        closeBrowser();
        System.out.println("after step--"+publicURL);
       // scenario.attach(publicURL,"text/plain","Browser Stack video Link");
        report.attachToReport("text/plain", publicURL, "Browser Stack video Link");
        System.out.println("before send email class");
    }

//    @AfterAll
//    public static void afterScenario() {
//        //SendEmail.sendEmailWay();
//        //String reportPath = "D:\\Demo\\target\\cucumber-reports\\cucumber.html";
//        copyReport();
//        String targetFolder = "D:\\Demo\\src\\test\\resources\\cucumber-reports\\";
//        String reportName = "cucumber.html";
//        String reportPath = new File(targetFolder, reportName).getAbsolutePath();
//        SendEmail.sendEmailWay(reportPath);
//    }
}
