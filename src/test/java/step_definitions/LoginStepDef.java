package step_definitions;


import io.cucumber.java.AfterStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import pages.BaseClass;
import pages.HomePage;
import report.ExtendCucumberReport;

import java.util.HashMap;
import java.util.Map;

public class LoginStepDef extends HomePage{
    Map<String,String> data=new HashMap<>();
    ExtendCucumberReport report = new ExtendCucumberReport();
    @AfterStep
    public void afterStepCompletes() throws Exception {
        String screenshotPath = takeSnapShot("StepScreenshot_" + System.currentTimeMillis());
        report.attachScreenshotToReport(screenshotPath);
    }
    @Given("Launch the site")
    public void launchSite(){
        launchURL();
    }
@Then("^create user using \"(.*)\"$")
    public void createUserAPICall(String testData) throws Exception {
   // data= BaseClass.readJsonElement("customer.json",testData);
    //String jsonStr = JSONValue.toJSONString(data);
    //Response res=BaseClass.postWithoutAppURL("https://reqres.in/api/users",jsonStr);
    System.out.println("============");
    //System.out.println(res.asString());
}
}
