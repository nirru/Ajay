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

package com.oxilo.oioindia.view.adapter;

import com.oxilo.oioindia.R;
import com.oxilo.oioindia.databinding.BusRowItemBinding;
import com.oxilo.oioindia.databinding.CategoryItemBinding;
import com.oxilo.oioindia.databinding.SubCategoryItemBinding;
import com.oxilo.oioindia.modal.Business;
import com.oxilo.oioindia.modal.BusinessListing;
import com.oxilo.oioindia.view.common.DataBoundListAdapter;
import com.oxilo.oioindia.vo.Category;
import com.oxilo.oioindia.vo.SubCategory;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Objects;

/**
 * A RecyclerView adapter for {@link Category} class.
 */
public class BusinessListAdapter extends DataBoundListAdapter<BusinessListing, BusRowItemBinding> {
    private final DataBindingComponent dataBindingComponent;
    private final RepoClickCallback repoClickCallback;

    public BusinessListAdapter(DataBindingComponent dataBindingComponent,
            RepoClickCallback repoClickCallback) {
        this.dataBindingComponent = dataBindingComponent;
        this.repoClickCallback = repoClickCallback;
    }

    @Override
    protected BusRowItemBinding createBinding(ViewGroup parent) {
        BusRowItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.bus_row_item,
                        parent, false, dataBindingComponent);
//        binding.setShowFullName(showFullName);
        binding.getRoot().setOnClickListener(v -> {
            BusinessListing repo = binding.getRepo();
            if (repo != null && repoClickCallback != null) {
                repoClickCallback.onClick(repo);
            }
        });
        return binding;
    }

    @Override
    protected void bind(BusRowItemBinding binding, BusinessListing item) {
        binding.setRepo(item);
    }

    @Override
    protected boolean areItemsTheSame(BusinessListing oldItem, BusinessListing newItem) {
        return Objects.equals(oldItem.getProductID(), newItem.getProductID()) &&
                Objects.equals(oldItem.getProductID(), newItem.getProductID());
    }

    @Override
    protected boolean areContentsTheSame(BusinessListing oldItem, BusinessListing newItem) {
        return Objects.equals(oldItem.getName(), newItem.getName()) &&
                oldItem.getProductID() == newItem.getProductID();
    }

    public interface RepoClickCallback {
        void onClick(BusinessListing respo);
    }
}
