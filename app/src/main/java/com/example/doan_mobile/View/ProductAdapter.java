package com.example.doan_mobile.View;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.doan_mobile.Database.Product;
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
        // Bind data to ViewHolder
        holder.txtProductName.setText(product.getName());
        holder.txtProductType.setText(product.getType());
        holder.txtProductPrice.setText(String.valueOf(product.getPrice()));
        // Load image using your preferred image loading library (e.g., Glide, Picasso)
        // Example with Glide:
        Glide.with(holder.itemView.getContext())
                .load(product.getImage())
                .placeholder(R.drawable.c1)
                .into(holder.imgProductImage);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProductImage;
        TextView txtProductName, txtProductType, txtProductPrice;

        ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProductImage = itemView.findViewById(R.id.product_image);
            txtProductName = itemView.findViewById(R.id.product_name);
            txtProductType = itemView.findViewById(R.id.product_type);
            txtProductPrice = itemView.findViewById(R.id.product_price);
        }
    }
}