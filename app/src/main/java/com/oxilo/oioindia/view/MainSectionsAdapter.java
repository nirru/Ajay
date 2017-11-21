package com.oxilo.oioindia.view;

/**
 * Created by nikk on 12/2/17.
 */

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by sonal on 7/2/2015.
 */
public class MainSectionsAdapter extends FragmentStatePagerAdapter {

    public static int pos = 0;

    private List<Fragment> myFragments;
    private List<String> list;
    private Context context;
    int oldPosition = -1;
    public MainSectionsAdapter(Context context, FragmentManager fm, List<Fragment> myFrags,List<String> list) {
        super(fm);
        this.myFragments = myFrags;
        this.list = list;
        this.context = context;
    }

    public void updateData(){
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {

        return myFragments.get(position);

    }

    @Override
    public int getItemPosition(Object object) {

        return POSITION_NONE;
    }

    @Override
    public int getCount() {

        return myFragments.size();
    }



    @Override
    public CharSequence getPageTitle(int position) {

        setPos(position);

        String PageTitle = "";


        return list.get(position);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
    }




    public static int getPos() {
        return pos;
    }

    public static void setPos(int pos) {
        MainSectionsAdapter.pos = pos;
    }


}
