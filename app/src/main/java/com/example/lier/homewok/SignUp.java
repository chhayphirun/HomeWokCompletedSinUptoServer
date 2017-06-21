package com.example.lier.homewok;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lier.homewok.util.RetrofitUtil;
import com.example.lier.homewok.webservice.ImageService;
import com.example.lier.homewok.webservice.UserService;
import com.google.gson.JsonObject;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dmax.dialog.SpotsDialog;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private static final int OPEN_GALLERY = 1;
    private static final int READ_EXTERNAL_STORAGE_CODE = 1;
    private Unbinder unbinder;
    private String imagePath;
    private ImageService imageService;
    private SpotsDialog dialog;
    private UserService userService;
    @BindView(R.id.imagebrowse)
    ImageView imagebrowse;

    @OnClick(R.id.imagebrowse)
    void onButtonBrowseClicked() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int checkedPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
            if (checkedPermission == PackageManager.PERMISSION_DENIED) {
                requestPermissions(permissions, READ_EXTERNAL_STORAGE_CODE);
            } else {
                showGallery();
            }
        } else {
            showGallery();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == READ_EXTERNAL_STORAGE_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showGallery();
            } else {
                Toast.makeText(this, "Please Grant Permisson", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri selectedImage = data.getData();
        String[] pathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(selectedImage, pathColumn, null, null, null);
        if (cursor.moveToFirst()){
            int columnIndex = cursor.getColumnIndex(pathColumn[0]);
            imagePath = cursor.getString(columnIndex);
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            imagebrowse.setImageBitmap(bitmap);
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    void showGallery() {
        Intent galleryIntent = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, OPEN_GALLERY);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        unbinder = ButterKnife.bind(this);
        imageService = ServiceGenerator.createService(ImageService.class);
        userService=ServiceGenerator.createService(UserService.class);
        dialog = new SpotsDialog(this, "Please wait...");





    SharedPreferences sharedPreferences=getSharedPreferences(Constant.USER_PREFERENCE,MODE_PRIVATE);
        final SharedPreferences.Editor editor=sharedPreferences.edit();

        /*btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user1;
                if(!editTextName.getText().toString().equals("")&&!editTextPassword.getText().toString().equals("")){
                    RequestBody name=RetrofitUtil.toRequestBody(editTextName.getText().toString());
                    RequestBody password=RetrofitUtil.toRequestBody(editTextPassword.getText().toString());
                    RequestBody gender=RetrofitUtil.toRequestBody("M");
                    RequestBody facebook=RetrofitUtil.toRequestBody(editTextEmail.getText().toString());
                    RequestBody telephone=RetrofitUtil.toRequestBody("012 222 222");
                    user1=new User(editTextName.getText().toString(),editTextPassword.getText().toString());
                  *//*  List<User> userList = new ArrayList<User>();
                    userList.add(user1);*//*
                    String userToJson = new Gson().toJson(user1);
                    editor.putString(Constant.USER, userToJson);
                    editor.putString(Constant.USER_PASSWORD,userToJson);
                    editor.apply();
//                    startActivity(new Intent(SignUp.this,Login.class));

                }

                *//*if(editTextName.getText().toString().equals("")||editTextEmail.getText().toString().equals("")||editTextPassword.getText().toString().equals("")){
                    Toast.makeText(SignUp.this, "Please input All field", Toast.LENGTH_SHORT).show();
                }else {
                    user=new UserOld(editTextName.getText().toString(),editTextPassword.getText().toString(),editTextEmail.getText().toString());
                    Intent inten=new Intent(SignUp.this,Login.class);
                    inten.putExtra("USER",user);
                    setResult(Activity.RESULT_OK,inten);
                    Toast.makeText(SignUp.this, "CREATE SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    finish();
                }*//*
            }
        });*/
    }
    @OnClick(R.id.btn_signup)
    void onSingUpClicked(){
        // Email Filed
        ButterKnife.bind(this);
        RequestBody name= RetrofitUtil.toRequestBody(editTextName.getText().toString());
        RequestBody password=RetrofitUtil.toRequestBody(editTextPassword.getText().toString());
        RequestBody gender=RetrofitUtil.toRequestBody("M");
        RequestBody facebook=RetrofitUtil.toRequestBody("phirun_chhay@yahoo.com");
        RequestBody telephone=RetrofitUtil.toRequestBody("012 222 222");
        RequestBody email=RetrofitUtil.toRequestBody("phirun_chhay@yahoo.com");


        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        Log.e("oooooo",file.getPath());
        MultipartBody.Part body = MultipartBody.Part.createFormData("PHOTO", file.getName(), requestBody);
//        Call<JsonObject> uploadImage = imageService.uploadSingleImage(body);
        Log.e("Email :",email.toString());
        Call<JsonObject> uploadUser=userService.createUser(email,name,password,gender,telephone,facebook,body);
//        dialog.show();
//        uploadImage.enqueue(new Callback<JsonObject>() {
        uploadUser.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()){
                    dialog.dismiss();
                    Log.e("ooooo",response.body().toString());
                    Toast.makeText(SignUp.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                dialog.dismiss();
                Toast.makeText(SignUp.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
    }

}
