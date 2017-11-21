package com.oxilo.oioindia.handlers;

import android.content.Context;
import android.view.View;

import com.oxilo.oioindia.repositary.forgotpassword.ForgetRequestManager;
import com.oxilo.oioindia.view.ForgetInterface;
import com.oxilo.oioindia.view.activity.MainActivity;

public class MyHandlers {

    ForgetInterface forgetInterface;
    Context _mContext;
    public MyHandlers(ForgetInterface forgetInterface, Context _context){
        this.forgetInterface = forgetInterface;
        this._mContext = _context;
    }


}