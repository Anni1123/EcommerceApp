package com.example.coursebuy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coursebuy.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

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

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });
    }
    private void check(){

        if (TextUtils.isEmpty(nameEdittext.getText().toString()))
        {
            Toast.makeText(this, "Name is mandatory.", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(addressEditText.getText().toString()))
        {
            Toast.makeText(this, "Address is address.", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(phoneEditText.getText().toString()))
        {
            Toast.makeText(this, "PhoneNumber is mandatory.", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(cityEditText.getText().toString()))
        {
            Toast.makeText(this, "City is mandatory.", Toast.LENGTH_SHORT).show();
        }
        else{
            ConfirmOrder();
        }
    }
    private void ConfirmOrder(){
         final String saveCurrentTime, saveCurrentDate;
        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentDate.format(calForDate.getTime());
        final DatabaseReference orderre= FirebaseDatabase.getInstance().getReference().child("Orders").child(Prevalent.currentOnlineUser.getPhone());
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("totalamount", totalamount);
        hashMap.put("pname", nameEdittext.getText().toString());
        hashMap.put("phonr", phoneEditText.getText().toString());
        hashMap.put("address", addressEditText.getText().toString());
        hashMap.put("city", cityEditText.getText().toString());
        hashMap.put("date", saveCurrentDate);
        hashMap.put("time", saveCurrentTime);
        hashMap.put("state","not shipped");
        orderre.updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    FirebaseDatabase.getInstance().getReference().child("Cart List").child("User View").child(Prevalent.currentOnlineUser.getPhone())
                            .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                       Toast.makeText(ConfirmFinalOrderActivity.this,"Order Successfully",Toast.LENGTH_LONG).show();
                      Intent adIntent= new Intent(ConfirmFinalOrderActivity.this,HomeActivity.class);
                        adIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(adIntent);
                        finish();
                        }

                    });
                }
            }
        });
    }
}
