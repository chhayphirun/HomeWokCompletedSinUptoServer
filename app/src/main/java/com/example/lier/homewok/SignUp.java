package com.example.lier.homewok;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

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
    UserOld user=new UserOld();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        SharedPreferences sharedPreferences=getSharedPreferences(Constant.USER_PREFERENCE,MODE_PRIVATE);
        final SharedPreferences.Editor editor=sharedPreferences.edit();
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user1;
                if(!editTextName.getText().toString().equals("")&&!editTextPassword.getText().toString().equals("")){
                    user1=new User(editTextName.getText().toString(),editTextPassword.getText().toString());
                  /*  List<User> userList = new ArrayList<User>();
                    userList.add(user1);*/
                    String userToJson = new Gson().toJson(user1);
                    editor.putString(Constant.USER, userToJson);
                    editor.putString(Constant.USER_PASSWORD,userToJson);
                    editor.apply();
                    startActivity(new Intent(SignUp.this,Login.class));

                }

                /*if(editTextName.getText().toString().equals("")||editTextEmail.getText().toString().equals("")||editTextPassword.getText().toString().equals("")){
                    Toast.makeText(SignUp.this, "Please input All field", Toast.LENGTH_SHORT).show();
                }else {
                    user=new UserOld(editTextName.getText().toString(),editTextPassword.getText().toString(),editTextEmail.getText().toString());
                    Intent inten=new Intent(SignUp.this,Login.class);
                    inten.putExtra("USER",user);
                    setResult(Activity.RESULT_OK,inten);
                    Toast.makeText(SignUp.this, "CREATE SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    finish();
                }*/
            }
        });
    }

    @Override
    public void finish() {

        super.finish();
    }
}
