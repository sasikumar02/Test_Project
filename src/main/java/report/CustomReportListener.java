package report;

import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.*;
import pages.DriverFactory;

import java.util.ArrayList;
import java.util.List;

public class CustomReportListener implements EventListener{
private List<ReportEvents> reportEventsList=new ArrayList<>();
    public CustomReportListener(){
        reportEventsList.add(new ExtendCucumberReport());
    }

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        eventPublisher.registerHandlerFor(TestRunStarted.class,this::testRunStarted);
        eventPublisher.registerHandlerFor(TestCaseStarted.class,this::testCaseStarted);
        eventPublisher.registerHandlerFor(TestStepStarted.class,this::testStepStarted);
        eventPublisher.registerHandlerFor(TestStepFinished.class,this::testStepFinished);
        eventPublisher.registerHandlerFor(TestCaseFinished.class,this::testCaseFinished);
        eventPublisher.registerHandlerFor(TestRunFinished.class,this::testRunFinished);
    }


    private  void testRunStarted(TestRunStarted testRunStarted) {
        reportEventsList.forEach(ReportEvents::testRunStart);
    }
    private void testCaseStarted(TestCaseStarted testCaseStarted) {
        TestCase testCase = testCaseStarted.getTestCase();
        String scenarioName = testCase.getName();
        List<String> tags = testCase.getTags();
        reportEventsList.forEach(reportEvents -> reportEvents.testScenarioStart(scenarioName,tags));

    }
    private void testStepStarted(TestStepStarted testStepStarted) {
        if(testStepStarted.getTestStep() instanceof PickleStepTestStep){
            Step step = ((PickleStepTestStep) testStepStarted.getTestStep()).getStep();
            String stepName = step.getKeyword() + " " + step.getText();
            reportEventsList.forEach(reportEvents -> reportEvents.testStepStart(stepName));
        }
        
    }
    private void testStepFinished(TestStepFinished testStepFinished) {
        if(testStepFinished.getTestStep() instanceof PickleStepTestStep){
            Result result = testStepFinished.getResult();
            reportEventsList.forEach(reportEvents -> reportEvents.testStepFinish(result));
        }
    }
    private void testCaseFinished(TestCaseFinished testCaseFinished) {
        reportEventsList.forEach(reportEvents -> reportEvents.testScenarioFinish());
    }
    private void testRunFinished(TestRunFinished testRunFinished){
        reportEventsList.forEach(reportEvents -> {
            try {
                reportEvents.testRunFinish();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
