package com.oxilo.oioindia.repositary.forgotpassword;

/**
 * Created by ManuelVivo on 03/10/15.
 */
public class ForgetRequest {

    private String mobile;
    private String otp;
    private String userID;
    private String password;
    private String cPassword;

    public String getMobile() {
        return mobile;
    }

    public ForgetRequest(String mobile) {
        this.mobile = mobile;
    }

    public ForgetRequest(String mobile,String otp) {
        this.mobile = mobile;
        this.otp = otp;
    }

    public ForgetRequest(String userID,String password,String cPaasword) {
        this.userID = userID;
        this.password = password;
        this.cPassword = cPaasword;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getcPassword() {
        return cPassword;
    }

    public void setcPassword(String cPassword) {
        this.cPassword = cPassword;
    }

}
