package com.example.coursebuy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConfirmFinalOrderActivity extends AppCompatActivity {

    private EditText nameEdittext,phoneEditText,addressEditText,cityEditText;
    private Button confirmButton;
    private String totalamount="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_final_order);
        totalamount=getIntent().getStringExtra("Total Price");
        Toast.makeText(ConfirmFinalOrderActivity.this,totalamount,Toast.LENGTH_LONG).show();
        nameEdittext=(EditText)findViewById(R.id.yourname);
        phoneEditText=(EditText)findViewById(R.id.yourphone);
        addressEditText=(EditText)findViewById(R.id.youraddress);
        cityEditText=(EditText)findViewById(R.id.yourcity);
        confirmButton=(Button)findViewById(R.id.confirm);

    }
}
