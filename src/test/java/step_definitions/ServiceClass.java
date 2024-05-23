package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import pages.*;
import report.ExtendCucumberReport;


public class ServiceClass extends BaseClass {
    Scenario scenario = null;
    ExtendCucumberReport report = new ExtendCucumberReport();

    @Before
    public void initiateBrowser(Scenario scenario) throws Exception {
        System.out.println("********* before hooks");
        this.scenario = scenario;
        System.out.println("Scenario name : "+scenario.getName());
        DriverFactory.initialiseBrowser(scenario);
    }
    @After
    public void after() throws Exception {
        closeBrowser();
        System.out.println("after step--"+publicURL);
       // scenario.attach(publicURL,"text/plain","Browser Stack video Link");
        report.attachToReport("text/plain", publicURL, "Browser Stack video Link");
        System.out.println("before send email class");
    }
}
