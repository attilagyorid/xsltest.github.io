package com.ericsson.eea.inv.jbehave.reporters.util;

import java.util.HashMap;
import java.util.Map;


public final class CustomStepCollector {

    private static final String FAILED_RESULT_HEADER = "----------------- Failed Result -------------------";
    public static final String FAILED_SCENARIO_PREFIX = "---Scenario: ";
    public static final String FAILED_STEP_PREFIX = "    ---Step: ";
    public static final String FAILED_STEP_EXCEPTION_PREFIX = "        ---Trace: ";
    private static Map<String, Map<String,Throwable>> failedStepsList = new HashMap<String, Map<String,Throwable>>();

    private CustomStepCollector() {
    }

    public static void addFailedStepToMap (String scenarioName, String stepName, Throwable stepException) {
        Map<String, Throwable> stepNameAndException = new HashMap<String, Throwable>();
        stepNameAndException.put(stepName,stepException);

        failedStepsList.put(scenarioName, stepNameAndException);
    }


    public static void printFailedStepMapContent () {
        if (!failedStepsList.isEmpty()) {
            System.out.println(FAILED_RESULT_HEADER);
            failedStepsList.entrySet().stream().forEach(e -> {
                System.out.println("");
                System.out.println(FAILED_SCENARIO_PREFIX + e.getKey()); e.getValue().entrySet().stream().forEach(i -> {
                    System.out.println(FAILED_STEP_PREFIX + i.getKey());
                    System.out.println(FAILED_STEP_EXCEPTION_PREFIX);
                    i.getValue().printStackTrace();
                    System.out.println("");
                });
            });
        }
    }
    public static Map<String, Map<String, Throwable>> getFailedStepsList() {
        return failedStepsList;
    }


}
