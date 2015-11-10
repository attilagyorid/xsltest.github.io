package com.ericsson.eea.inv.jbehave.reporters;

import com.ericsson.eea.inv.jbehave.reporters.util.CustomStepCollector;
import com.ericsson.eea.inv.jbehave.reporters.util.ImageProcessor;
import org.jbehave.core.model.*;
import org.jbehave.core.reporters.StoryReporter;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class FailedStepsReporter implements StoryReporter {
    private String failedScenario;

    public FailedStepsReporter() {
    }

    @Override
    public void storyNotAllowed(Story story, String s) {

    }

    @Override
    public void storyCancelled(Story story, StoryDuration storyDuration) {

    }

    @Override
    public void beforeStory(Story story, boolean b) {

    }

    @Override
    public void afterStory(boolean b) {

    }

    @Override
    public void narrative(Narrative narrative) {

    }

    @Override
    public void lifecyle(Lifecycle lifecycle) {

    }

    @Override
    public void scenarioNotAllowed(Scenario scenario, String s) {

    }

    @Override
    public void beforeScenario(String s) {
        failedScenario = s;
    }

    @Override
    public void scenarioMeta(Meta meta) {

    }

    @Override
    public void afterScenario() {
        failedScenario = "";
    }

    @Override
    public void givenStories(GivenStories givenStories) {

    }

    @Override
    public void givenStories(List<String> list) {

    }

    @Override
    public void beforeExamples(List<String> list, ExamplesTable examplesTable) {

    }

    @Override
    public void example(Map<String, String> map) {

    }

    @Override
    public void afterExamples() {

    }

    @Override
    public void beforeStep(String s) {

    }

    @Override
    public void successful(String s) {

    }

    @Override
    public void ignorable(String s) {

    }

    @Override
    public void pending(String s) {

    }

    @Override
    public void notPerformed(String s) {

    }

    @Override
    public void failed(String s, Throwable throwable) {
        CustomStepCollector.addFailedStepToMap(failedScenario,s,throwable);
        try {
            ImageProcessor.takeScreenshot(failedScenario + "_" + s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failedOutcomes(String s, OutcomesTable outcomesTable) {

    }

    @Override
    public void restarted(String s, Throwable throwable) {

    }

    @Override
    public void restartedStory(Story story, Throwable throwable) {

    }

    @Override
    public void dryRun() {

    }

    @Override
    public void pendingMethods(List<String> list) {

    }

    public String getFailedScenario() {
        return failedScenario;
    }

    public void setFailedScenario(String failedScenario) {
        this.failedScenario = failedScenario;
    }

}
