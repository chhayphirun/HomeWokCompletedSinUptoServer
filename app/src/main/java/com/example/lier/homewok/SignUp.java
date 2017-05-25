package com.example.lier.homewok;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUp extends AppCompatActivity {
   @BindView(R.id.btn_signup)
   Button btn_signup;
    @BindView(R.id.editTextName)
    EditText editTextName;
    @BindView(R.id.editTextEmail)
    EditText editTextEmail;
    @BindView(R.id.editTextPassword)
    EditText editTextPassword;
    User user=new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextName.getText().toString().equals("")||editTextEmail.getText().toString().equals("")||editTextPassword.getText().toString().equals("")){
                    Toast.makeText(SignUp.this, "Please input All field", Toast.LENGTH_SHORT).show();
                }else {
                    user=new User(editTextName.getText().toString(),editTextPassword.getText().toString(),editTextEmail.getText().toString());
                    Intent inten=new Intent(SignUp.this,Login.class);
                    inten.putExtra("USER",user);
                    setResult(Activity.RESULT_OK,inten);
                    finish();
                }
            }
        });
    }

    @Override
    public void finish() {
        Toast.makeText(SignUp.this, "CREATE SUCCESSFUL", Toast.LENGTH_SHORT).show();
        super.finish();
    }
}
