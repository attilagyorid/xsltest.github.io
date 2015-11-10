package com.ericsson.eea.inv.jbehave.steps;

import com.ericsson.eea.inv.jbehave.pages.FacebookLoginPage;
import com.ericsson.eea.inv.jbehave.pages.FacebookMainPage;
import com.ericsson.eea.inv.jbehave.reporters.util.ImageProcessor;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by Attila on 2015.10.31..
 */
@Component
public class FacebookSignUpSteps {

    @Autowired
    FacebookMainPage facebookMainPage;
    @Autowired
    FacebookLoginPage facebookLoginPage;

    @Given("Browser Running with loaded site $url having header $header")
    public void loadSiteWithHeader(@Named("url") String url,@Named("header") String header ) throws IOException, URISyntaxException {
        facebookMainPage.setPageURL(url);
        facebookMainPage.setPageTitle(header);
        facebookMainPage.loadPage();
        ImageProcessor.takeScreenshotOfWebElementAndCompareWithExpected(facebookMainPage.getButtonLogin(), "loginButton");
    }

    @When("Email field is filed by $email")
    public void enterEmail(@Named("email") String email) {
        facebookMainPage.setTextEmailMobileField(email);
    }

    @When("Login is pressed")
    public void loginPressed() {
        facebookMainPage.clickLoginMain();
    }


    @Then("Page Redirected to site with error header $erroHeaderValue")
    public void redirectedWithErrorHeader(@Named("erroHeaderValue") String erroHeaderValue) {
        assertTrue(facebookLoginPage.checkErrorHeader(erroHeaderValue));
    }

}
