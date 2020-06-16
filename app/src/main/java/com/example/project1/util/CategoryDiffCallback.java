package com.example.project1.util;

import com.example.project1.model.Category;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;

public class CategoryDiffCallback extends DiffUtil.Callback {

    private List<Category> mOldList;
    private List<Category> mNewList;

    public CategoryDiffCallback(List<Category> mOldList, List<Category> mNewList) {
        this.mOldList = mOldList;
        this.mNewList = mNewList;
    }

    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        Category oldCategory = mOldList.get(oldItemPosition);
        Category newCategory = mNewList.get(newItemPosition);
        return oldCategory.getId() == newCategory.getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Category oldCategory = mOldList.get(oldItemPosition);
        Category newCategory = mNewList.get(newItemPosition);
        return oldCategory.getName().equals(newCategory.getName());
    }

}
