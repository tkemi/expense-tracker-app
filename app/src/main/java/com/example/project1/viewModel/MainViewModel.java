package com.example.project1.viewModel;

import android.app.Application;
import android.util.Log;

import com.example.project1.model.Category;
import com.example.project1.model.Expense;
import com.example.project1.util.Util;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class MainViewModel extends AndroidViewModel {
    private List<Expense> expenseList = new ArrayList<>();
    private List<Category> categoryList = new ArrayList<>();
    private MutableLiveData<List<Expense>> mExpensesLiveData;
    private MutableLiveData<List<Category>> mCategoryLiveData;
    private List<Expense> expenseFilteredList;
    private List<Expense> categoryFilteredList;

    public MainViewModel(@NonNull Application application) {
        super(application);
        mExpensesLiveData = new MutableLiveData<>();
        mCategoryLiveData = new MutableLiveData<>();

        categoryList.add(new Category(Util.generatedId(), "All"));

        mCategoryLiveData.setValue(categoryList);
    }

    public void addCategory(int index, Category c) {
        categoryList.add(index, c);
        mCategoryLiveData.setValue(categoryList);
    }

    public void addExpense(int index, Expense expense) {
        expenseList.add(index, expense);
        mExpensesLiveData.setValue(expenseList);
    }

    public void removeExpense(int index) {
        Expense expense = expenseList.get(index);
//        Log.d("Expense category:","sum "+expense.getCategory().getSum());
        expense.getCategory().setSum(expense.getCategory().getSum()-expense.getPrice());
//        Log.d("Expense category:","sum "+expense.getCategory().getSum());
        expenseList.remove(index);
        mExpensesLiveData.setValue(expenseList);
        mCategoryLiveData.setValue(categoryList);
    }

    public Expense getExpense(int index){
        return expenseList.get(index);

    }

    public MutableLiveData<List<Expense>> getmExpensesLiveData() {
        return mExpensesLiveData;
    }

    public void setmExpensesLiveData(List<Expense> expenses) {
        this.mExpensesLiveData.setValue(new ArrayList<>(expenses));
    }

    public MutableLiveData<List<Category>> getmCategoryLiveData() {
        return mCategoryLiveData;
    }

    public void setmCategoryLiveData(List<Category> categories) {
        this.mCategoryLiveData.setValue(new ArrayList<>(categories));
    }


    public void searchRecyclerViewByName(CharSequence searchText) {
        expenseFilteredList = new ArrayList<>();
        String filterPattern = searchText.toString().toLowerCase().trim();
        Log.d("Filter pattern: ", filterPattern);

        if (filterPattern.length() == 0) {
            setmExpensesLiveData(expenseList);
        } else {

            for (Expense e : expenseList) {

                if (e.getName().toLowerCase().startsWith(filterPattern) && !expenseFilteredList.contains(e)) {
                    Log.d("Contact name: ", e.getName());
                    expenseFilteredList.add(e);

                }
            }
            setmExpensesLiveData(expenseFilteredList);
        }
    }

    public void searchRecyclerViewCategory(Category c) {
        categoryFilteredList = new ArrayList<>();
        if (c.getName().equals("All")) {
            setmExpensesLiveData(expenseList);
        } else {
            for (Expense e : expenseList) {
                if (e.getCategory().getName().equals(c.getName())) {
                    categoryFilteredList.add(e);
                }
            }
            setmExpensesLiveData(categoryFilteredList);
        }
    }
}
