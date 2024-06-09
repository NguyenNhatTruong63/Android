package com.example.doan_mobile;

import android.content.Intent;
import android.os.Bundle;
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

public class ProductDetailActivity extends AppCompatActivity {
    private ImageView productImage;
    private TextView productName, productPrice;
    private RadioGroup variantGroup, sugarGroup, milkGroup, iceGroup, sizeGroup;
    private Button btnAddToCart;
    private Button btnViewCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        productImage = findViewById(R.id.product_image_detail);
        productName = findViewById(R.id.product_name_detail);
        productPrice = findViewById(R.id.product_price_detail);
        variantGroup = findViewById(R.id.variant_group);
        sugarGroup = findViewById(R.id.sugar_group);
        milkGroup = findViewById(R.id.milk_group);
        iceGroup = findViewById(R.id.ice_group);
        sizeGroup = findViewById(R.id.size_group);
        btnAddToCart = findViewById(R.id.btn_add_to_cart);
        btnViewCart = findViewById(R.id.btn_view_cart);

        // Nhận dữ liệu từ intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("PRODUCT_NAME");
        int imageResId = intent.getIntExtra("PRODUCT_IMAGE", 0);
        double price = intent.getDoubleExtra("PRODUCT_PRICE", 0.0);

        // Thiết lập dữ liệu vào view
        productName.setText(name);
        Glide.with(this).load(imageResId).into(productImage);
        productPrice.setText(String.format("$%.2f", price));

        // Xử lý nút Add to Cart
        btnAddToCart.setOnClickListener(v -> {
            // Lấy giá trị từ các tùy chọn
            int selectedVariantId = variantGroup.getCheckedRadioButtonId();
            int selectedSugarId = sugarGroup.getCheckedRadioButtonId();
            int selectedMilkId = milkGroup.getCheckedRadioButtonId();
            int selectedIceId = iceGroup.getCheckedRadioButtonId();
            int selectedSizeId = sizeGroup.getCheckedRadioButtonId();

            String selectedVariant = ((RadioButton) findViewById(selectedVariantId)).getText().toString();
            String selectedSugar = ((RadioButton) findViewById(selectedSugarId)).getText().toString();
            String selectedMilk = ((RadioButton) findViewById(selectedMilkId)).getText().toString();
            String selectedIce = ((RadioButton) findViewById(selectedIceId)).getText().toString();
            String selectedSize = ((RadioButton) findViewById(selectedSizeId)).getText().toString();

            // Tạo đối tượng Product và thêm vào giỏ hàng
            Product product = new Product(name, imageResId, price);
            Cart.getInstance().addToCart(product, 1); // Mặc định thêm 1 sản phẩm

            Toast.makeText(ProductDetailActivity.this, "Added to cart", Toast.LENGTH_SHORT).show();
        });

        // Xử lý nút View Cart
        btnViewCart.setOnClickListener(v -> {
            Intent cartIntent = new Intent(ProductDetailActivity.this, CartActivity.class);
            startActivity(cartIntent);
        });
    }
}