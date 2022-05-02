package com.can.book.anything.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Category extends AppCompatActivity implements View.OnClickListener {
    private Button cook, maid, carpenter, gardener, electrician, mechanic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagory);
        cook = findViewById(R.id.cook);
        maid = findViewById(R.id.maid);
        carpenter =  findViewById(R.id.carpenter);
        gardener = findViewById(R.id.gardener);
        electrician = findViewById(R.id.electric);
        mechanic = findViewById(R.id.mechanic);



        cook.setOnClickListener(this);
        maid.setOnClickListener(this);
        carpenter.setOnClickListener(this);
        gardener.setOnClickListener(this);
        electrician.setOnClickListener(this);
        mechanic.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent fillFetch = getIntent();
        String forWhat = fillFetch.getStringExtra("for_what");
        Intent intent;
        if(forWhat.equals("feed")) {
            intent = new Intent(Category.this, FillData.class);
        }else{
            intent = new Intent(Category.this, FetchData.class);
        }
        switch (view.getId()){
            case R.id.cook:
                intent.putExtra("from", "Cook");
                startActivity(intent);
                break;
            case R.id.maid:
                intent.putExtra("from", "Maid");
                startActivity(intent);
                break;
            case R.id.carpenter:
                intent.putExtra("from", "Carpenter");
                startActivity(intent);
                break;
            case R.id.gardener:
                intent.putExtra("from", "Gardener");
                startActivity(intent);
                break;
            case R.id.electric:
                intent.putExtra("from", "Electrician");
                startActivity(intent);
                break;
            case R.id.mechanic:
                intent.putExtra("from", "Mechanic");
                startActivity(intent);
                break;

        }

    }
}