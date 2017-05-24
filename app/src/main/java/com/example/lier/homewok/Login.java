package com.example.lier.homewok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Login extends AppCompatActivity {
    @BindView(R.id.btnlogin)
    Button btnlogin;
    @BindView(R.id.editText)
    EditText name;
    @BindView(R.id.editText2)
    EditText password;
    List<User> userList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        userList.add(new User("dara","1234"));
        userList.add(new User("daro","1234"));
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (User user:userList){
                    //Toast.makeText(Login.this,"listpassword"+user.getPassword(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(Login.this,name.getText().toString(), Toast.LENGTH_SHORT).show();
                    if(name.getText().toString().equals(user.getUsername())&&password.getText().toString().equals(user.getPassword())){
                        Intent intent=new Intent(Login.this,SignUp.class);
                        startActivity(intent);
                        break;
                    }
                }


            }
        });
    }
}
