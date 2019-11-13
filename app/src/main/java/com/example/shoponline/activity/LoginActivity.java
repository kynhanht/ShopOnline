package com.example.shoponline.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shoponline.R;
import com.example.shoponline.constant.Constant;
import com.example.shoponline.db.DBHelper;
import com.example.shoponline.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEt,passwordEt;
    private TextView errorLoginEt,registerTv;
    private Button loginBtn;
    private DBHelper dbHelper=null;
    private SQLiteDatabase db=null;
    private List<User> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mapping();
        getAllUser();
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<userList.size();i++){
                    String username=usernameEt.getText().toString();
                    String password=passwordEt.getText().toString();
                    if(username.equals(userList.get(i).getUsername()) && password.equals(userList.get(i).getPassword())){
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        errorLoginEt.setVisibility(View.INVISIBLE);
                        intent.putExtra("user",userList.get(i));
                        startActivity(intent);
                        finish();
                        return;
                    }
                }
                errorLoginEt.setVisibility(View.VISIBLE);

            }
        });
        registerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });


    }

    private void getAllUser() {
        dbHelper = new DBHelper(this, "product.db", null, 1);
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

    private void mapping() {
        usernameEt=findViewById(R.id.usernameEt);
        passwordEt=findViewById(R.id.passwordEt);
        errorLoginEt=findViewById(R.id.errorLoginTv);
        registerTv=findViewById(R.id.registerTv);
        errorLoginEt.setVisibility(View.INVISIBLE);
        loginBtn=findViewById(R.id.loginBtn);
    }
}
