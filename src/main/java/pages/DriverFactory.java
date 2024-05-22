package pages;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import common.ConfigLoader;
import io.cucumber.java.Scenario;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.Map;

public class DriverFactory {
   public static  WebDriver driver;
    public static String publicURL;
    public static final String USERNAME = "kannanre_3IAdJY";
    public static final String AUTOMATE_KEY = "Nx8ZDFbUtWU49hoMQtsS";
    public static final String url = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    public static void initialiseBrowser(Scenario scenario) throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("browser", ConfigLoader.getProperty("browser"));
        caps.setCapability("browser_version", "80");
        //caps.setCapability("name", "sasikumar's First Test");
        caps.setCapability("name", scenario.getName());
        driver = new RemoteWebDriver(new URL(url), caps);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        Object response = jse.executeScript("browserstack_executor: {\"action\": \"getSessionDetails\"}");
        //JSONObject json = (JSONObject) new JSONParser().parse((String) response);
        Gson gson = new Gson();
        Map<String,String> result= gson.fromJson((String) response, Map.class);
        publicURL=result.get("public_url");
        //publicURL =  responseElement.get("public_url");
        System.out.println("session id -- before " + publicURL);


// Navigating through the URL
//        driver.get("http://www.google.com");
////Locating the search box of google
//        WebElement element = driver.findElement(By.name("q"));
//// Sending browserstack keyword for search
//        element.sendKeys("BrowserStack");
//        element.submit();
//
//        System.out.println(driver.getTitle());
//        driver.quit();
    }
}
