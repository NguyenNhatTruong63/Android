package com.example.doan_mobile;

import android.content.Intent;
import android.database.Cursor;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.doan_mobile.Database.DatabaseProductHelper;
import com.example.doan_mobile.Database.Product;
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
    private ImageView home;

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

        // Thêm sản phẩm mẫu vào cơ sở dữ liệu
        addSampleProducts();

        // Lấy tất cả sản phẩm từ cơ sở dữ liệu
        productList = dbHelper.getAllProducts();

        // Thiết lập RecyclerView
        adapter = new ProductAdapter(productList);
        recyclerView.setAdapter(adapter);


//---------------------------------------------------------------------

        viewPager = findViewById(R.id.viewpager);
        home = findViewById(R.id.imageView3);

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
    }

    private void addSampleProducts() {
        dbHelper.addProduct(new Product("Coffee Latte", R.drawable.c1, "Coffee", 25.999));
        dbHelper.addProduct(new Product("Coffee Robusta", R.drawable.c2, "Coffee", 20.999));
        dbHelper.addProduct(new Product("Coffee Mocha", R.drawable.c3, "Coffee", 30.999));
        dbHelper.addProduct(new Product("Coffee Chery", R.drawable.c4, "Coffee", 25.999));
        dbHelper.addProduct(new Product("Coffee Culi", R.drawable.c5, "Coffee", 23.999));
        dbHelper.addProduct(new Product("Coffee Espresso", R.drawable.c6, "Coffee", 29.999));
        dbHelper.addProduct(new Product("Coffee Capuchino", R.drawable.c7, "Coffee", 39.999));
        dbHelper.addProduct(new Product("Coffee Macchiato", R.drawable.c8, "Coffee", 26.999));
        dbHelper.addProduct(new Product("Coffee Cubano", R.drawable.c9, "Coffee", 31.999));
        dbHelper.addProduct(new Product("Coffee Antoccino", R.drawable.c10, "Coffee", 35.999));

        //drinks
        dbHelper.addProduct(new Product("Pepsi", R.drawable.d1, "Drink", 12.999));
        dbHelper.addProduct(new Product("Revive", R.drawable.d2, "Drink", 9.999));
        dbHelper.addProduct(new Product("7 Up", R.drawable.d3, "Drink", 5.999));
        dbHelper.addProduct(new Product("Mirinda", R.drawable.d4, "Drink", 15.999));
        dbHelper.addProduct(new Product("Trà Xanh Không Độ", R.drawable.d5, "Drink", 9.999));
        dbHelper.addProduct(new Product("RedBull", R.drawable.d6, "Drink", 19.999));
        dbHelper.addProduct(new Product("Sprite", R.drawable.d7, "Drink", 9.999));
        dbHelper.addProduct(new Product("Trà ÔLong", R.drawable.d8, "Drink", 4.999));
        dbHelper.addProduct(new Product("Number 1", R.drawable.d9, "Drink", 19.999));
        dbHelper.addProduct(new Product("Trà Xanh C2", R.drawable.d10, "Drink", 5.999));
        dbHelper.addProduct(new Product("Fanta", R.drawable.d11, "Drink", 8.999));
        dbHelper.addProduct(new Product("Coca-CoLa", R.drawable.d12, "Drink", 9.999));

        //cake
        dbHelper.addProduct(new Product("Bánh Su Kem", R.drawable.ca1, "Cake", 12.999));
        dbHelper.addProduct(new Product("Cupcake", R.drawable.ca2, "Cake", 12.999));
        dbHelper.addProduct(new Product("Bánh rán Dorayaki", R.drawable.ca3, "Cake", 12.999));
        dbHelper.addProduct(new Product("Muffin", R.drawable.ca4, "Cake", 12.999));
        dbHelper.addProduct(new Product(" Pancake", R.drawable.ca5, "Cake", 12.999));
        dbHelper.addProduct(new Product("Tiramisu", R.drawable.ca6, "Cake", 12.999));
        dbHelper.addProduct(new Product("Cheesecake", R.drawable.ca7, "Cake", 12.999));
        dbHelper.addProduct(new Product("Salted egg sponge", R.drawable.ca8, "Cake", 12.999));
        dbHelper.addProduct(new Product("Bánh ngọt Donut", R.drawable.ca9, "Cake", 12.999));
        dbHelper.addProduct(new Product("Savoure", R.drawable.ca10, "Cake", 12.999));
        dbHelper.addProduct(new Product("Bánh Táo Mĩ", R.drawable.ca11, "Cake", 12.999));
        dbHelper.addProduct(new Product("Black Forest Đức", R.drawable.ca12, "Cake", 12.999));






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
