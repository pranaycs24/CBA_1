package com.can.book.anything.admin;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button feedData, fetchData;
    Dialog userAgreement;
    private static final String TAG = "MainActivity_check";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userAgreement  = new Dialog(this);
        userAgreement.setContentView(R.layout.user_agreement);
        userAgreement.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT
        , ViewGroup.LayoutParams.WRAP_CONTENT);
        userAgreement.setCancelable(false);

        Button accept = userAgreement.findViewById(R.id.button);
        TextView textView = userAgreement.findViewById(R.id.useragri_content);
        textView.setText(getTermsString());
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userAgreement.dismiss();
            }
        });
        userAgreement.show();
//        Log.d(TAG, "onCreate: "+getTermsString());
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

    private String getTermsString() {
        StringBuilder termsString = new StringBuilder();
        Reader reader;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getResources().openRawResource(R.raw.user_agreement)));

            String str;
            while ((str = ((BufferedReader) reader).readLine()) != null) {
                termsString.append(str);
            }
            reader.close();
            return termsString.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}