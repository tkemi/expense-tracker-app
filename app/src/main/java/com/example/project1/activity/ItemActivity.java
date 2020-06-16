package com.example.project1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project1.R;
import com.example.project1.fragment.FragmentExpensesView;
import com.example.project1.model.Expense;
import com.example.project1.viewModel.MainViewModel;
import com.squareup.picasso.Picasso;

import butterknife.*;

import androidx.appcompat.app.AppCompatActivity;

public class ItemActivity extends AppCompatActivity {

    private static final String URL = "https://picsum.photos/1080/1920/?random";


    @BindView(R.id.tv_expense_item_name)
    TextView itemName;
    @BindView(R.id.tv_expense_item_category)
    TextView itemCategory;
    @BindView(R.id.tv_expense_item_price)
    TextView itemPrice;
    @BindView(R.id.tv_expense_item_date)
    TextView itemDate;
    @BindView(R.id.iv_expense_item_image)
    ImageView itemImage;
    @BindView(R.id.btn_expense_item_remove)
    Button btnRemoveItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        Intent intent = getIntent();
        int position = intent.getIntExtra("position",0);
//
        String name = intent.getStringExtra("name").toUpperCase();
        String category = intent.getStringExtra("category");
        String price = intent.getStringExtra("price");
        String date = intent.getStringExtra("date");
//
        itemName.setText(name);
        itemCategory.setText(category);
        itemPrice.setText(price);
        itemDate.setText(date);
        Picasso.get().load(URL).into(itemImage);
//
        btnRemoveItem.setOnClickListener(v -> {

            finishActivityWithResult(position);
        });
    }

    public void finishActivityWithResult(int position){
        Intent returnIntent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt(String.valueOf(FragmentExpensesView.ADAPTER_POSITION), position);
        returnIntent.putExtras(bundle);
        setResult(RESULT_OK,returnIntent);

        finish();
    }
}
