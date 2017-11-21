package com.oxilo.oioindia.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.oxilo.oioindia.R;
import com.oxilo.oioindia.modal.City;
import com.oxilo.oioindia.view.adapter.CityListAdapter;

import java.util.ArrayList;

public class CityActivity extends AppCompatActivity {

    ArrayList<City> cityArrayList;
    CityListAdapter cityListAdapter;
    RecyclerView recyclerView;
    City mySeletedCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        if (getIntent()!=null){
            cityArrayList = getIntent().getParcelableArrayListExtra("A");
        }
        else {
            cityArrayList = new ArrayList<>();
        }

        TextView go = (TextView)findViewById(R.id.go);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CityActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        cityListAdapter = new CityListAdapter(cityArrayList,this);
        recyclerView = (RecyclerView)findViewById(R.id.recylerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(cityListAdapter);

        cityListAdapter.setOnItemClickListener(new CityListAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                try {
                    CityListAdapter.mSelectedItem = position;
                    cityListAdapter.notifyItemRangeChanged(0, cityListAdapter.dataSet.size());

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
