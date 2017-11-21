package com.oxilo.oioindia.vo;

/**
 * Created by nikk on 28/10/17.
 */

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "message",
        "result"
})
public class CategoryResponse {

    @JsonProperty("message")
    private String message;
    @JsonProperty("result")
    private ArrayList<Category> result = null;

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("result")
    public ArrayList<Category> getResult() {
        return result;
    }

    @JsonProperty("result")
    public void setResult(ArrayList<Category> result) {
        this.result = result;
    }

}
