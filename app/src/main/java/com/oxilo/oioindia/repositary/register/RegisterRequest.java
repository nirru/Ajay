package com.oxilo.oioindia.repositary.register;

/**
 * Created by ManuelVivo on 03/10/15.
 */
public class RegisterRequest {

    private String fname;
    private String lname;
    private String email;
    private String password;
    private String mobile;

    public RegisterRequest(String fname,String lname,String email, String password,String mobile) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getMobile() {
        return mobile;
    }
}
