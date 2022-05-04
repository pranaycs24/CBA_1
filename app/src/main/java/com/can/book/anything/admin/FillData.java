package com.can.book.anything.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FillData extends AppCompatActivity {
    private TextView header;
    private EditText name, phNumber, address, serviceArea;
    private Button save;
    private Spinner city;
    private String cityName;
    private static final String TAG = "FillData";

    private static final String KEY_NAME = "personName";
    private static final String KEY_PH = "personPhoneNumber";
    private static final String KEY_ADD = "personAddress";
    private static final String KEY_SERVICE = "personServiceArea";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_data);
        header = findViewById(R.id.head);
        name = findViewById(R.id.editTextName);
        phNumber = findViewById(R.id.editTextPhone);
        address = findViewById(R.id.editTextAdress);
        serviceArea = findViewById(R.id.editTextService);
        city = findViewById(R.id.spinner);
        save = findViewById(R.id.saveData);


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
        Intent intent = getIntent();
        String from = intent.getStringExtra("from");
        String cityIntent = intent.getStringExtra("city");
        header.setText(from);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cityIntent.equals(cityName)) {
                    String personName = name.getText().toString();
                    String phoneNumber = phNumber.getText().toString();
                    String personAddress = address.getText().toString();
                    String personServiceArea = serviceArea.getText().toString();

//
                    PersonalData data = new PersonalData(personName, phoneNumber,
                            personAddress, personServiceArea, cityName);

//                db.collection(from).document("FirstCook").set(data)
                    db.collection(cityName + "-" + from).add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(FillData.this, "DataSaved", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(FillData.this, "Error", Toast.LENGTH_SHORT).show();
//                                Log.d(TAG, e.toString());
                        }
                    });
                }
                else
                    Toast.makeText(FillData.this, "City Mismatched", Toast.LENGTH_SHORT).show();
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void unused) {
//                                Toast.makeText(FillData.this, "DataSaved", Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(FillData.this, "Error", Toast.LENGTH_SHORT).show();
//                                Log.d(TAG, e.toString());
//                            }
//                        });

            }
        });
    }
}