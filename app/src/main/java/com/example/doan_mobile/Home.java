package com.example.doan_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.doan_mobile.Database.DatabaseProductHelper;
import com.example.doan_mobile.Database.Product;
import com.example.doan_mobile.ListProduct.CoffeeLattee;
import com.example.doan_mobile.View.ProductAdapter;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> productList;
    private DatabaseProductHelper dbHelper;
    GridLayoutManager gridLayoutManager;

    private ViewPager viewPager;
    private PhotoAdapter photoAdapter;

    private Button Coffee, Drinks, Cake;
    private ImageView home, person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_trangchu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recycler_view);
        gridLayoutManager = new GridLayoutManager(this, 2); // 2 cột
        recyclerView.setLayoutManager(gridLayoutManager);

        productList = new ArrayList<>();
        dbHelper = new DatabaseProductHelper(this);


        // Lấy tất cả sản phẩm từ cơ sở dữ liệu
        productList = dbHelper.getAllProducts();

        // Thiết lập RecyclerView
        adapter = new ProductAdapter(productList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(product -> {
            if (product.getName().equals("Coffee Latte")) {
                Intent intent = new Intent(Home.this, CoffeeLattee.class);
                startActivity(intent);
            }
        });

//---------------------------------------------------------------------

        viewPager = findViewById(R.id.viewpager);
        home = findViewById(R.id.imageView3);
        person = findViewById(R.id.imageView7);

        photoAdapter = new PhotoAdapter(this, getListPhoto());
        viewPager.setAdapter(photoAdapter);

        Coffee = findViewById(R.id.button3);
        Drinks = findViewById(R.id.button2);
        Cake = findViewById(R.id.button);

        Coffee.setOnClickListener(this);
        Drinks.setOnClickListener(this);
        Cake.setOnClickListener(this);

        home.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, chi_la_test_function.class);
            startActivity(intent);
        });
        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Account.class);
                startActivity(intent);
            }
        });
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
        // Implement scroll logic here
    }
}
