package com.oxilo.oioindia.view.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Service;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.oxilo.oioindia.R;
import com.oxilo.oioindia.databinding.PagerItemBinding;
import com.oxilo.oioindia.vo.ImageSlider;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by Wasim on 11-06-2015.
 */
public class ImagePagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<ImageSlider> mResources;



    public ImagePagerAdapter(Context mContext, List<ImageSlider> mResources ) {
        this.mContext = mContext;
        this.mResources = mResources;
    }

    @Override
    public int getCount() {
        return mResources.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        PagerItemBinding binding = PagerItemBinding.inflate(LayoutInflater.from(mContext),container,false);

        if (!mResources.get(position).getImage().toString().trim().equals(""))
        Picasso.with(mContext)
                .load(mResources.get(position).getImage())
                .fit()
                .centerInside()
                .into(binding.imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {

                    }
                });

        container.addView(binding.getRoot());

        return binding.getRoot();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public  void showProgress(final boolean show, final View mainView, final View mProgressView) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        try{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                int shortAnimTime = mContext.getResources().getInteger(android.R.integer.config_shortAnimTime);

                mainView.setVisibility(show ? View.GONE : View.VISIBLE);
                mainView.animate().setDuration(shortAnimTime).alpha(
                        show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mainView.setVisibility(show ? View.GONE : View.VISIBLE);
                    }
                });

                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                mProgressView.animate().setDuration(shortAnimTime).alpha(
                        show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                    }
                });
            } else {
                // The ViewPropertyAnimator APIs are not available, so simply show
                // and hide the relevant UI components.
                mainView.setVisibility(show ? View.VISIBLE : View.GONE);
                mainView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
