package com.example.doan_mobile;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class chi_la_test_function extends AppCompatActivity {
    private DatabaseHelper databasetest;
    EditText username, password;
    TextView add, view;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chi_la_test_function);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });


        databasetest = new DatabaseHelper(this);
        username = findViewById(R.id.username);
        password = findViewById(R.id.passwordts);
        add= findViewById(R.id.addtest);
        view = findViewById(R.id.viewtest);

        listView = findViewById(R.id.view_test_list);
        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query();
            }
        });
    }
    public void add(){
        String name = username.getText().toString();
        String pass = password.getText().toString();
        SQLiteDatabase db= databasetest.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("pass", pass);
        long insert= db.insert("food", null, values);

        if(insert != -1){
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();

        }
    }
    public void query(){
        SQLiteDatabase db= databasetest.getWritableDatabase();
        Cursor cursor = db.query("food",null,null,null,null,null,null);
        if(cursor != null){
            while(cursor.moveToNext()){
                int nameIndex = cursor.getColumnIndexOrThrow("name");
                int passIndex = cursor.getColumnIndexOrThrow("pass");
                String name = cursor.getString(nameIndex);
                String pass = cursor.getString(passIndex);
                list.add(name+ "-----"+pass);

            }
            cursor.close();
        }
        adapter.notifyDataSetChanged(); // Notify the adapter to update the ListView

    }
}