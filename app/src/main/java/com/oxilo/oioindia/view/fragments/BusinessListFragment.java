package com.oxilo.oioindia.view.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oxilo.oioindia.R;
import com.oxilo.oioindia.binding.FragmentDataBindingComponent;
import com.oxilo.oioindia.databinding.FragmentBusinessListBinding;
import com.oxilo.oioindia.databinding.FragmentSubCategorieBinding;
import com.oxilo.oioindia.modal.Business;
import com.oxilo.oioindia.modal.BusinessListing;
import com.oxilo.oioindia.utils.AutoClearedValue;
import com.oxilo.oioindia.view.activity.MainActivity;
import com.oxilo.oioindia.view.adapter.BusinessListAdapter;
import com.oxilo.oioindia.view.adapter.SubCategoryListAdapter;
import com.oxilo.oioindia.view.common.NavigationController;
import com.oxilo.oioindia.viewmodal.BusinesListViewModal;
import com.oxilo.oioindia.viewmodal.SubViewModal;
import com.oxilo.oioindia.vo.SubCategory;
import com.oxilo.oioindia.vo.SubCategoryResponse;

import io.reactivex.functions.Consumer;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BusinessListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BusinessListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    android.databinding.DataBindingComponent dataBindingComponent;

    BusinesListViewModal viewModal;
    private BusinessListAdapter businessListAdapter;
    private AutoClearedValue<BusinessListAdapter> adapter;
    private AutoClearedValue<FragmentBusinessListBinding> binding;
    private FragmentBusinessListBinding databinding;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private SubCategory mParam2;


    public BusinessListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter ic_name.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BusinessListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BusinessListFragment newInstance(String param1, SubCategory param2) {
        BusinessListFragment fragment = new BusinessListFragment();
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
        databinding = DataBindingUtil.bind(inflater.inflate(R.layout.fragment_business_list, container, false));

        dataBindingComponent = new FragmentDataBindingComponent(this);

        binding = new AutoClearedValue<>(this, databinding);

        binding.get().toolbar.setNavigationOnClickListener(view -> getActivity().getSupportFragmentManager().popBackStack());

        binding.get().setRepo(mParam2);


        return databinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        BusinesListViewModal.Factory factory = new BusinesListViewModal.Factory(getActivity().getApplication());
        viewModal = ViewModelProviders.of(this,factory).get(BusinesListViewModal.class);
        binding.get().setVm(viewModal);
        businessListAdapter = new BusinessListAdapter(dataBindingComponent, new BusinessListAdapter.RepoClickCallback() {
            @Override
            public void onClick(BusinessListing repo) {
                NavigationController navigationController = new NavigationController((MainActivity) getActivity());
                navigationController.navigateToBusinessDetails(repo.getProductID());
            }
        });
        binding.get().categorylist.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.get().categorylist.setAdapter(businessListAdapter);

        this.adapter = new AutoClearedValue<>(this, businessListAdapter);
        initCategory();
    }


    private void initCategory(){
        viewModal.getBusinessListing(mParam1,"2").subscribe(new Consumer<Business>() {
            @Override
            public void accept(Business repos) throws Exception {
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
