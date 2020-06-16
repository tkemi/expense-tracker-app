package com.example.project1.adapter;

import android.util.Log;
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

public class CategorySumAdapter extends RecyclerView.Adapter<CategorySumAdapter.CategorySumHolder>{

    private List<Category> mCategoryDataSet;

    public CategorySumAdapter() {
        this.mCategoryDataSet = new ArrayList<>();
    }

    @NonNull
    @Override
    public CategorySumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.expense_view_by_category_list_item,parent,false);
        return new CategorySumHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategorySumHolder holder, int position) {
        Category category = mCategoryDataSet.get(position);
        holder.tvItemCategory.setText(category.getName());
        holder.tvItemPrice.setText(Integer.toString(category.getSum()));
    }

    @Override
    public int getItemCount() {
        return mCategoryDataSet.size();
    }

    public void setData(List<Category> categories){
        CategoryDiffCallback categoryDiffCallback = new CategoryDiffCallback(mCategoryDataSet,categories);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(categoryDiffCallback);
//        for(Category c:categories){
//            Log.d("Category: ",c.getName()+" Sum: "+c.getSum());
//        }
        mCategoryDataSet.clear();
        mCategoryDataSet.addAll(categories);
        result.dispatchUpdatesTo(this);
    }

    public class CategorySumHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_view_by_category_list_item_category)
        TextView tvItemCategory;
        @BindView(R.id.tv_view_by_category_list_item_price)
        TextView tvItemPrice;

        public CategorySumHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
