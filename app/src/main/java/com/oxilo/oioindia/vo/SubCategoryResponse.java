package com.oxilo.oioindia.vo;

/**
 * Created by nikk on 28/10/17.
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "message",
        "result"
})
public class SubCategoryResponse {

    @JsonProperty("message")
    private String message;
    @JsonProperty("result")
    private List<SubCategory> result = null;

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("result")
    public List<SubCategory> getResult() {
        return result;
    }

    @JsonProperty("result")
    public void setResult(List<SubCategory> result) {
        this.result = result;
    }

}
