package com.example.newpatientapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddPatientActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4,ed5;
    TextView t1;
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
        t1=(TextView) findViewById(R.id.bd);
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
                if (!getMob.matches("^[6-9]\\d{9}$"))
                {
                    Toast.makeText(getApplicationContext(), "Invalied Phone number", Toast.LENGTH_SHORT).show();


                }
                else if(status==true)
                {
                    ed1.setText("");
                    ed2.setText("");
                    ed3.setText("");
                    ed4.setText("");
                    ed5.setText("");
                    Toast.makeText(getApplicationContext(), "Successfully inserted", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

    }
}