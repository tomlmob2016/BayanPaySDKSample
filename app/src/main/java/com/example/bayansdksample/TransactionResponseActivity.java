package com.example.bayansdksample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import models.TransactionResponse;

public class TransactionResponseActivity extends AppCompatActivity {

    TextView textView;
    Button btnTxn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_response);
        textView = (TextView) findViewById(R.id.txt);
        btnTxn = (Button) findViewById(R.id.btnTxn);

        TransactionResponse transactionResponse = getIntent().getParcelableExtra("transactionResponse");


        if (transactionResponse.getStatusFlag().equals("SUCCESS"))
            textView.setText(transactionResponse.toString());
        else {

            textView.setText(transactionResponse.toString());// As per basantas suggestions.

//            if (!TextUtils.isEmpty(transactionResponse.getErrorMessage())) {
//               // textView.setText(transactionResponse.getErrorMessage());
//                textView.setText(transactionResponse.toString());// As per basantas suggestions.
//            } else {
//                textView.setText("Error Occurred!!!");
//            }
        }

        btnTxn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TransactionResponseActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}
