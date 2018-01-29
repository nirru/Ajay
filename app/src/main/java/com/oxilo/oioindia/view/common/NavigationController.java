/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.oxilo.oioindia.view.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


import com.oxilo.oioindia.R;
import com.oxilo.oioindia.view.activity.MainActivity;
import com.oxilo.oioindia.view.fragments.BusinessDetailFragment;
import com.oxilo.oioindia.view.fragments.BusinessListFragment;
import com.oxilo.oioindia.view.fragments.LocationFragment;
import com.oxilo.oioindia.view.fragments.MainFragment;
import com.oxilo.oioindia.view.fragments.SubCategorieFragment;
import com.oxilo.oioindia.vo.Category;
import com.oxilo.oioindia.vo.SubCategory;


/**
 * A utility class that handles navigation in {@link MainActivity}.
 */
public class NavigationController {
    private final int containerId;
    private final FragmentManager fragmentManager;

    public NavigationController(MainActivity mainActivity) {
        this.containerId = R.id.content;
        this.fragmentManager = mainActivity.getSupportFragmentManager();
    }

    public void navigateToMain(String city,String adress) {
        String tag = "repo" + "/"  + "/" + "main";
        MainFragment searchFragment =  MainFragment.newInstance(city,adress);
        fragmentManager.beginTransaction()
                .replace(containerId, searchFragment,tag)
                .commitAllowingStateLoss();
    }

    public void navigateToLocation(String address, String city) {
        LocationFragment fragment = LocationFragment.newInstance(address, city);
        String tag = "repo" + "/"  + "/" + "location";
        String hide = "repo" + "/"  + "/" + "main";
        fragmentManager.beginTransaction()
                .hide(fragmentManager.findFragmentByTag(hide))
                .add(containerId, fragment, tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    public void navigateToSubCategory(String serviceId, Category name) {
        SubCategorieFragment fragment = SubCategorieFragment.newInstance(serviceId, name);
        String tag = "repo" + "/"  + "/" + "subcategory";
        String hide = "repo" + "/"  + "/" + "main";
        fragmentManager.beginTransaction()
                .hide(fragmentManager.findFragmentByTag(hide))
                .add(containerId, fragment, tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    public void navigateToBusinessListing(String cat_id, SubCategory name) {
        BusinessListFragment fragment = BusinessListFragment.newInstance(cat_id, name);
        String tag = "repo" + "/"  + "/" + "blist";
        String hide = "repo" + "/"  + "/" + "subcategory";
        fragmentManager.beginTransaction()
                .hide(fragmentManager.findFragmentByTag(hide))
                .add(containerId, fragment, tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    public void navigateToBusinessDetails(String product_id) {
        BusinessDetailFragment fragment = BusinessDetailFragment.newInstance(product_id, "");
        String tag = "repo" + "/"  + "/" + "detail";
        String hide = "repo" + "/"  + "/" + "blist";
        fragmentManager.beginTransaction()
                .hide(fragmentManager.findFragmentByTag(hide))
                .add(containerId, fragment, tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

//    public void navigateToUser(String login) {
//        String tag = "user" + "/" + login;
//        UserFragment userFragment = UserFragment.create(login);
//        fragmentManager.beginTransaction()
//                .replace(containerId, userFragment, tag)
//                .addToBackStack(null)
//                .commitAllowingStateLoss();
//    }
}
