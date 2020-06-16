package com.example.project1.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project1.R;
import com.example.project1.adapter.ExpenseAdapter;
import com.example.project1.model.Category;
import com.example.project1.model.Expense;
import com.example.project1.util.Util;
import com.example.project1.viewModel.MainViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;


import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentAddExpense extends Fragment {

    private MainViewModel mViewModel;
    private List<Expense> mExpenses;
    private ExpenseAdapter mAdapter;

    @BindView(R.id.tv_add_expense_fragment_title)
    TextView tvTitle;
    @BindView(R.id.et_add_expense_fragment_name)
    TextInputEditText etExpenseName;
    @BindView(R.id.et_add_expense_fragment_price)
    TextInputEditText etExpensePrice;
    @BindView(R.id.spinner_add_expense_fragment)
    Spinner spinner;
    @BindView(R.id.btn_add_expense_fragment_add)
    MaterialButton btnAddExpense;

    public static FragmentAddExpense newInstance() {

//        Bundle args = new Bundle();
//
//        FragmentAddExpense fragment = new FragmentAddExpense();
//        fragment.setArguments(args);
        return new FragmentAddExpense();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_expense, container, false);
        ButterKnife.bind(this, view);
        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category category = (Category) spinner.getSelectedItem();
                String name = etExpenseName.getText().toString().trim();
                String priceString = etExpensePrice.getText().toString().trim();
                if (!category.getName().equals("All")) {
                    if (!name.equals("")) {
                        if (!priceString.equals("")) {
                            int price = Integer.parseInt(priceString);
                            category.setSum(category.getSum() + price);
                            Expense expense = new Expense(Util.generatedId(), name, price, category);
                            mViewModel.addExpense(0, expense);
                            Toast.makeText(FragmentAddExpense.this.getContext(),
                                    etExpenseName.getText().toString().trim()
                                            + " expense added - " + Integer.parseInt(etExpensePrice.getText().toString().trim())
                                            + " price", Toast.LENGTH_SHORT).show();

                            etExpenseName.setText("");
                            etExpensePrice.setText("");
                        } else {
                            Toast.makeText(FragmentAddExpense.this.getContext(), "Enter price!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(FragmentAddExpense.this.getContext(), "Name the expense!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(FragmentAddExpense.this.getContext(), "Choose category!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mViewModel.getmExpensesLiveData().observe(getViewLifecycleOwner(), new Observer<List<Expense>>() {
            @Override
            public void onChanged(List<Expense> expenses) {
                Log.d("List changed:", "List has been changed");
            }
        });

        mViewModel.getmCategoryLiveData().observe(getViewLifecycleOwner(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                ArrayAdapter<Category> adapter = new ArrayAdapter<>(FragmentAddExpense.this.getContext(),
                        R.layout.support_simple_spinner_dropdown_item, categories);

                adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
            }
        });
    }
}
