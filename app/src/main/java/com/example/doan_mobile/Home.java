package com.example.doan_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;

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

public class Home extends AppCompatActivity implements View.OnClickListener  {


    private ViewPager viewPager;
    private PhotoAdapter photoAdapter;

    private RecyclerView recyclerView;
    private Button Coffee, Drinks, Cake;
    private ImageView home, bill, cart, person;
    GridLayoutManager gridLayoutManager;

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
        home = findViewById(R.id.imageView3);
        person = findViewById(R.id.imageView7);

        photoAdapter = new PhotoAdapter(this, getListPhoto());
        viewPager.setAdapter(photoAdapter);


        Coffee = findViewById(R.id.button3);
        Drinks = findViewById(R.id.button2);
        Cake = findViewById(R.id.button);




        recyclerView = findViewById(R.id.rcv_drink);
        gridLayoutManager = new GridLayoutManager(this, 2);//2 cọt
        recyclerView.setLayoutManager(gridLayoutManager);

        DrinkAdapter drinkAdapter = new DrinkAdapter(getList());
        recyclerView.setAdapter(drinkAdapter);

        Coffee.setOnClickListener(this);
        Drinks.setOnClickListener(this);
        Cake.setOnClickListener(this);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, chi_la_test_function.class);
                startActivity(intent);
            }
        });
        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Account.class);
                startActivity(intent);
            }
        });
    }

    private List<Drink> getList() {
        List<Drink> list = new ArrayList<>();
        list.add(new Drink(R.drawable.c1, "Coffee Latte",Drink.TYPE_COFFEE, "25.000", R.drawable.star));
        list.add(new Drink(R.drawable.c2, "Coffee Robusta",Drink.TYPE_COFFEE, "20.000", R.drawable.star));
        list.add(new Drink(R.drawable.c3, "Coffee Mocha",Drink.TYPE_COFFEE, "30.000", R.drawable.star));
        list.add(new Drink(R.drawable.c4, "Coffee Cherry",Drink.TYPE_COFFEE, "25.000", R.drawable.star));
        list.add(new Drink(R.drawable.c5, "Coffee Culi",Drink.TYPE_COFFEE, "20.000", R.drawable.star));
        list.add(new Drink(R.drawable.c6, "Coffee Espresso",Drink.TYPE_DRINKS, "20.000", R.drawable.star));
        list.add(new Drink(R.drawable.c7, "Coffee Capuchino",Drink.TYPE_DRINKS, "20.000", R.drawable.star));
        list.add(new Drink(R.drawable.c8, "Coffee Macchiato",Drink.TYPE_DRINKS, "20.000", R.drawable.star));
        list.add(new Drink(R.drawable.c9, "Coffee Cubano",Drink.TYPE_DRINKS, "20.000", R.drawable.star));
        list.add(new Drink(R.drawable.c10, "Coffee Antoccino",Drink.TYPE_DRINKS, "20.000", R.drawable.star));


        //Drinks
        list.add(new Drink(R.drawable.d1, "Pepsi",Drink.TYPE_DRINKS, "20.000", R.drawable.star));
        list.add(new Drink(R.drawable.d2, "Revive",Drink.TYPE_DRINKS, "20.000", R.drawable.star));
        list.add(new Drink(R.drawable.d3, "7 Up",Drink.TYPE_DRINKS, "20.000", R.drawable.star));
        list.add(new Drink(R.drawable.d4, "Mirinda",Drink.TYPE_DRINKS, "20.000", R.drawable.star));
        list.add(new Drink(R.drawable.d5, "Trà Xanh Không Độ",Drink.TYPE_DRINKS, "20.000", R.drawable.star));
        list.add(new Drink(R.drawable.d6, "RedBull",Drink.TYPE_DRINKS, "20.000", R.drawable.star));
        list.add(new Drink(R.drawable.d7, "Sprite",Drink.TYPE_DRINKS, "20.000", R.drawable.star));
        list.add(new Drink(R.drawable.d8, "Trà ÔLong",Drink.TYPE_DRINKS, "20.000", R.drawable.star));
        list.add(new Drink(R.drawable.d9, "Number 1",Drink.TYPE_DRINKS, "20.000", R.drawable.star));
        list.add(new Drink(R.drawable.d10, "Trà Xanh C2",Drink.TYPE_DRINKS, "20.000", R.drawable.star));
        list.add(new Drink(R.drawable.d11, "Fanta",Drink.TYPE_DRINKS, "20.000", R.drawable.star));
        list.add(new Drink(R.drawable.d12, "Coca-CoLa",Drink.TYPE_DRINKS, "20.000", R.drawable.star));


        //CAKE
        list.add(new Drink(R.drawable.ca1, "Bánh Su Kem",Drink.TYPE_DRINKS, "20.000", R.drawable.star));
        list.add(new Drink(R.drawable.ca2, "Cupcake",Drink.TYPE_DRINKS, "20.000", R.drawable.star));
        list.add(new Drink(R.drawable.ca3, "Bánh rán Dorayaki",Drink.TYPE_DRINKS, "20.000", R.drawable.star));
        list.add(new Drink(R.drawable.ca4, "Muffin",Drink.TYPE_DRINKS, "20.000", R.drawable.star));
        list.add(new Drink(R.drawable.ca5, "Pancake",Drink.TYPE_DRINKS, "20.000", R.drawable.star));
        list.add(new Drink(R.drawable.ca6, "Tiramisu",Drink.TYPE_DRINKS, "20.000", R.drawable.star));
        list.add(new Drink(R.drawable.ca7, "Cheesecake",Drink.TYPE_DRINKS, "20.000", R.drawable.star));
        list.add(new Drink(R.drawable.ca8, "Salted egg sponge",Drink.TYPE_DRINKS, "20.000", R.drawable.star));
        list.add(new Drink(R.drawable.ca9, "Bánh ngọt Donut",Drink.TYPE_DRINKS, "20.000", R.drawable.star));
        list.add(new Drink(R.drawable.ca10, "Savoure",Drink.TYPE_DRINKS, "20.000", R.drawable.star));
        list.add(new Drink(R.drawable.ca11, "Bánh Táo Mĩ",Drink.TYPE_DRINKS, "20.000", R.drawable.star));
        list.add(new Drink(R.drawable.ca12, "Black Forest Đức",Drink.TYPE_DRINKS, "20.000", R.drawable.star));


        return list;
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
        if(gridLayoutManager == null){
            return;
        }
        gridLayoutManager.scrollToPositionWithOffset(index,0);
    }
}