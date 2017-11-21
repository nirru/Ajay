package com.oxilo.oioindia.utils;

/**
 * Created by nikk on 18/10/17.
 */
import android.databinding.Observable.OnPropertyChangedCallback;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Cancellable;


public class RxUtils {
    /**
     * Converts an ObservableField to an Observable. Note that setting null value inside
     * ObservableField (except for initial value) throws a NullPointerException.
     * @return Observable that contains the latest value in the ObservableField
     */
    @NonNull
    public static <T> Observable<T> toObservable(@NonNull final ObservableField<T> field) {

        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(final ObservableEmitter<T> e) throws Exception {
                T initialValue = field.get();
                if (initialValue != null) {
                    e.onNext(initialValue);
                }
                final OnPropertyChangedCallback callback = new OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(android.databinding.Observable observable, int i) {
                        e.onNext(field.get());
                    }
                };
                field.addOnPropertyChangedCallback(callback);
                e.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Exception {
                        field.removeOnPropertyChangedCallback(callback);
                    }
                });
            }
        });
    }

    /**
     * A convenient wrapper for {@code ReadOnlyField#create(Observable)}
     * @return DataBinding field created from the specified Observable
     */
    @NonNull
    public static <T> ReadOnlyField<T> toField(@NonNull final Observable<T> observable) {
        return ReadOnlyField.create(observable);
    }
}
