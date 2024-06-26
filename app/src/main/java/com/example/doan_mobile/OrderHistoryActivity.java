package com.example.doan_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.doan_mobile.Database.OrderHelper;
import com.example.doan_mobile.View.OrderHistoryAdapter;
import com.example.doan_mobile.cart.UserSessionManager;

import java.util.List;

public class OrderHistoryActivity extends AppCompatActivity {

    private ListView listView;
    private OrderHistoryAdapter adapter;
    private OrderHelper orderHelper;
    private UserSessionManager session;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_history);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listView = findViewById(R.id.order_list);
        session = new UserSessionManager(getApplicationContext());
        orderHelper = new OrderHelper(this);

        // Lấy danh sách đơn hàng từ cơ sở dữ liệu
        String customerName = session.getUserDetails().get(UserSessionManager.KEY_NAME);
        List<Order> orders = orderHelper.getOrderHistory(customerName);

        if (orders.isEmpty()) {
            Toast.makeText(this, "Không có lịch sử đặt hàng", Toast.LENGTH_SHORT).show();
        } else {
            adapter = new OrderHistoryAdapter(this, orders);
            listView.setAdapter(adapter);
        }
        back = findViewById(R.id.backhistory);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderHistoryActivity.this, Account.class);
                startActivity(intent);
            }
        });
    }
}