package step_definitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import pages.BaseClass;
import pages.HomePage;
import java.util.HashMap;
import java.util.Map;

public class LoginStepDef extends HomePage{
    Map<String,String> data=new HashMap<>();
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
