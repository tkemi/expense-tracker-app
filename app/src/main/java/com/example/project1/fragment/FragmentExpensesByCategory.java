package com.example.project1.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.project1.R;
import com.example.project1.adapter.CategorySumAdapter;
import com.example.project1.model.Category;
import com.example.project1.viewCustom.PercentageTextView;
import com.example.project1.viewModel.MainViewModel;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentExpensesByCategory extends Fragment {

    private MainViewModel mViewModel;
    private List<Category> mCategories;
    private CategorySumAdapter mCategorySumAdapter;

    @BindView(R.id.pv_fragment_expenses_by_category_chart)
    PercentageTextView pieChart;
    @BindView(R.id.tv_fragment_expenses_by_category_sum)
    TextView tvCategorySum;
    @BindView(R.id.rv_expenses_by_category_fragment)
    RecyclerView recyclerView;
//    @BindView(R.id.btn_list_item_details)
//    MaterialButton btnDetails;
//    @BindView(R.id.btn_list_item_remove)
//    MaterialButton btnRemove;

    public static FragmentExpensesByCategory newInstance() {

//        Bundle args = new Bundle();
//
//        FragmentExpensesByCategory fragment = new FragmentExpensesByCategory();
//        fragment.setArguments(args);
        return new FragmentExpensesByCategory();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expenses_by_category,container,false);
        ButterKnife.bind(this,view);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);

        mCategorySumAdapter = new CategorySumAdapter();
        recyclerView.setAdapter(mCategorySumAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mViewModel.getmCategoryLiveData().observe(getViewLifecycleOwner(), categories -> {
            mCategories = new ArrayList<>();
            for(Category c:categories){
                if(!c.getName().equals("All")){
                    mCategories.add(c);
//                    Log.d("adding ","category: "+c.getName());
//                    Log.d("category ","sum: "+c.getSum());
                }
            }
            mCategorySumAdapter.setData(mCategories);

            int sum=0;

            for(Category c:categories){
                sum += c.getSum();
            }

            tvCategorySum.setText("In total: " + sum);

            pieChart.setCategoryList(mCategories);
            pieChart.invalidate();
        });
    }
}
