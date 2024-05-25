package pages;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import common.ConfigLoader;
import io.cucumber.java.Scenario;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class DriverFactory {
   public static  WebDriver driver;
    public static String publicURL;
    public static final String USERNAME = "kannanre_3IAdJY";
    public static final String AUTOMATE_KEY = "Nx8ZDFbUtWU49hoMQtsS";
//    public static final String USERNAME = "acchuthanthasma_Iazcdt";
//    public static final String AUTOMATE_KEY = "d2L8nTGV7hqPhRxwvf7Q";
    public static final String url = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    public static void initialiseBrowser(Scenario scenario) throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        String browserName=ConfigLoader.getProperty("browser");
        String platform=ConfigLoader.getProperty("platform");
        switch(platform){
            case "Windows":
                switch (browserName){
                    case "Chrome":
                        caps.setCapability("os", "Windows");
                        caps.setCapability("os_version", "10");
                        caps.setCapability("browser", ConfigLoader.getProperty("browser"));
                        caps.setCapability("browser_version", "80");
                        caps.setCapability("name", scenario.getName());
                        break;
                    case "IE":
                        caps.setCapability("browser", "IE");
                        caps.setCapability("browser_version", "11.0");
                        caps.setCapability("os", "Windows");
                        caps.setCapability("os_version", "10");
                        caps.setCapability("name", scenario.getName());
                        break;
                    case "Firefox":
                        caps.setCapability("browser", "Firefox");
                        caps.setCapability("browser_version", "latest");
                        caps.setCapability("os", "Windows");
                        caps.setCapability("os_version", "10");
                        caps.setCapability("name", scenario.getName());
                        break;
                }
                break;
            case "Mac":
                switch(browserName){
                    case "Safari":
                        caps.setCapability("browser", "Safari");
                        caps.setCapability("browser_version", "latest");
                        caps.setCapability("os", "OS X");
                        caps.setCapability("os_version", "Catalina");
                        caps.setCapability("name", scenario.getName());
                        break;
                }
                break;
            case "iOS":
                switch(browserName){
                    case "Safari":
                        caps.setCapability("browserName", "Safari");
                        caps.setCapability("device", "iPhone 12");
                        caps.setCapability("realMobile", "true");
                        caps.setCapability("os_version", "14");
                        caps.setCapability("name", scenario.getName());
                        break;
                }
                break;
            case "Android":
                switch(browserName){
                    case "Chrome":
                        //caps.setCapability("browserName", "Chrome");
                        caps.setCapability("device", "Samsung Galaxy S23");
                        //caps.setCapability("realMobile", "true");
                        caps.setCapability("app","com.pnc.ecommerce.mobile");
                        caps.setCapability("os_version", "13.0");
                        caps.setCapability("name", scenario.getName());
                        break;
                }
                break;
        }
        driver = new RemoteWebDriver(new URL(url), caps);
        getPublicURLFromBS();

    }
public static void getPublicURLFromBS() {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            Object response = jse.executeScript("browserstack_executor: {\"action\": \"getSessionDetails\"}");
            Gson gson = new Gson();
            Map<String, String> result = gson.fromJson((String) response, Map.class);
            publicURL = result.get("public_url");
            System.out.println("session id -- before " + publicURL);
        }
}

//        Navigating through the URL
//        driver.get("http://www.google.com");
//        WebElement element = driver.findElement(By.name("q"));
//        element.sendKeys("BrowserStack");
//        element.submit();
//        System.out.println(driver.getTitle());
//        driver.quit();
