package com.example.project1.util;

import com.example.project1.model.Expense;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;

public class ExpenseDiffCallback extends DiffUtil.Callback {

    private List<Expense> mOldList;
    private List<Expense> mNewList;

    public ExpenseDiffCallback(List<Expense> mOldList, List<Expense> mNewList) {
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
        Expense oldExpense = mOldList.get(oldItemPosition);
        Expense newExpense = mNewList.get(newItemPosition);
        return oldExpense.getId() == newExpense.getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Expense oldExpense = mOldList.get(oldItemPosition);
        Expense newExpense = mNewList.get(newItemPosition);
        return  oldExpense.getName().equals(newExpense.getName());
    }
}
