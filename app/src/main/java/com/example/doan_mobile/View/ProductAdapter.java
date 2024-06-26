// ProductAdapter.java
package com.example.doan_mobile.View;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.doan_mobile.Database.Product;
import com.example.doan_mobile.DetailCake;
import com.example.doan_mobile.DetailDrink;
import com.example.doan_mobile.ProductDetailActivity;
import com.example.doan_mobile.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productName.setText(product.getName());
        holder.productPrice.setText(String.format("$%.2f", product.getPrice()));
        holder.productType.setText(product.getType());

        Glide.with(context).load(product.getImage()).into(holder.productImage);

        holder.itemView.setOnClickListener(v -> {
            Intent intent;
            switch (product.getType()) {
                case "Coffee":
                    intent = new Intent(context, ProductDetailActivity.class);
                    break;
                case "Drink":
                    intent = new Intent(context, DetailDrink.class);
                    break;
                case "Cake":
                    intent = new Intent(context, DetailCake.class);
                    break;
                default:
                    return;
            }
            intent.putExtra("product", product);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productPrice, productType;
        ImageView productImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
            productType = itemView.findViewById(R.id.product_type);
            productImage = itemView.findViewById(R.id.product_image);
        }
    }
}
