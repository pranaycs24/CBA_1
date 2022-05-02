package com.can.book.anything.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button feedData, fetchData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        feedData = findViewById(R.id.feed);
        fetchData = findViewById(R.id.fetch);
        feedData.setOnClickListener(this);
        fetchData.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, Category.class);
        switch (view.getId()){
            case R.id.feed:
                intent.putExtra("for_what", "feed");
                startActivity(intent);
                break;
            case R.id.fetch:
                intent.putExtra("for_what", "fetch");
                startActivity(intent);
                break;

        }
    }
}