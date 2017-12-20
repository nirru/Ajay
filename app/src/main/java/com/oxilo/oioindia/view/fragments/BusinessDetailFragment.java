package com.oxilo.oioindia.view.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oxilo.oioindia.R;
import com.oxilo.oioindia.data.DataManager;
import com.oxilo.oioindia.databinding.FragmentBusinessDetailBinding;
import com.oxilo.oioindia.modal.BusinessDetails;
import com.oxilo.oioindia.modal.Details;
import com.oxilo.oioindia.viewmodal.BusinesDetailViewModal;
import com.oxilo.oioindia.viewmodal.MainViewModal;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.List;

import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BusinessDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BusinessDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView reviews_rating_list;
    FragmentBusinessDetailBinding binding;


    public BusinessDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter ic_name.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BusinessDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BusinessDetailFragment newInstance(String param1, String param2) {
        BusinessDetailFragment fragment = new BusinessDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.bind(inflater.inflate(R.layout.fragment_business_detail, container, false));
        BusinesDetailViewModal.Factory factory = new BusinesDetailViewModal.Factory(getActivity().getApplication());
        BusinesDetailViewModal viewModal = ViewModelProviders.of(this,factory).get(BusinesDetailViewModal.class);
        binding.setVm(viewModal);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });


        viewModal.getBusinessDetail(mParam1).subscribe(new Consumer<Response<ResponseBody>>() {
            @Override
            public void accept(Response<ResponseBody> responseBodyResponse) throws Exception {
                viewModal.enable.set(false);
                try {
                    String sd = new String(responseBodyResponse.body().bytes());
                    JSONObject mapping = new JSONObject(sd);
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    List<BusinessDetails> businessDetailsList = mapper.readValue(mapping.getString("result1"), new TypeReference<List<BusinessDetails>>() {
                    });
//
                    Log.e("SIZE==", "" + businessDetailsList.size());
                    if(businessDetailsList.get(0).getImage()== null)
                        businessDetailsList.get(0).setImage("http://cumbrianrun.co.uk/wp-content/uploads/2014/02/default-placeholder-300x300.png");
                    binding.setRepo(businessDetailsList.get(0));
                    binding.desc.setText(Html.fromHtml(businessDetailsList.get(0).getDescription()));

                    String url = businessDetailsList.get(0).getImage();
                    if (!url.equals("") && url != null) {
                        try {
                            Picasso.with(getActivity()).load(url).placeholder(R.mipmap.ic_launcher_round).fit().centerCrop().into(binding.image);
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }

                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });

        return  binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        reviews_rating_list = (RecyclerView)view.findViewById(R.id.reviews_rating_list);
        setHasOptionsMenu(true);
        reviews_rating_list.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

    }
}
