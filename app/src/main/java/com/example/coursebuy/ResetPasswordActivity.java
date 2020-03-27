package com.example.coursebuy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coursebuy.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ResetPasswordActivity extends AppCompatActivity {

    private String check = "";
    private TextView pagetitle,titleQues;
    private EditText phonenumber,ques1,ques2;
    private Button verify;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);


        check = getIntent().getStringExtra("check");
        pagetitle=findViewById(R.id.total_price);
        titleQues=findViewById(R.id.title_questions);
        phonenumber=findViewById(R.id.find_phone_number);
        ques1=findViewById(R.id.question_1);
        ques2=findViewById(R.id.question_2);
        verify=(Button)findViewById(R.id.verify_btn);
    }



    @Override
    protected void onStart()
    {
        super.onStart();

        phonenumber.setVisibility(View.GONE);

        if (check.equals("settings"))
        { displayanswer();
            pagetitle.setText("Set Question");
            titleQues.setText("Please set answer for security purpose");
            verify.setText("Set");
            verify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  setAnswer();
                }
            });

        }
        else if (check.equals("login"))
        {
phonenumber.setVisibility(View.VISIBLE);
        }
    }
    private void setAnswer(){
        String quest1=ques1.getText().toString().toLowerCase();
        String quest2=ques2.getText().toString().toLowerCase();
        if(quest1.equals("")&&quest2.equals("")){
            Toast.makeText(ResetPasswordActivity.this,"Please Give all Details",Toast.LENGTH_LONG).show();
        }
        else{
            DatabaseReference data= FirebaseDatabase.getInstance().getReference()
                    .child("Users").child(Prevalent.currentOnlineUser.getPhone());
            HashMap<String, Object> userdatamap = new HashMap<>();
            userdatamap.put("answer1", quest1);
            userdatamap.put("answer2", quest2);
            data.child("Security Answer").updateChildren(userdatamap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if(task.isSuccessful()){
                        Toast.makeText(ResetPasswordActivity.this,"you have filled details",Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(ResetPasswordActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                }
            });

        }
    }
    private void displayanswer(){
        DatabaseReference data= FirebaseDatabase.getInstance().getReference()
                .child("Users").child(Prevalent.currentOnlineUser.getPhone());
        data.child("Security Answer").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String ans1=dataSnapshot.child("answer1").getValue().toString();
                String ans2=dataSnapshot.child("answer2").getValue().toString();
                ques1.setText(ans1);
                ques2.setText(ans2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
