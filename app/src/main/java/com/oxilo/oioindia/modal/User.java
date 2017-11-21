package com.oxilo.oioindia.modal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"message",
"result"
})
public class User {

@JsonProperty("message")
private String message;
@JsonProperty("result")
private String result;

@JsonProperty("message")
public String getMessage() {
return message;
}

@JsonProperty("message")
public void setMessage(String message) {
this.message = message;
}

@JsonProperty("result")
public String getResult() {
return result;
}

@JsonProperty("result")
public void setResult(String result) {
this.result = result;
}

}