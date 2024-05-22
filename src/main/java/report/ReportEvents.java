package report;

import io.cucumber.plugin.event.Result;

import java.util.List;

public interface ReportEvents {

    void testRunStart();
    void testScenarioStart(String scenarioName, List<String> tags);
    void testStepStart(String stepName);
    void testStepFinish(Result result);
    void testScenarioFinish();
    void testRunFinish() throws InterruptedException;

}
