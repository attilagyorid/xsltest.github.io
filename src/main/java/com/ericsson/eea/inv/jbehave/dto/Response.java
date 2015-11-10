package com.ericsson.eea.inv.jbehave.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by eattgyo on 2015.11.03..
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {

    public Response() {
    }
    private String one;

    private String key;

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Response{" +
                "one='" + one + '\'' +
                ", key='" + key + '\'' +
                '}';
    }

}
