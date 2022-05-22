package com.can.book.anything.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Category extends AppCompatActivity implements View.OnClickListener {
    private Button cook, maid, carpenter, gardener, electrician, mechanic, tutor, grocery;
    private Spinner city;
    private String cityName;
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
        tutor = findViewById(R.id.tutor);
        grocery = findViewById(R.id.grocery);

        city = findViewById(R.id.spinner2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(adapter);

        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cityName = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        cook.setOnClickListener(this);
        maid.setOnClickListener(this);
        carpenter.setOnClickListener(this);
        gardener.setOnClickListener(this);
        electrician.setOnClickListener(this);
        mechanic.setOnClickListener(this);
        tutor.setOnClickListener(this);
        grocery.setOnClickListener(this);
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
        intent.putExtra("city", cityName);
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
            case R.id.tutor:
                intent.putExtra("from", "Tutor");
                startActivity(intent);
                break;
            case R.id.grocery:
                intent.putExtra("from", "Grocery");
                startActivity(intent);
                break;

        }

    }
}