package com.example.doan_mobile.View;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_mobile.Database.Product;
import com.example.doan_mobile.ProductDetailActivity;
import com.example.doan_mobile.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> productList;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        public ImageView productImage;
        public TextView productName, productPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
        }

        public void bind(final Product product) {
            productImage.setImageResource(product.getImage());
            productName.setText(product.getName());
            productPrice.setText(String.valueOf(product.getPrice()));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Truyền dữ liệu sang activity chi tiết
                    Intent intent = new Intent(v.getContext(), ProductDetailActivity.class);
                    intent.putExtra("PRODUCT_NAME", product.getName());
                    intent.putExtra("PRODUCT_IMAGE", product.getImage());
                    intent.putExtra("PRODUCT_PRICE", product.getPrice());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
