package com.oxilo.oioindia.repositary.login;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ManuelVivo on 03/10/15.
 */
public class LoginRequest {

    private String email;
    private String password;

    public LoginRequest(String nickname, String password) {
        this.email = nickname;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
