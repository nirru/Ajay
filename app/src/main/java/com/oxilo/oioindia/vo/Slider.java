package com.oxilo.oioindia.vo;

/**
 * Created by nikk on 28/10/17.
 */

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "message",
        "result"
})
public class Slider {

    @JsonProperty("message")
    private String message;
    @JsonProperty("result")
    private List<ImageSlider> result = null;

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("result")
    public List<ImageSlider> getResult() {
        return result;
    }

    @JsonProperty("result")
    public void setResult(List<ImageSlider> result) {
        this.result = result;
    }

}
