package com.example.coursebuy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coursebuy.Model.Users;
import com.example.coursebuy.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.CheckBox;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {

    private EditText InputPhoneNumber, InputPassword;
    private Button LoginButton;
    private ProgressDialog loadingBar;
    private TextView AdminLink, NotAdminLink;
    private String parentdbname="Users";
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginButton = (Button) findViewById(R.id.login_btn);
        InputPassword = (EditText) findViewById(R.id.login_password);
        InputPhoneNumber = (EditText) findViewById(R.id.login_phone_number);
        AdminLink = (TextView) findViewById(R.id.admin_panel_link);
        NotAdminLink = (TextView) findViewById(R.id.not_admin_panel_link);
        AdminLink=(TextView)findViewById(R.id.admin_panel_link);
        NotAdminLink=(TextView)findViewById(R.id.not_admin_panel_link);
        checkBox=(com.rey.material.widget.CheckBox)findViewById(R.id.remember_me_chkb);
        Paper.init(this);
        loadingBar = new ProgressDialog(this);



        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                LoginUser();
            }
        });
AdminLink.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        LoginButton.setText("Login Admin");
        AdminLink.setVisibility(View.INVISIBLE);
        NotAdminLink.setVisibility(View.VISIBLE);
        parentdbname="Admins";
    }
});
NotAdminLink.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        LoginButton.setText("Login");
        AdminLink.setVisibility(View.VISIBLE);
        NotAdminLink.setVisibility(View.INVISIBLE);
        parentdbname="Users";
    }
});

    }
    private void LoginUser(){

        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();



        if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        }
        else{
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();


            AllowAccessToAccount(phone, password);

        }

    }




    private void AllowAccessToAccount(final String phone, final String password) {

        if(checkBox.isChecked()){
            Paper.book().write(Prevalent.UserPhonekey,phone);
            Paper.book().write(Prevalent.UserPassword,password);
        }
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(parentdbname).child(phone).exists()){
                    Users userdata=dataSnapshot.child(parentdbname).child(phone).getValue(Users.class);
                    if(userdata.getPhone().equals(phone)){
                        if(userdata.getPassword().equals(password)){

                           if(parentdbname.equals("Admins")){
                               Toast.makeText(LoginActivity.this, "Welcome Admin, you are logged in Successfully...", Toast.LENGTH_SHORT).show();
                               loadingBar.dismiss();


                               Intent intent = new Intent(LoginActivity.this, AdminCategoryActivity.class);
                               startActivity(intent);
                           }
                           else if(parentdbname.equals("Users")){
                               Toast.makeText(LoginActivity.this, "Welcome Admin, you are logged in Successfully...", Toast.LENGTH_SHORT).show();
                               loadingBar.dismiss();


                               Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                               Prevalent.currentOnlineUser=userdata;
                               startActivity(intent);
                           }
                        }
                        else
                        {
                            loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, "Password is incorrect.", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                else {

                    Toast.makeText(LoginActivity.this, "Account with this " + phone + " number do not exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
