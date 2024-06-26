package com.example.doan_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.doan_mobile.Database.DatabaseProductHelper;
import com.example.doan_mobile.Database.Product;
import com.example.doan_mobile.View.ProductAdapter;
import com.example.doan_mobile.cart.CartActivity;
import com.example.doan_mobile.cart.UserSessionManager;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> productList;
    private DatabaseProductHelper dbHelper;
    private GridLayoutManager gridLayoutManager;
    private ViewPager viewPager;
    private PhotoAdapter photoAdapter;
    private Button Coffee, Drinks, Cake;
    private ImageView home, ViewCart, account,history;
    //tiem kiem
    private List<Product> filteredProductList;
    private SearchView searchView;
//dang nhap
    private UserSessionManager session;
    private TextView tvWelcome;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu);

        ViewCart = findViewById(R.id.ViewCart);
        ViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, CartActivity.class);
                startActivity(intent);
            }
        });

        history = findViewById(R.id.history);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, OrderHistoryActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recycler_view);
        gridLayoutManager = new GridLayoutManager(this, 2); // 2 cột
        recyclerView.setLayoutManager(gridLayoutManager);

        dbHelper = new DatabaseProductHelper(this);
        productList = dbHelper.getAllProducts();
        filteredProductList = new ArrayList<>(productList);

        // Thay đổi cách khởi tạo adapter để truyền context
        adapter = new ProductAdapter(this, filteredProductList);
        recyclerView.setAdapter(adapter);


        viewPager = findViewById(R.id.viewpager);
        photoAdapter = new PhotoAdapter(this, getListPhoto());
        viewPager.setAdapter(photoAdapter);

        Coffee = findViewById(R.id.button3);
        Drinks = findViewById(R.id.button2);
        Cake = findViewById(R.id.button);

        Coffee.setOnClickListener(this);
        Drinks.setOnClickListener(this);
        Cake.setOnClickListener(this);

        home = findViewById(R.id.imageView3);
        home.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, Home.class);
            startActivity(intent);
        });


        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });
        account = findViewById(R.id.account);
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Account.class);
                startActivity(intent);
            }
        });
    }

    private void filter(String text) {
        filteredProductList.clear();
        if (text.isEmpty()) {
            filteredProductList.addAll(productList);
        } else {
            for (Product product : productList) {
                if (product.getName().toLowerCase().contains(text.toLowerCase())) {
                    filteredProductList.add(product);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    private List<Photo> getListPhoto() {
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.logo1));
        list.add(new Photo(R.drawable.logo2));
        list.add(new Photo(R.drawable.logo3));
        return list;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button3) {
            ScrollToItem(0);
        } else if (v.getId() == R.id.button2) {
            ScrollToItem(10);
        } else if (v.getId() == R.id.button) {
            ScrollToItem(22);
        }
    }

    private void ScrollToItem(int index) {
        recyclerView.smoothScrollToPosition(index);
    }
}
