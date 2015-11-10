package com.ericsson.eea.inv.jbehave.steps;

import com.ericsson.eea.inv.jbehave.pages.FacebookMainPage;
import com.ericsson.eea.inv.jbehave.reporters.util.CustomStepCollector;
import com.ericsson.eea.inv.jbehave.reporters.util.FileIOHelper;
import org.apache.commons.io.FileUtils;
import org.jbehave.core.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * Created by Attila on 2015.10.25..
 */
@Component
public class BeforeAfterStoriesSteps {

    @Autowired
    FacebookMainPage facebookMainPage;
    @Autowired
    FileIOHelper fileIOHelper;

    @BeforeScenario
    public void beforeEachScenario() {

        System.out.println("***New Scenario Begins***");

    }

    @AfterScenario
    public void afterAnyScenario() {

        System.out.println("***Scenario Ends***");
    }

    @BeforeStory
    public void beforeStory() {

        System.out.println("***New Story Begins***");

    }

    @AfterStory
    public void afterStory() {

        System.out.println("***Story Ends***");

    }

    @BeforeStories
    public void beforeStories() throws IOException {
        System.out.println("***STORIES start***");
        fileIOHelper.deleteAndRecreateFolder("jbehave-screenShotsOnError");
    }

    @AfterStories
    public void afterStories() {

        facebookMainPage.closeBrowser();
        System.out.println("***STORIES end***");
        CustomStepCollector.printFailedStepMapContent();

    }
}
