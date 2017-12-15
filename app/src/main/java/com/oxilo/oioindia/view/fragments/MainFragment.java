



package com.oxilo.oioindia.view.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.oxilo.oioindia.R;
import com.oxilo.oioindia.binding.BindingCity;
import com.oxilo.oioindia.binding.IDataChangeListener;
import com.oxilo.oioindia.data.DataManager;
import com.oxilo.oioindia.databinding.FragmentMainBinding;
import com.oxilo.oioindia.modal.City;
import com.oxilo.oioindia.modal.DirectoryData;
import com.oxilo.oioindia.view.MainSectionsAdapter;
import com.oxilo.oioindia.view.activity.MainActivity;
import com.oxilo.oioindia.view.adapter.ImagePagerAdapter;
import com.oxilo.oioindia.view.adapter.SpinnerAdapter;
import com.oxilo.oioindia.viewmodal.LoginViewModal;
import com.oxilo.oioindia.viewmodal.MainViewModal;
import com.oxilo.oioindia.vo.Slider;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import io.reactivex.functions.Consumer;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    FragmentMainBinding binding;



    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter ic_name.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.bind(inflater.inflate(R.layout.fragment_main, container, false));
        MainViewModal.Factory factory = new MainViewModal.Factory(getActivity().getApplication(), DataManager.getInstance());
        MainViewModal viewModal = ViewModelProviders.of(this,factory).get(MainViewModal.class);
        binding.setVm(viewModal);


        viewModal.getCombineData().subscribe(new Consumer<DirectoryData>() {
            @Override
            public void accept(DirectoryData directoryData) throws Exception {
                final List<Fragment> fragments = new Vector<Fragment>();
                final Bundle page = new Bundle();
                page.putString("url", "d");
                fragments.add(AllFragment.newInstance(directoryData.getAllCategory(), ""));
                fragments.add(TopFragment.newInstance(directoryData.getTopCategory(), ""));
                fragments.add(FaviouriteFragment.newInstance("", ""));

                List<String> strings = new ArrayList<>();
                strings.add("All");
                strings.add("Top");
                strings.add("Faviourite");

                binding.setPagerAdapter(new MainSectionsAdapter(getActivity(), getChildFragmentManager(), fragments, strings));
                binding.setPager(binding.viewpager);
                viewModal.enable.set(false);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
                viewModal.enable.set(false);
            }
        });

        viewModal.getSlider().subscribe(slider -> binding.setImageAdapter(new ImagePagerAdapter(getActivity(),slider.getResult())), Throwable::printStackTrace);

        return binding.getRoot();
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

    private void setupspinner(){
//        stockArr = strings.toArray(stockArr);
//        adapter = new SpinnerAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, stockArr);
//        timeSpinner.setAdapter(adapter);
//        timeSpinner.setOnItemSelectedListener(l);
    }



    private City loadPlanet(Context context, int resourceId) {
        City planet = null;

        if (context != null) {
            TypedArray typedArrayPlanet = null;
            try {
                String name = null;
                typedArrayPlanet = context.getResources().obtainTypedArray(resourceId);
                for (int i = 0; i < typedArrayPlanet.length(); i++) {
                    if (i == 0) {
                        name = typedArrayPlanet.getString(i);
                    }
                }
                if (name != null) {
                    planet = new City(name);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (typedArrayPlanet != null) {
                    typedArrayPlanet.recycle();
                }
            }
        }

        return planet;
    }


}
