package com.example.newpatientapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SearchPatientActivity extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5;
    AppCompatButton b1,b2,b3;
    String getMob,getCode,getName,getAddress,getDrname;
    DbHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_patient);
        e1=(EditText) findViewById(R.id.smob);
        e2=(EditText) findViewById(R.id.pcode);
        e3=(EditText) findViewById(R.id.pname);
        e4=(EditText) findViewById(R.id.address);
        e5=(EditText) findViewById(R.id.drname);
        b1=(AppCompatButton) findViewById(R.id.search);
        b2=(AppCompatButton) findViewById(R.id.delete);
        b3=(AppCompatButton) findViewById(R.id.update);
        mydb=new DbHelper(this);
        mydb.getWritableDatabase();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMob=e1.getText().toString();

                Cursor c=mydb.searchPatient(getMob);
                if (!getMob.matches("^[6-9]\\d{9}$"))
                {
                    Toast.makeText(getApplicationContext(), "Invalied Phone number", Toast.LENGTH_SHORT).show();


                }
                else if (c.getCount()==0)
                {
                    Toast.makeText(getApplicationContext(), "No patient found", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    while (c.moveToNext())
                    {
                        getCode=c.getString(1);
                        getName=c.getString(2);
                        getAddress=c.getString(3);
                        getDrname=c.getString(5);
                    }
                    e2.setText(getCode);
                    e3.setText(getName);
                    e4.setText(getAddress);
                    e5.setText(getDrname);
                }

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMob=e1.getText().toString();

                boolean status=mydb.patientDelete(getMob);
                if (status==true)
                {
                    e1.setText("");
                    e2.setText("");
                    e3.setText("");
                    e4.setText("");
                    e5.setText("");
                    Toast.makeText(getApplicationContext(), "Successfully deleted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Deletion Failed", Toast.LENGTH_SHORT).show();
                }


            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMob=e1.getText().toString();
                getCode=e2.getText().toString();
                getName=e3.getText().toString();
                getAddress=e4.getText().toString();
                getDrname=e5.getText().toString();

                boolean status=mydb.upadatePatient(getMob,getCode,getName,getAddress,getDrname);
                if (status==true)
                {

                    Toast.makeText(getApplicationContext(), "Successfully Updated", Toast.LENGTH_SHORT).show();
                    e1.setText("");
                    e2.setText("");
                    e3.setText("");
                    e4.setText("");
                    e5.setText("");
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Update Failed"  , Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}