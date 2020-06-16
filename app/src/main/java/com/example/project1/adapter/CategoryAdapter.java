package com.example.project1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.project1.R;
import com.example.project1.model.Category;
import com.example.project1.util.CategoryDiffCallback;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {

    private List<Category> mCategoryDataSet;

    public CategoryAdapter() {
        this.mCategoryDataSet = new ArrayList<>();
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.expense_view_add_category_list_item,parent,false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        Category category = mCategoryDataSet.get(position);
        holder.itemCategory.setText(category.getName());
    }

    @Override
    public int getItemCount() {
        return mCategoryDataSet.size();
    }

    public void setData(List<Category> categories){
        CategoryDiffCallback callback = new CategoryDiffCallback(mCategoryDataSet,categories);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        mCategoryDataSet.clear();
        mCategoryDataSet.addAll(categories);
        result.dispatchUpdatesTo(this);
    }

    public class CategoryHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_view_add_category_list_item_category)
        TextView itemCategory;
        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
//            itemCategory = itemView.findViewById(R.id.tv_view_add_category_list_item_category);
        }
    }
}
