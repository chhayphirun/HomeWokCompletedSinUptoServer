package com.example.custome_dialog;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnshowdialog=(Button)findViewById(R.id.button);
        btnshowdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mbuilder=new AlertDialog.Builder(MainActivity.this);
                View view=getLayoutInflater().inflate(R.layout.custom_dialog,null);
                final EditText email=(EditText)view.findViewById(R.id.editText);
                final EditText password=(EditText)view.findViewById(R.id.editText2);
                Button mlogin=(Button)view.findViewById(R.id.btnlogin1);
                mlogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!email.getText().toString().isEmpty()&&!password.getText().toString().isEmpty()){
                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(MainActivity.this, "Please input all fields", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                                mbuilder.setView(view);
                AlertDialog alerdialog=mbuilder.create();
                alerdialog.show();
            }
        });
    }
}
