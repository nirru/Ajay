package com.oxilo.oioindia.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.oxilo.oioindia.R;
import com.oxilo.oioindia.modal.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Created by ericbasendra on 02/12/15.
 */
public class CityListAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private Context mContext;
    public ArrayList<T> dataSet;
    public  ArrayList<T> filteredProductList;
    private static MyClickListener myClickListener;

//    public static CheckBox lastChecked = null;
//    public static int lastCheckedPos = 0;

    public static int mSelectedItem = -1;
    public CityListAdapter(ArrayList<T> productLists, Context mContext) {
        this.mContext = mContext;
        this.dataSet = productLists;
        this.filteredProductList = new ArrayList<>();
    }
    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }
    public void addItems(@NonNull List<T> newDataSetItems) {
        filteredProductList.addAll(newDataSetItems);
    }

    public void animateTo(List<T> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }


    private void applyAndAnimateRemovals(List<T> newModels) {
        for (int i = dataSet.size() - 1; i >= 0; i--) {
            final T model = dataSet.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }


    private void applyAndAnimateAdditions(List<T> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final T model = newModels.get(i);
            if (!dataSet.contains(model)) {
                addItem(i, model);
            }
        }
    }


    private void applyAndAnimateMovedItems(List<T> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final T model = newModels.get(toPosition);
            final int fromPosition = dataSet.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public void addItem(T item) {
        if (!dataSet.contains(item)) {
            dataSet.add(item);
            notifyItemInserted(dataSet.size() - 1);
        }
    }

    public void addItem(int position, T model) {
        dataSet.add(position, model);
        notifyItemInserted(position);
    }

    public void removeItem(T item) {
        int indexOfItem = dataSet.indexOf(item);
        if (indexOfItem != -1) {
            this.dataSet.remove(indexOfItem);
            notifyItemRemoved(indexOfItem);
        }
    }

    public T removeItem(int position) {
        final T model = dataSet.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void moveItem(int fromPosition, int toPosition) {
        final T model = dataSet.remove(fromPosition);
        dataSet.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

    public T getItem(int index) {
        if (dataSet != null && dataSet.get(index) != null) {
            return dataSet.get(index);
        } else {
            throw new IllegalArgumentException("Item with index " + index + " doesn't exist, dataSet is " + dataSet);
        }
    }


    @Override
    public int getItemViewType(int position) {
        return dataSet.get(position)!=null? VIEW_ITEM: VIEW_PROG;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh = null;
        if (viewType == VIEW_ITEM){
            View itemView = LayoutInflater.
                    from(parent.getContext()).
                    inflate(R.layout.city_row, parent, false);
            vh = new ProductViewHolder(itemView);
        }
        else if(viewType == VIEW_PROG){
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.progress_item, parent, false);
            vh = new ProgressViewHolder(v);
        }else {
            throw new IllegalStateException("Invalid type, this type ot items " + viewType + " can't be handled");
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ProductViewHolder){
            T dataItem = dataSet.get(position);
            ((ProductViewHolder) holder).name.setText(((City)dataItem).getCityname().toString());
            ((ProductViewHolder)holder).radioButton.setChecked(position == mSelectedItem);

        }else{
            ((ProgressViewHolder)holder).progressBar.setIndeterminate(true);
        }

    }

    @Override
    public int getItemCount() {
        if (dataSet!=null)
            return dataSet.size();
        else
            return 0;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        dataSet.clear();
        if (charText.length() == 0) {
            dataSet.addAll(filteredProductList);
        }
        else
        {
            for (T cI : filteredProductList)
            {
                if (((City)cI).getCityname().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    dataSet.add(cI);

                }
            }
        }
        notifyDataSetChanged();
    }

      // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        RadioButton radioButton;
        public ProductViewHolder(View itemView) {
            super(itemView);
            radioButton = (RadioButton) itemView.findViewById(R.id.radio);
            name = (TextView)itemView.findViewById(R.id.city_name);
            radioButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            try{
                if(myClickListener!=null){
                myClickListener.onItemClick(getLayoutPosition(), view);
                }else{
                    Toast.makeText(view.getContext(),"Click Event Null",Toast.LENGTH_SHORT).show();
                }
            }catch(NullPointerException e){
                Toast.makeText(view.getContext(),"Click Event Null Ex",Toast.LENGTH_SHORT).show();
            }
        }
    }


    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;
        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar)v.findViewById(R.id.progress_bar);
        }
    }

    /**
     * y Custom Item Listener
     */

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}