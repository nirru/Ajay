package com.oxilo.oioindia.binding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.oxilo.oioindia.R;
import com.squareup.picasso.Picasso;

/**
 * Created by nikk on 29/10/17.
 */

public class ImageBindingAdapter {

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        if (!url.equals("") && url != null) {
            try {
                Picasso.with(imageView.getContext()).load(url).placeholder(R.mipmap.ic_launcher_round).fit().centerCrop().into(imageView);
            }catch (Exception ex){
                ex.printStackTrace();
            }

        }
    }


}
