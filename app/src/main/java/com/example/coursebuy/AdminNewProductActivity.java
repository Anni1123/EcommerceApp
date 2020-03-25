package com.example.coursebuy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AdminNewProductActivity extends AppCompatActivity {

    private String categoryname;
    private Button addnewproduct;
    private ImageView inputimage;
    private EditText inputname,inputdesc,inputprice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_new_product);
        categoryname=getIntent().getExtras().get("category").toString();

        addnewproduct=(Button)findViewById(R.id.add_new_product);
        inputdesc=(EditText)findViewById(R.id.product_description);
        inputname=(EditText)findViewById(R.id.product_name);
        inputprice=(EditText)findViewById(R.id.product_price);
        inputimage=(ImageView)findViewById(R.id.select_product_image);


    }
}
