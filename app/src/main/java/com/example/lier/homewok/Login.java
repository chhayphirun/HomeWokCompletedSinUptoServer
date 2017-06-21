package com.example.lier.homewok;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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
    final List<UserOld> userList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences sharedPreferences=getSharedPreferences(Constant.USER_PREFERENCE,MODE_PRIVATE);
        final SharedPreferences.Editor editor=sharedPreferences.edit();
        String userpasswor=sharedPreferences.getString(Constant.USER_PASSWORD,"N/A");
        String userName = sharedPreferences.getString(Constant.USER_NAME, "N/A");
        String userFromJson = sharedPreferences.getString(Constant.USER, "N/A");
//        String userListFromJson = sharedPreferences.getString(Constant.USER_LIST, "N/A");
        User user;
        /*if (!userFromJson.equals("N/A")){
            user = new Gson().fromJson(userFromJson, User.class);
            Toast.makeText(this,"Name :"+user.getName()+" ,Password : "+userpasswor, Toast.LENGTH_SHORT).show();
            Log.e("ooooo",userpasswor);
            if(!user.getName().equals("")&&!user.getPassword().equals("")){
                startActivity(new Intent(this,HomeActivity.class));

            }
        }
*/


        /*findViewById(R.id.btnlogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user1;
                if(!name.getText().toString().equals("")&&!password.getText().toString().equals("")){
                    user1=new User(name.getText().toString(),password.getText().toString());
                    List<User> userList = new ArrayList<User>();
                    userList.add(user1);
                    String userToJson = new Gson().toJson(user1);
                    editor.putString(Constant.USER, userToJson);
                    editor.apply();

                }
            }
        });
*/

        /*ButterKnife.bind(this);
        userList.add(new UserOld("dara","1234"));
        userList.add(new UserOld("daro","1234"));
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean status=false;
                for (UserOld user:userList){
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
        });*/

        findViewById(R.id.btnsignup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,SignUp.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_CODE){
            if(resultCode== Activity.RESULT_OK){
                Bundle bundle=data.getExtras();
                UserOld user1=new UserOld();
                user1=bundle.getParcelable("USER");
            userList.add(user1);
                for(UserOld u:userList){
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
