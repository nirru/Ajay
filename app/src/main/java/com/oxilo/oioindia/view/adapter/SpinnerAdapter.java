package com.oxilo.oioindia.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.oxilo.oioindia.R;
import com.oxilo.oioindia.modal.City;

import java.util.List;


/**
 * Created by nikk on 7/7/17.
 */

public class SpinnerAdapter extends ArrayAdapter<City>
{
    private List<City> mCategories;
    private int[] mIcons;

    public SpinnerAdapter(@NonNull Context context, int resource, @NonNull List<City> objects) {
        super(context, resource, objects);
        this.mCategories = objects;
    }

//    public SpinnerAdapter(Context context, int layoutResourceId, String[] categories)
//    {
//        super(context, layoutResourceId, categories);
//        mCategories = categories;
//
//        // Add the same icon to all items, just for testing.
////        mIcons = new int[mCategories.length];
////        for (int i = 0; i < mIcons.length; i++)
////        {
////            mIcons[i] = R.drawable.my_icon;
////        }
//    }

    /**
     * View for a dropdown item.
     * */
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {
        View rowView = convertView;

        if (rowView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            rowView = inflater.inflate(R.layout.spinner_row, parent, false);
        }

        TextView categoryText = (TextView) rowView.findViewById(R.id.my_spinner_item_text);
        categoryText.setText(mCategories.get(position).getCityname());

//        ImageView icon = (ImageView) rowView.findViewById(R.id.my_spinner_dropdown_item_icon);
//        icon.setImageResource(mIcons[position]);

        return rowView;
    }

    /**
     * The Spinner View that is selected and shown in the *Spinner*, i.e. not the dropdown item.
     * */
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View spinnerView = convertView;

        if (spinnerView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            spinnerView = inflater.inflate(R.layout.spinner_row, parent, false);
        }

        TextView categoryText = (TextView) spinnerView.findViewById(R.id.my_spinner_item_text);
        categoryText.setText(mCategories.get(position).getCityname());

        return spinnerView;
    }

}
