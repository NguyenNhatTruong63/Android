package com.example.doan_mobile;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.doan_mobile.ScowView.Drink;
import com.example.doan_mobile.ScowView.DrinkAdapter;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    private ViewPager viewPager;
    private PhotoAdapter photoAdapter;

    private RecyclerView recyclerView;
    private Button Coffee, Drinks, Cake;

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
        viewPager = findViewById(R.id.viewpager);

        photoAdapter = new PhotoAdapter(this, getListPhoto());
        viewPager.setAdapter(photoAdapter);


        Coffee = findViewById(R.id.button3);
        Drinks = findViewById(R.id.button2);
        Cake = findViewById(R.id.button);

        recyclerView = findViewById(R.id.rcv_drink);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);//2 cọt
        recyclerView.setLayoutManager(gridLayoutManager);

        DrinkAdapter drinkAdapter = new DrinkAdapter(getList());
        recyclerView.setAdapter(drinkAdapter);
    }

    private List<Drink> getList() {
        List<Drink> list = new ArrayList<>();
        list.add(new Drink(R.drawable.c1, "Cafe",Drink.TYPE_COFFEE, 20.000, R.drawable.star));
        

        return list;
    }

    private List<Photo> getListPhoto() {
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.logo1));
        list.add(new Photo(R.drawable.logo2));
        list.add(new Photo(R.drawable.logo3));

        return list;

    }

}