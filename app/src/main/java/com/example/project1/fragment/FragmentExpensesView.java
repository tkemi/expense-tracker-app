package com.example.project1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.project1.R;
import com.example.project1.activity.ItemActivity;
import com.example.project1.adapter.ExpenseAdapter;
import com.example.project1.model.Category;
import com.example.project1.model.Expense;
import com.example.project1.viewModel.MainViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;


import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class FragmentExpensesView extends Fragment {

    private MainViewModel mViewModel;
    private ExpenseAdapter mExpenseAdapter;
    private List<Expense> mExpenses;
    private static final int REQUEST_CODE = 100;
    public static final int ADAPTER_POSITION=1;

    @BindView(R.id.et_expense_view_fragment_filter)
    TextInputEditText edFilter;
    @BindView(R.id.spinner_expense_view_fragment)
    Spinner spinner;
    @BindView(R.id.btn_expense_view_fragment_apply_category)
    MaterialButton btnApplyCategory;
    @BindView(R.id.rv_expenses_view_fragment)
    RecyclerView recyclerView;


    public static FragmentExpensesView newInstance() {

//        Bundle args = new Bundle();
//
//        FragmentExpensesView fragment = new FragmentExpensesView();
//        fragment.setArguments(args);
        return new FragmentExpensesView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expenses_view,container,false);
        ButterKnife.bind(this,view);
        btnApplyCategory.setOnClickListener(v -> {
//            Toast.makeText(FragmentExpensesView.this.getContext(),"Implement search with button",Toast.LENGTH_SHORT).show();
            Category c= (Category)spinner.getSelectedItem();
            mViewModel.searchRecyclerViewCategory(c);
        });

        edFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()!=0){
                    btnApplyCategory.setEnabled(false);
                }else{
                    btnApplyCategory.setEnabled(true);
                }
                mViewModel.searchRecyclerViewByName(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);

        mExpenseAdapter = new ExpenseAdapter();

        mExpenseAdapter.setItemRemoveCallback(position -> {
            mViewModel.removeExpense(position);
            Toast.makeText(FragmentExpensesView.this.getContext(),"Expense removed!",Toast.LENGTH_SHORT).show();
        });

//        mExpenseAdapter.setItemDetailsCallback(new ExpenseAdapter.itemDetailsCallback() {
//            @Override
//            public void itemDetails(int position) {
//                ItemActivity.setiViewModel(mViewModel);
//                Intent intent = new Intent(FragmentExpensesView.this.getContext(), ItemActivity.class);
//                intent.putExtra("position", position);
//                intent.putExtra()
//
//                FragmentExpensesView.this.startActivity(intent);
//            }
//        });

        mExpenseAdapter.setItemDetailsCallback(new ExpenseAdapter.itemDetailsCallback() {
            @Override
            public void itemDetails(int position, String name, String category, String price, String date) {
                Intent intent = new Intent(FragmentExpensesView.this.getContext(), ItemActivity.class);
//
                intent.putExtra("position",position);
                intent.putExtra("name", name);
                intent.putExtra("category",category);
                intent.putExtra("price", price);
                intent.putExtra("date", date);
//
                startActivityForResult(intent,REQUEST_CODE);

            }
        });

        recyclerView.setAdapter(mExpenseAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mViewModel.getmExpensesLiveData().observe(getViewLifecycleOwner(), expenses -> {
            mExpenses = new ArrayList<>(expenses);
            mExpenseAdapter.setData(mExpenses);
        });

        mViewModel.getmCategoryLiveData().observe(getViewLifecycleOwner(), categories -> {
            ArrayAdapter<Category> adapter = new ArrayAdapter<>(FragmentExpensesView.this.getContext(),
                    R.layout.support_simple_spinner_dropdown_item,categories);
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode != REQUEST_CODE){
            return;
        }

        if(resultCode == RESULT_OK){

            int position = data.getExtras().getInt(String.valueOf(ADAPTER_POSITION));
            mViewModel.removeExpense(position);

        }else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getContext(),"CANCELED "+resultCode,Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(),"Something went wrond.",Toast.LENGTH_SHORT).show();
        }
    }
}
