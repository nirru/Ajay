package com.oxilo.oioindia.view.fragments;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
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
import com.oxilo.oioindia.utils.AutoClearedValue;
import com.oxilo.oioindia.view.ProductClickCallback;
import com.oxilo.oioindia.view.activity.MainActivity;
import com.oxilo.oioindia.view.adapter.CategoryListAdapter;
import com.oxilo.oioindia.view.common.NavigationController;
import com.oxilo.oioindia.viewmodal.AllViewModal;
import com.oxilo.oioindia.viewmodal.MainViewModal;
import com.oxilo.oioindia.vo.Category;
import com.oxilo.oioindia.vo.CategoryResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AllFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AllFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private ArrayList<Category> mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
     android.databinding.DataBindingComponent dataBindingComponent;

    AllViewModal viewModal;
    private CategoryListAdapter categoryListAdapter;
    private AutoClearedValue<CategoryListAdapter> adapter;
    private AutoClearedValue<FragmentAllBinding> binding;
    private FragmentAllBinding databinding;

    public AllFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter ic_name.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllFragment newInstance(ArrayList<Category> param1, String param2) {
        AllFragment fragment = new AllFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getParcelableArrayList(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        databinding = DataBindingUtil.bind(inflater.inflate(R.layout.fragment_all, container, false));

        dataBindingComponent = new FragmentDataBindingComponent(this);

        binding = new AutoClearedValue<>(this, databinding);

        AllViewModal.Factory factory = new AllViewModal.Factory(getActivity().getApplication());
        viewModal = ViewModelProviders.of(this,factory).get(AllViewModal.class);


       return databinding.getRoot();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        AllViewModal.Factory factory = new AllViewModal.Factory(getActivity().getApplication());
        viewModal = ViewModelProviders.of(this,factory).get(AllViewModal.class);

        categoryListAdapter = new CategoryListAdapter(dataBindingComponent, false, new CategoryListAdapter.RepoClickCallback() {
            @Override
            public void onClick(Category repo) {
                NavigationController navigationController = new NavigationController((MainActivity) getActivity());
                navigationController.navigateToSubCategory(repo.getServiceID(),repo);
            }
        });
        binding.get().categorylist.setLayoutManager(new GridLayoutManager(getContext(),3));
        binding.get().categorylist.setAdapter(categoryListAdapter);

        this.adapter = new AutoClearedValue<>(this, categoryListAdapter);
        initCategory(savedInstanceState);
    }

    private final ProductClickCallback mProductClickCallback = new ProductClickCallback() {
        @Override
        public void onClick(Category product) {

            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
//                ((MainActivity) getActivity()).show(product);
            }
        }
    };

    private void initCategory(@Nullable Bundle savedInstanceState){
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

        if (savedInstanceState !=null)
        mParam1 = savedInstanceState.getParcelableArrayList("dataGotFromServer");
//        adapter.get().replace(mParam1);

        if (mParam1 == null) {
            adapter.get().replace(null);
        } else {
            adapter.get().replace(mParam1);
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("dataGotFromServer", mParam1);
    }

}
