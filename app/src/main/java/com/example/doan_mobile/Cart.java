package com.example.doan_mobile;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
<<<<<<< HEAD

import android.os.Bundle;
=======
>>>>>>> bdadfb6c573033a79dd7449ae21cc8b2927f07bf

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Cart extends AppCompatActivity {

<<<<<<< HEAD
=======

>>>>>>> bdadfb6c573033a79dd7449ae21cc8b2927f07bf
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
<<<<<<< HEAD

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cart), (v, insets) -> {

=======
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cart), (v, insets) -> {
>>>>>>> bdadfb6c573033a79dd7449ae21cc8b2927f07bf
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


//        TextView txt = findViewById(R.id.scroll_cart);
//        txt.setMovementMethod(new ScrollingMovementMethod());
    }
}
<<<<<<< HEAD

    

=======
>>>>>>> bdadfb6c573033a79dd7449ae21cc8b2927f07bf
