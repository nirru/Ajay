package com.oxilo.oioindia.view.fragments;


import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oxilo.oioindia.R;
import com.oxilo.oioindia.binding.FragmentDataBindingComponent;
import com.oxilo.oioindia.databinding.FragmentAllBinding;
import com.oxilo.oioindia.databinding.FragmentTopBinding;
import com.oxilo.oioindia.utils.AutoClearedValue;
import com.oxilo.oioindia.view.activity.MainActivity;
import com.oxilo.oioindia.view.adapter.CategoryListAdapter;
import com.oxilo.oioindia.view.adapter.TopListAdapter;
import com.oxilo.oioindia.view.common.NavigationController;
import com.oxilo.oioindia.viewmodal.AllViewModal;
import com.oxilo.oioindia.viewmodal.SubViewModal;
import com.oxilo.oioindia.vo.Category;
import com.oxilo.oioindia.vo.CategoryResponse;

import java.util.ArrayList;

import io.reactivex.functions.Consumer;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopFragment extends LifecycleFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private ArrayList<Category> mParam1;
    private String mParam2;

    android.databinding.DataBindingComponent dataBindingComponent;

    AllViewModal viewModal;
    private TopListAdapter topListAdapter;
    private AutoClearedValue<TopListAdapter> adapter;
    private AutoClearedValue<FragmentTopBinding> binding;
    private FragmentTopBinding databinding;


    public TopFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TopFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TopFragment newInstance(ArrayList<Category> param1, String param2) {
        TopFragment fragment = new TopFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getParcelableArrayList(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        databinding = DataBindingUtil.bind(inflater.inflate(R.layout.fragment_top, container, false));

        dataBindingComponent = new FragmentDataBindingComponent(this);

        binding = new AutoClearedValue<>(this, databinding);


        return databinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        AllViewModal.Factory factory = new AllViewModal.Factory(getActivity().getApplication());
        viewModal = ViewModelProviders.of(this,factory).get(AllViewModal.class);

        topListAdapter = new TopListAdapter(dataBindingComponent, false, new TopListAdapter.RepoClickCallback() {
            @Override
            public void onClick(Category repo) {
                NavigationController navigationController = new NavigationController((MainActivity) getActivity());
                navigationController.navigateToSubCategory(repo.getServiceID(),repo);

            }
        });
        databinding.categorylist.setLayoutManager(new GridLayoutManager(getContext(),3));
        databinding.categorylist.setAdapter(topListAdapter);

        this.adapter = new AutoClearedValue<>(this, topListAdapter);
        initCategory();
    }

    private void initCategory(){
//        viewModal.getCategory("2").subscribe(new Consumer<CategoryResponse>() {
//            @Override
//            public void accept(CategoryResponse repos) throws Exception {
//                if (repos == null) {
//                    adapter.get().replace(null);
//                } else {
//                    adapter.get().replace(repos.getResult());
//                }
//            }
//        }, new Consumer<Throwable>() {
//            @Override
//            public void accept(Throwable throwable) throws Exception {
//
//                throwable.printStackTrace();
//            }
//        });

        if (mParam1 == null) {
            adapter.get().replace(null);
        } else {
            adapter.get().replace(mParam1);
        }
    }

}
