package com.ericsson.eea.inv.jbehave.steps;

import java.util.Stack;

import com.ericsson.eea.inv.jbehave.utiles.SomeUtility;
import com.ericsson.eea.inv.jbehave.utiles.Utility;
import org.jbehave.core.annotations.*;
import junit.framework.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StackSteps {

    private Stack<String> testStack;
    private String searchElement;

    @Autowired
    Utility someUtility;
 
    @Given("an empty stack")
    public void anEmptyStack() {
        testStack = new Stack<String>();
    }
    
    @When("the string $element is added")
    public void anElementIsAdded(String element) {
        testStack.push(element);
    }
 
    @When("the last element is removed again")
    public void removeLastElement() {
        testStack.pop();
    }

    @When("the element $element is searched for")
    public void searchForElement(String element) {
        searchElement = element;
    }
    
    @Then("the resulting element should be $result")
    public void theResultingElementShouldBe(String result) {
    	Assert.assertEquals(testStack.pop(), result);
    }
    
    @Then("the position returned should be $pos")
    public void thePositionReturnedShouldBe(int pos) {
    	Assert.assertEquals(testStack.search(searchElement), pos);
        someUtility.printSomeMessage();
    }
}
