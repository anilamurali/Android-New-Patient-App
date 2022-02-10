package com.example.newpatientapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddPatientActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4,ed5;
    AppCompatButton b1;
    String getCode,getName,getAddress,getMob,getDrname;
    DbHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);
        ed1=(EditText) findViewById(R.id.pcode);
        ed2=(EditText) findViewById(R.id.pname);
        ed3=(EditText) findViewById(R.id.address);
        ed4=(EditText) findViewById(R.id.mob);
        ed5=(EditText) findViewById(R.id.dname);
        b1=(AppCompatButton) findViewById(R.id.sub1);
        mydb=new DbHelper(this);
        mydb.getReadableDatabase();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCode=ed1.getText().toString();
                getName=ed2.getText().toString();
                getAddress=ed3.getText().toString();
                getMob=ed4.getText().toString();
                getDrname=ed5.getText().toString();
                boolean status=mydb.insertPatient(getCode,getName,getAddress,getMob,getDrname);
                if (status==true)
                {
                    Toast.makeText(getApplicationContext(), "Successfully inserted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}