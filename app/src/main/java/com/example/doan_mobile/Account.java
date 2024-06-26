package com.example.doan_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.doan_mobile.cart.UserSessionManager;

public class Account extends AppCompatActivity {
    private UserSessionManager session;
    private TextView tvUserName, loginaccount;
    private TextView btnLogout;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loginaccount = findViewById(R.id.loginaccount);
        loginaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Account.this, Login.class);
                startActivity(intent);
            }
        });
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Account.this, Home.class);
                startActivity(intent);
            }
        });



        session = new UserSessionManager(getApplicationContext());
        tvUserName = findViewById(R.id.tvUserName);
        btnLogout = findViewById(R.id.tvLogout);

        // Lấy tên khách hàng từ session
        String name = session.getUserDetails().get(UserSessionManager.KEY_NAME);
        tvUserName.setText(name);

        // Thiết lập sự kiện cho nút Logout
        btnLogout.setOnClickListener(v -> {
            session.logoutUser();
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView orderHistoryTextView = findViewById(R.id.orderHistoryTextView);
        orderHistoryTextView.setOnClickListener(v -> {
            Intent intent = new Intent(Account.this, OrderHistoryActivity.class);
            startActivity(intent);
        });

    }
}