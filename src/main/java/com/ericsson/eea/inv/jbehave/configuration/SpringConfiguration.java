package com.ericsson.eea.inv.jbehave.configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.client.RestTemplate;


/**
 * Created by eattgyo on 2015.10.28..
 */
@Configuration
@ComponentScan({"com.ericsson.eea.inv.jbehave"})
@PropertySource("classpath:config.properties")
public class SpringConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SpringConfiguration.class);

    @Bean
//    @Profile({ "firefox", "chrome" })
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    @Bean
    public RestTemplate getRestTemplate () {
        return new RestTemplate();
    }

    @Bean
//    @Profile("firefox")
    public WebDriver getFirefoxWebDriver() {
        log.debug("Firefox in use.");
        return new FirefoxDriver();
    }

//    @Bean
//    @Profile("chrome")
//    public WebDriver getChromeWebDriver() {
//        log.debug("Chrome in use.");
//        return new FirefoxDriver();
//    }


}
