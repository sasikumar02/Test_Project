package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        dryRun = false,
        features = "src/test/java/features",
        glue="step_definitions"   ,
        tags = "@Regression",
        plugin={"report.CustomReportListener","html:target/Extent-Report/extent.html"}
)

public class TestRunner{
}
