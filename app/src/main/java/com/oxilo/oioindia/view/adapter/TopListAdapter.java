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
import com.oxilo.oioindia.databinding.CategoryItemBinding;
import com.oxilo.oioindia.view.common.DataBoundListAdapter;
import com.oxilo.oioindia.vo.Category;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Objects;

/**
 * A RecyclerView adapter for {@link Category} class.
 */
public class TopListAdapter extends DataBoundListAdapter<Category, CategoryItemBinding> {
    private final DataBindingComponent dataBindingComponent;
    private final RepoClickCallback repoClickCallback;
    private final boolean showFullName;

    public TopListAdapter(DataBindingComponent dataBindingComponent, boolean showFullName,
            RepoClickCallback repoClickCallback) {
        this.dataBindingComponent = dataBindingComponent;
        this.repoClickCallback = repoClickCallback;
        this.showFullName = showFullName;
    }

    @Override
    protected CategoryItemBinding createBinding(ViewGroup parent) {
        CategoryItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.category_item,
                        parent, false, dataBindingComponent);
//        binding.setShowFullName(showFullName);
        binding.getRoot().setOnClickListener(v -> {
            Category repo = binding.getRepo();
            if (repo != null && repoClickCallback != null) {
                repoClickCallback.onClick(repo);
            }
        });
        return binding;
    }

    @Override
    protected void bind(CategoryItemBinding binding, Category item) {
        binding.setRepo(item);
    }

    @Override
    protected boolean areItemsTheSame(Category oldItem, Category newItem) {
        return Objects.equals(oldItem.getName(), newItem.getName()) &&
                Objects.equals(oldItem.getName(), newItem.getName());
    }

    @Override
    protected boolean areContentsTheSame(Category oldItem, Category newItem) {
        return Objects.equals(oldItem.getImage(), newItem.getImage()) &&
                oldItem.getServiceID() == newItem.getServiceID();
    }

    public interface RepoClickCallback {
        void onClick(Category repo);
    }
}
