package com.example.coursebuy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private Button createacntbtn;
    private EditText Inputname,inputpassword,inputnumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Inputname=(EditText)findViewById(R.id.register_username);
        inputpassword=(EditText)findViewById(R.id.register_password);
        inputnumber=(EditText)findViewById(R.id.register_phone_number);
        createacntbtn=(Button)findViewById(R.id.register_btn);
    }
}
