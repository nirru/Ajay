package com.oxilo.oioindia.binding;

/**
 * Created by nikk on 7/11/17.
 */

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.ObservableField;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.oxilo.oioindia.modal.City;

import java.lang.ref.WeakReference;

/**
 * Created by cli on 14/11/2016.
 * All Rights Reserved.
 */

public class BindingCity {

    public final ObservableField<City> obvSelectedCity_ = new ObservableField<>(); //for simplicity, we use a public variable here

    private static WeakReference<IDataChangeListener> iDataChangeListenerWeakRef_ = new WeakReference<>(null);

    public BindingCity(IDataChangeListener iDataChangeListener, City selectedCity) {
        iDataChangeListenerWeakRef_ = new WeakReference<>(iDataChangeListener);
        obvSelectedCity_.set(selectedCity);
    }

    private static class SpinPlanetOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        private City initialSelectedPlanet_;
        private InverseBindingListener inverseBindingListener_;

        public SpinPlanetOnItemSelectedListener(City initialSelectedPlanet, InverseBindingListener inverseBindingListener) {
            initialSelectedPlanet_ = initialSelectedPlanet;
            inverseBindingListener_ = inverseBindingListener;
        }

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            if (initialSelectedPlanet_ != null) {
                //Adapter is not ready yet but there is already a bound data,
                //hence we need to set a flag so we can implement a special handling inside the OnItemSelectedListener
                //for the initial selected item
                Integer positionInAdapter = getPlanetPositionInAdapter((ArrayAdapter<City>) adapterView.getAdapter(), initialSelectedPlanet_);
                if (positionInAdapter != null) {
                    adapterView.setSelection(positionInAdapter); //set spinner selection as there is a match
                }
                initialSelectedPlanet_ = null; //set to null as the initialization is done
            } else {
                if (inverseBindingListener_ != null) {
                    inverseBindingListener_.onChange();
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {}
    }

    @BindingAdapter(value = {"bind:selectedPlanet", "bind:selectedPlanetAttrChanged"}, requireAll = false)
    public static void bindPlanetSelected(final AppCompatSpinner spinner, City planetSetByViewModel,
                                          final InverseBindingListener inverseBindingListener) {

        City initialSelectedPlanet = null;
        if (spinner.getAdapter() == null && planetSetByViewModel != null) {
            //Adapter is not ready yet but there is already a bound data,
            //hence we need to set a flag in order to implement a special handling inside the OnItemSelectedListener
            //for the initial selected item, otherwise the first item will be selected by the framework
            initialSelectedPlanet = planetSetByViewModel;
        }

        spinner.setOnItemSelectedListener(new SpinPlanetOnItemSelectedListener(initialSelectedPlanet, inverseBindingListener));

        //only proceed further if the newly selected planet is not equal to the already selected item in the spinner
        if (planetSetByViewModel != null && !planetSetByViewModel.equals(spinner.getSelectedItem())) {
            //find the item in the adapter
            Integer positionInAdapter = getPlanetPositionInAdapter((ArrayAdapter<City>) spinner.getAdapter(), planetSetByViewModel);
            if (positionInAdapter != null) {
                spinner.setSelection(positionInAdapter); //set spinner selection as there is a match
            }
        }
    }

    @InverseBindingAdapter(attribute = "bind:selectedPlanet", event = "bind:selectedPlanetAttrChanged")
    public static City captureSelectedPlanet(AppCompatSpinner spinner) {
        return (City) spinner.getSelectedItem();
    }

    @BindingConversion
    public static String convertPlanetToString(City planet) {
        return planet != null? planet.getCityname() : null;
    }

    public void onEditTextChanged(CharSequence s, int start, int before, int count) {
        if (iDataChangeListenerWeakRef_.get() != null) {
            iDataChangeListenerWeakRef_.get().onEditTextChanged(s.toString());
        }
    }

    private static Integer getPlanetPositionInAdapter(ArrayAdapter<City> adapter, City planet) {
        if (adapter != null && planet != null) {
            for (int i = 0; i < adapter.getCount(); i++) {
                City adapterPlanet = adapter.getItem(i);
                if (adapterPlanet != null && adapterPlanet.getCityname() != null && adapterPlanet.getCityname().equals(planet.getCityname())) {
                    return i;
                }
            }
        }
        return null;
    }
}
