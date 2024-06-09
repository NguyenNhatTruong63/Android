//package com.example.doan_mobile.ScowView;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.doan_mobile.R;
//
//import java.util.List;
//
//public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder> {
//    private List<Drink>listDrink;
//
//    public DrinkAdapter(List<Drink> listDrink) {
//        this.listDrink = listDrink;
//    }
//
//    @NonNull
//    @Override
//    public DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_trangchu, parent,false);
//        return new DrinkViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull DrinkViewHolder holder, int position) {
//        Drink drink = listDrink.get(position);
//        if(drink == null){
//            return;
//        }
//        holder.image.setImageResource(drink.getImage());
//        holder.name.setText(drink.getName());
//        holder.price.setText(drink.getPrice());
//        holder.star.setImageResource(drink.getStar());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        if(listDrink !=null){
//            return listDrink.size();
//        }
//        return 0;
//    }
//
//    public class DrinkViewHolder extends RecyclerView.ViewHolder{
//
//        private ImageView image;
//        private ImageView star;// ngoi sao
//        private TextView name;
//        private TextView price;// gia tien
//
//
//        public DrinkViewHolder(@NonNull View itemView) {
//            super(itemView);
//            image = itemView.findViewById(R.id.imageView1);
//            name = itemView.findViewById(R.id.text_cdc);
//            price = itemView.findViewById(R.id.rp_view);
//            star = itemView.findViewById(R.id.start_view);
//        }
//    }
//}
