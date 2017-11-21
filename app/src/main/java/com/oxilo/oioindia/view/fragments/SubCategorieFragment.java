package com.oxilo.oioindia.view.fragments;


import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oxilo.oioindia.R;
import com.oxilo.oioindia.binding.FragmentDataBindingComponent;
import com.oxilo.oioindia.databinding.FragmentAllBinding;
import com.oxilo.oioindia.databinding.FragmentSubCategorieBinding;
import com.oxilo.oioindia.utils.AutoClearedValue;
import com.oxilo.oioindia.view.activity.MainActivity;
import com.oxilo.oioindia.view.adapter.CategoryListAdapter;
import com.oxilo.oioindia.view.adapter.SubCategoryListAdapter;
import com.oxilo.oioindia.view.common.NavigationController;
import com.oxilo.oioindia.viewmodal.AllViewModal;
import com.oxilo.oioindia.viewmodal.SubViewModal;
import com.oxilo.oioindia.vo.Category;
import com.oxilo.oioindia.vo.CategoryResponse;
import com.oxilo.oioindia.vo.SubCategory;
import com.oxilo.oioindia.vo.SubCategoryResponse;

import io.reactivex.functions.Consumer;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubCategorieFragment extends LifecycleFragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private Category mParam2;

    android.databinding.DataBindingComponent dataBindingComponent;

    SubViewModal viewModal;
    private SubCategoryListAdapter categoryListAdapter;
    private AutoClearedValue<SubCategoryListAdapter> adapter;
    private AutoClearedValue<FragmentSubCategorieBinding> binding;
    private FragmentSubCategorieBinding databinding;

    public SubCategorieFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SubCategorieFragment newInstance(String param1, Category param2) {
        SubCategorieFragment fragment = new SubCategorieFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putParcelable(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getParcelable(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        databinding = DataBindingUtil.bind(inflater.inflate(R.layout.fragment_sub_categorie, container, false));

        dataBindingComponent = new FragmentDataBindingComponent(this);

        binding = new AutoClearedValue<>(this, databinding);

        binding.get().toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        binding.get().setRepo(mParam2);


        return databinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SubViewModal.Factory factory = new SubViewModal.Factory(getActivity().getApplication());
        viewModal = ViewModelProviders.of(this,factory).get(SubViewModal.class);
        binding.get().setVm(viewModal);
        categoryListAdapter = new SubCategoryListAdapter(dataBindingComponent, false, new SubCategoryListAdapter.RepoClickCallback() {
            @Override
            public void onClick(SubCategory repo) {

                NavigationController navigationController = new NavigationController((MainActivity) getActivity());
                navigationController.navigateToBusinessListing(repo.getSubcatID(),repo);

            }
        });
        binding.get().categorylist.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.get().categorylist.setAdapter(categoryListAdapter);

        this.adapter = new AutoClearedValue<>(this, categoryListAdapter);
        initCategory();
    }


    private void initCategory(){
        viewModal.getSubCategory(mParam1).subscribe(new Consumer<SubCategoryResponse>() {
            @Override
            public void accept(SubCategoryResponse repos) throws Exception {
                if (repos == null) {
                    adapter.get().replace(null);
                } else {
                    adapter.get().replace(repos.getResult());
                }
                viewModal.enable.set(false);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

                throwable.printStackTrace();
            }
        });
    }

}
