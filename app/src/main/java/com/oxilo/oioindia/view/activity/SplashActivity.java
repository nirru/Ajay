package com.oxilo.oioindia.view.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.location.Address;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.widget.Toast;

import com.github.florent37.rxgps.RxGps;
import com.oxilo.oioindia.AppController;
import com.oxilo.oioindia.R;
import com.oxilo.oioindia.databinding.ActivitySplashBinding;
import com.oxilo.oioindia.vo.Splash;

import io.reactivex.MaybeSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends BaseActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    ActivitySplashBinding binding;
    RxGps rxGps;

    private static final String BUNDLE_SELECTED_CITY = "bundle_selected_planet";
    private static final int GPS_ENABLE_REQUEST = 0x1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        binding.setSplash(new Splash(this));

//          validate();
        rxGps = new RxGps(SplashActivity.this);

        if (Build.VERSION.SDK_INT< Build.VERSION_CODES.M) {
            if (!isGPSEnable()) {
              locationDialog();
            }else{
                getCurrentLocation();
            }
        }
        else
        getCurrentLocation();


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


    @SuppressLint("CheckResult")
    private  void getCurrentLocation() {
        rxGps.locationLowPower()
                .flatMapMaybe(new Function<Location, MaybeSource<? extends Address>>() {
                    @Override
                    public MaybeSource<? extends Address> apply(Location location) throws Exception {
                        return rxGps.geocoding(location);
                    }
                })
                .doOnSubscribe(this::addDisposable)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Address>() {
                    @Override
                    public void accept(Address address) throws Exception {
                        validate(address);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (throwable instanceof RxGps.PermissionException) {
                            SplashActivity.this.displayError(throwable.getMessage());
                        } else if (throwable instanceof RxGps.PlayServicesNotAvailableException) {
                            SplashActivity.this.displayError(throwable.getMessage());
                        }

                        throwable.printStackTrace();
                    }
                });

    }

    private void validate(Address address) {
        String login = AppController.getInstance().getAppPrefs().getObject("LOGIN", String.class);
        if (login != null) {
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            i.putExtra("CITY", "" + address.getLocality());
            i.putExtra("ADDRESS", "" + getAddressText(address));
            startActivity(i);
            finish();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    i.putExtra("CITY", "" + address.getLocality());
                    i.putExtra("ADDRESS", "" + getAddressText(address));
                    startActivity(i);
                    finish();
                }
            }, 3000);
        }
    }

    public void displayError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private String getAddressText(Address address) {
        String addressText = "";
        final int maxAddressLineIndex = address.getMaxAddressLineIndex();
        for (int i = 0; i <= maxAddressLineIndex; i++) {
            addressText += address.getAddressLine(i);
            if (i != maxAddressLineIndex) {
                addressText += "\n";
            }
        }

        return addressText;
    }

    private void locationDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(SplashActivity.this);

        // Setting Dialog Title
        alertDialog.setTitle("GPS is settings");

        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

        // On pressing Settings button
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), GPS_ENABLE_REQUEST);
                dialog.dismiss();
            }
        });

        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        // Showing Alert Message
        alertDialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==GPS_ENABLE_REQUEST){
            if (isGPSEnable())
                getCurrentLocation();
            else
                Toast.makeText(SplashActivity.this,"Location is not turn on ,some of the function might not work properly", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isGPSEnable(){
        return ((LocationManager) getSystemService(Context.LOCATION_SERVICE)).isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
}