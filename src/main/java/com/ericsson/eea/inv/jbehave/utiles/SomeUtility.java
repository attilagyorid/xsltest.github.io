package com.ericsson.eea.inv.jbehave.utiles;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by eattgyo on 2015.10.27..
 */
@Component
public class SomeUtility implements Utility {

    @Value("${injected.value}")
    private String injectedValue;

    public void printSomeMessage () {
        System.out.println("This is a message by an autowired class method! And this is the value of the injected property: " + injectedValue);
    }
}
