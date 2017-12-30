package com.oxilo.oioindia.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.oxilo.oioindia.AppController;
import com.oxilo.oioindia.R;
import com.oxilo.oioindia.binding.BindingCity;
import com.oxilo.oioindia.binding.IDataChangeListener;
import com.oxilo.oioindia.databinding.ActivitySplashBinding;
import com.oxilo.oioindia.modal.City;
import com.oxilo.oioindia.modal.CityResponse;
import com.oxilo.oioindia.repositary.main.MainRequestManager;
import com.oxilo.oioindia.view.adapter.SpinnerAdapter;
import com.oxilo.oioindia.vo.Splash;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

public class SplashActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    ActivitySplashBinding binding;

    private List<City> city_;

    private static final String BUNDLE_SELECTED_CITY = "bundle_selected_planet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
          binding.setSplash(new Splash(this));

        String login = AppController.getInstance().getAppPrefs().getObject("LOGIN", String.class);
        if (login != null) {
            Intent i = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(i);
            finish();
        } else {
            Intent i = new Intent(SplashActivity.this,LoginActivity.class);
            startActivity(i);
            finish();
        }


//        MainRequestManager.getInstance(getApplicationContext()).getCity().subscribe(new Consumer<CityResponse>() {
//            @Override
//            public void accept(CityResponse cityResponse) throws Exception {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        Intent i = new Intent(SplashActivity.this,LoginActivity.class);
//                        i.putParcelableArrayListExtra("A",cityResponse.getResult());
//                        startActivity(i);
//                    }
//                }, 1000);
//
//            }
//        }, new Consumer<Throwable>() {
//            @Override
//            public void accept(Throwable throwable) throws Exception {
//
//                throwable.printStackTrace();
//            }
//        });


//        binding.setSpinAdapterCity(new SpinnerAdapter(SplashActivity.this, R.layout.spinner_row, city_));
//        City selectedCity = savedInstanceState != null ? savedInstanceState.<City> getParcelable(BUNDLE_SELECTED_CITY)
//                : city_.get(3);//initial selected planet is Earth, 3 is the index of Earth after a blank item inserted
//        binding.setBindingCity(new BindingCity(this, selectedCity));

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
//    public native String stringFromJNI();

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putParcelable(BUNDLE_SELECTED_CITY, binding.getBindingCity().obvSelectedCity_.get());
//    }
//    @Override
//    public void onEditTextChanged(String planetName) {
//
//    }
}
