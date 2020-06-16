package com.example.project1.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project1.R;
import com.example.project1.adapter.CategoryAdapter;
import com.example.project1.adapter.CategorySumAdapter;
import com.example.project1.model.Category;
import com.example.project1.util.Util;
import com.example.project1.viewModel.MainViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

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

public class FragmentAddCategory extends Fragment {

    private MainViewModel mViewModel;
    private List<Category> mCategories;
    private CategoryAdapter mCategoryAdapter;
    private static int index = 0;

    @BindView(R.id.tv_add_category_fragment_title)
    TextView tvTitle;
    @BindView(R.id.et_add_category_fragment_name)
    TextInputEditText etCategoryName;
    @BindView(R.id.btn_add_category_fragment_add)
    Button btnAddCategory;
    @BindView(R.id.rv_expenses_add_category_fragment)
    RecyclerView recyclerView;

    public static FragmentAddCategory newInstance() {

//        Bundle args = new Bundle();
//
//        FragmentAddCategory fragment = new FragmentAddCategory();
//        fragment.setArguments(args);
        return new FragmentAddCategory();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_category,container,false);
        ButterKnife.bind(this,view);
        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etCategoryName.getText().toString().trim();
                if(!name.equals("")) {
                    Category category = new Category(Util.generatedId(), name);
//                    Log.d("Category_id: ", Integer.toString(category.getId()));
//                    Log.d("Category_name: ", category.getName());
                    mViewModel.addCategory(0, category);
                    etCategoryName.setText("");
//                mViewModel.addCategory(index++,category);
                }else{
                    Toast.makeText(FragmentAddCategory.this.getContext(),"Enter category name!",Toast.LENGTH_SHORT).show();
                }
            };
        });

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);


        mCategoryAdapter = new CategoryAdapter();
        recyclerView.setAdapter(mCategoryAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        mViewModel= ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mViewModel.getmCategoryLiveData().observe(getViewLifecycleOwner(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                mCategories = new ArrayList<>();
                for(Category c:categories){
                    if(!c.getName().equals("All")){
                        mCategories.add(c);
                    }
                }
                mCategoryAdapter.setData(mCategories);
            }
        });
    }
}
