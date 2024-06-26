package com.example.doan_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.doan_mobile.Database.Product;
import com.example.doan_mobile.cart.Cart;
import com.example.doan_mobile.cart.CartActivity;

public class DetailCake extends AppCompatActivity {
    private ImageView productImage, backdetail;
    private TextView productName, productPrice;
    private RadioGroup sizeGroup;
    private Button btnAddToCart, btnViewCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cake);

        productImage = findViewById(R.id.product_image);
        productName = findViewById(R.id.product_name);
        productPrice = findViewById(R.id.product_price);
        sizeGroup = findViewById(R.id.size_group);
        btnAddToCart = findViewById(R.id.btn_add_to_cart);
        btnViewCart = findViewById(R.id.btn_view_cart);

        // Nhận dữ liệu từ intent
        Intent intent = getIntent();
        Product product = (Product) intent.getSerializableExtra("product");

        // Thiết lập dữ liệu vào view
        if (product != null) {
            productName.setText(product.getName());
            Glide.with(this).load(product.getImage()).into(productImage);
            productPrice.setText(String.format("$%.2f", product.getPrice()));
        }

        // Xử lý nút Add to Cart
        btnAddToCart.setOnClickListener(v -> {
            // Lấy giá trị từ các tùy chọn
            int selectedSizeId = sizeGroup.getCheckedRadioButtonId();

            if (selectedSizeId != -1) {
                String selectedSize = ((RadioButton) findViewById(selectedSizeId)).getText().toString();

                // Thêm sản phẩm vào giỏ hàng
                Cart.getInstance().addToCart(product, 1); // Mặc định thêm 1 sản phẩm

                Toast.makeText(DetailCake.this, "Added to cart", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(DetailCake.this, "Please select a size", Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý nút View Cart
        btnViewCart.setOnClickListener(v -> {
            Intent cartIntent = new Intent(DetailCake.this, CartActivity.class);
            startActivity(cartIntent);
        });

        backdetail = findViewById(R.id.backdetail);
        backdetail.setOnClickListener(v -> {
            Intent backIntent = new Intent(DetailCake.this, Home.class);
            startActivity(backIntent);
        });
    }
}
