package com.ericsson.eea.inv.jbehave.steps;

import org.jbehave.core.annotations.Then;
import org.springframework.stereotype.Component;

/**
 * Created by Attila on 2015.10.25..
 */
@Component
public class AdditionalSteps {

    public final String name = "hello";
    @Then("we use and additional method")
    public void additionalMethod() {
        System.out.println("Additional Method");
    }
}
