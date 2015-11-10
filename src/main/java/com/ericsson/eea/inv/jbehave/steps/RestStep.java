package com.ericsson.eea.inv.jbehave.steps;

import com.ericsson.eea.inv.jbehave.dto.Response;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by eattgyo on 2015.11.03..
 */
@Component
public class RestStep {

    ResponseEntity<Response> response;
    @Autowired
    RestTemplate restTemplate;
    @When("I call rest api with get $url")
    public void callRestApiViaGet(@Named("url") String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);
        response = restTemplate.exchange(url, HttpMethod.GET, entity, Response.class);
    }

    @Then("the response should be $responseCode")
    public void restGetAsQuote(@Named("responseCode") String responseCode) {
        Assert.assertThat(response.getStatusCode().toString(), is(equalTo(responseCode)));
    }
    @Then("the response body is $responseBody")
    public void checkResponse(@Named("responseBody") String responseBody) {
        Assert.assertThat(responseBody,is(equalTo(response.getBody().toString())));
    }
}
