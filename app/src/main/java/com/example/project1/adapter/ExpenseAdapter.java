package com.example.project1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project1.R;
import com.example.project1.fragment.FragmentAddExpense;
import com.example.project1.model.Expense;
import com.example.project1.util.ExpenseDiffCallback;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseHolder> {

    private List<Expense> mExpenseDataSet;
    private itemDetailsCallback itemDetailsCallback;
    private itemRemoveCallback itemRemoveCallback;

    public ExpenseAdapter() {
        this.mExpenseDataSet = new ArrayList<>();
    }

    public ExpenseAdapter.itemDetailsCallback getItemDetailsCallback() {
        return itemDetailsCallback;
    }

    public void setItemDetailsCallback(ExpenseAdapter.itemDetailsCallback itemDetailsCallback) {
        this.itemDetailsCallback = itemDetailsCallback;
    }

    public ExpenseAdapter.itemRemoveCallback getItemRemoveCallback() {
        return itemRemoveCallback;
    }

    public void setItemRemoveCallback(ExpenseAdapter.itemRemoveCallback itemRemoveCallback) {
        this.itemRemoveCallback = itemRemoveCallback;
    }

    @NonNull
    @Override
    public ExpenseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.expense_view_list_item, parent, false);
        return new ExpenseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseHolder holder, int position) {
        Expense expense = mExpenseDataSet.get(position);
        holder.itemName.setText(expense.getName());
        holder.itemDate.setText(expense.getDate());
        holder.itemCategory.setText(expense.getCategory().getName());
        holder.itemPrice.setText(Integer.toString(expense.getPrice()));

        holder.btnDetails.setOnClickListener((View v) -> {
            itemDetailsCallback.itemDetails(holder.getAdapterPosition(), expense.getName(), expense.getCategory().toString(),
                    Integer.toString(expense.getPrice()), expense.getDate().toString());
        });

        holder.btnRemove.setOnClickListener(v -> itemRemoveCallback.itemRemove(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return mExpenseDataSet.size();
    }

    public void setData(List<Expense> expenses) {
        ExpenseDiffCallback expenseDiffCallback = new ExpenseDiffCallback(mExpenseDataSet, expenses);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(expenseDiffCallback);
        mExpenseDataSet.clear();
        mExpenseDataSet.addAll(expenses);
        result.dispatchUpdatesTo(this);
    }

    public class ExpenseHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_list_item_name)
        TextView itemName;
        @BindView(R.id.tv_list_item_date)
        TextView itemDate;
        @BindView(R.id.tv_list_item_category)
        TextView itemCategory;
        @BindView(R.id.tv_list_item_price)
        TextView itemPrice;
        @BindView(R.id.btn_list_item_remove)
        public MaterialButton btnRemove;
        @BindView(R.id.btn_list_item_details)
        public MaterialButton btnDetails;

        public ExpenseHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
//            ButterKnife.bind(this,itemName);
//            ButterKnife.bind(this,itemDate);
//            ButterKnife.bind(this,itemCategory);
//            ButterKnife.bind(this,itemPrice);
//            ButterKnife.bind(this,btnRemove);
//            ButterKnife.bind(this,btnDetails);
//            itemName = itemView.findViewById(R.id.tv_list_item_name);
//            itemDate = itemView.findViewById(R.id.tv_list_item_date);
//            itemCategory = itemView.findViewById(R.id.tv_list_item_category);
//            itemPrice = itemView.findViewById(R.id.tv_list_item_price);
//            btnRemove = itemView.findViewById(R.id.btn_list_item_remove);
//            btnDetails = itemView.findViewById(R.id.btn_list_item_details);

        }
    }
    public interface itemRemoveCallback {
        void itemRemove(int id);
    }

    public interface itemDetailsCallback {
        void itemDetails(int position,String name,String category,String price,String date);
    }
}

