package com.example.shoponline.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shoponline.R;
import com.example.shoponline.constant.Constant;
import com.example.shoponline.db.DBHelper;
import com.example.shoponline.model.User;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private TextView usernameRegisterEt,passwordRegisterEt,emailRegisterEt,phoneRegisterEt,nameRegisterEt;
    private Button registerBtn;
    private Toolbar registerToolbar;
    private DBHelper dbHelper=null;
    private SQLiteDatabase db=null;
    private List<User> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dbHelper = new DBHelper(this, "product.db", null, 1);
        mapping();
        actionToolBar();
    }

    private void actionToolBar() {
        setSupportActionBar(registerToolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        registerToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void mapping() {
        usernameRegisterEt=findViewById(R.id.usernameRegisterEt);
        passwordRegisterEt=findViewById(R.id.passwordRegisterEt);
        emailRegisterEt=findViewById(R.id.emailRegisterEt);
        phoneRegisterEt=findViewById(R.id.phoneRegisterEt);
        nameRegisterEt=findViewById(R.id.nameRegisterEt);
        registerBtn=findViewById(R.id.registerBtn);
        registerToolbar=findViewById(R.id.registerToolbar);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usernameRegisterEt.setError(null);
                passwordRegisterEt.setError(null);
                emailRegisterEt.setError(null);
                phoneRegisterEt.setError(null);
                nameRegisterEt.setError(null);

                String username=usernameRegisterEt.getText().toString();
                String password=passwordRegisterEt.getText().toString();
                String email=emailRegisterEt.getText().toString();
                String phone=phoneRegisterEt.getText().toString();
                String name=nameRegisterEt.getText().toString();
                if(!username.isEmpty()&&!password.isEmpty()&&!email.isEmpty()&&!phone.isEmpty()&&!name.isEmpty()){
                    boolean check=true;
                    if(!username.matches("^[a-zA-Z0-9._-]{3,100}$")){
                        usernameRegisterEt.setError("Username is invalid!");
                        check=false;
                    }
                    if(!password.matches("^[a-zA-Z0-9._-]{3,100}$")){
                        passwordRegisterEt.setError("Password is invalid");
                        check=false;

                    }
                    if(!email.matches("^(.+)@(.+)$")){
                        emailRegisterEt.setError("Email is invalid");
                        check=false;
                    }
                    if(!phone.matches("\\d{8,10}")){
                        phoneRegisterEt.setError("Phone is invalid");
                        check=false;
                    }
                    if(!name.matches("[^0-9]{6,100}")){
                        nameRegisterEt.setError("Name is invalid");
                        check=false;
                    }
                    //
                    if(!check){
                        return;
                    }else{

                        for(int i=0;i<userList.size();i++){
                            if(username.equals(userList.get(i).getUsername())){
                                usernameRegisterEt.setError("Username already exists!");
                                passwordRegisterEt.setError(null);
                                emailRegisterEt.setError(null);
                                phoneRegisterEt.setError(null);
                                nameRegisterEt.setError(null);
                                return;
                            }
                        }
                        //insert user
                        db = dbHelper.getWritableDatabase();
                        db.execSQL(Constant.INSERT_USER,new Object[]{
                                username,password,email,phone,name
                        });
//                        User user=new User(username,password,email,phone,name);
//                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
//                        intent.putExtra("user",user);
//                        startActivity(intent);
                        finish();
                    }
                }else{
                    if(username.isEmpty()){
                        usernameRegisterEt.setError("Username is required!");
                    }
                    if(password.isEmpty()){
                        passwordRegisterEt.setError("password is required!");
                    }
                    if(email.isEmpty()){
                        emailRegisterEt.setError("Email is required!");
                    }
                    if(phone.isEmpty()){
                        phoneRegisterEt.setError("Phone is required!");
                    }
                    if(name.isEmpty()){
                        nameRegisterEt.setError("Name is required!");
                    }
                    return;
                }


            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(dbHelper!=null){
            db = dbHelper.getReadableDatabase();
            userList = new ArrayList<>();
            Cursor cursor = db.rawQuery(Constant.SELECT_ALL_USER, null);
            while(cursor.moveToNext()){
                User user=new User();
                user.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                user.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                user.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
                user.setName(cursor.getString(cursor.getColumnIndex("name")));
                userList.add(user);

            }
        }
    }
}
