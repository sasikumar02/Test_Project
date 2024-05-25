package pages;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import common.ConfigLoader;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class BaseClass extends DriverFactory{



    //public static WebDriver driver;
//    public void initializeBrowser(){
//        WebDriverManager.chromedriver().setup();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--remote-allow-origins=*");
//        driver = new ChromeDriver(options);
//    }
public void launchURL(){
        System.out.println("url launched");
        String url= ConfigLoader.getProperty("url");
        driver.get(url);
}

    public void closeBrowser() throws Exception {
        //takeSnapShot(scenario);
        Thread.sleep(2000);
        driver.quit();
    }

    public static Map<String, String> readJsonElement(String fileName,
                                                      String elementName) throws Exception{
        String filePath = System.getProperty("user.dir") + File.separator+"src"+File.separator+
                "test"+File.separator+"resources"+File.separator+ "testData"+File.separator+fileName;
        JsonElement root = new JsonParser().parse(new FileReader(filePath));
        JsonObject jsonObject = root.getAsJsonObject();
        JsonElement some = jsonObject.get(elementName);
        JsonObject testData = some.getAsJsonObject();
        Map<String, String> testDataMap = new HashMap<String, String>();
        for (Map.Entry<String,JsonElement> entry : testData.entrySet()) {
            testDataMap.put(entry.getKey(), entry.getValue().getAsString());
        }
        return testDataMap;
    }

    public static Response postWithoutAppURL(String url, String body) {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addHeader("Content-Type", ContentType.JSON.toString());
        builder.addHeader("accept", ContentType.JSON.toString());
        builder.setBody(body);
        builder.log(LogDetail.ALL);
        RequestSpecification requestSpec = builder.build();
        return RestAssured
                .expect()
                .given()
                .spec(requestSpec)
                .post(url);
    }

    public static Response put(String url, String appURL, String body) {
        return RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(body)
                .put(url);
    }
    public static String takeSnapShot(String screenshotName) throws Exception{
        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] source = ts.getScreenshotAs(OutputType.BYTES);
        String dest = "D:/Demo_Project/target/Extent-Report/" + screenshotName + ".png";
        File destination = new File(dest);
        try (InputStream inputStream = new ByteArrayInputStream(source)) {
            FileUtils.copyInputStreamToFile(inputStream, destination);
        }
        return dest;
    }
}
