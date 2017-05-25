package com.example.lier.homewok;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Login extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    @BindView(R.id.btnlogin)
    Button btnlogin;
    @BindView(R.id.editText)
    EditText name;
    @BindView(R.id.editText2)
    EditText password;
    @BindView(R.id.btnsignup)
            Button btnsignup;
    final List<User> userList=new ArrayList<>();


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
                boolean status=false;
                for (User user:userList){
                    if(name.getText().toString().equals(user.getUsername())&&password.getText().toString().equals(user.getPassword())){
                        status=true;
                        break;
                    }
                }
                if(status==false){
                    Toast.makeText(Login.this,"Username and Password mismatch", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Login.this, "SIGNIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,SignUp.class);
                startActivityForResult(intent,REQUEST_CODE);
//                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle bundle=data.getExtras();
        if(requestCode==REQUEST_CODE){
            if(resultCode== Activity.RESULT_OK){
                User user1=new User();
                user1=bundle.getParcelable("USER");
            userList.add(user1);
                for(User u:userList){
                   Log.e("ooooo",u.getUsername());
                }

            }
            
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
