package com.example.shoponline.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoponline.R;
import com.example.shoponline.model.User;

public class ProfileUserActivity extends AppCompatActivity {

    private Toolbar profileToolBar;
    private ImageView profileImageView;
    private TextView profileNameTv,profileUsernameTv,profileEmailTv,profilePhoneTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);
        mapping();
        setActionBar();
        addInfomationUser();

    }

    private void addInfomationUser() {
        User user=MainActivity.user;
        profileNameTv.setText(user.getName());
        profileUsernameTv.setText(user.getUsername());
        profileEmailTv.setText(user.getEmail());
        profilePhoneTv.setText(user.getPhone());
    }

    private void setActionBar() {
        setSupportActionBar(profileToolBar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        profileToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void mapping() {
        profileToolBar=findViewById(R.id.profileToolBar);
        profileImageView=findViewById(R.id.profileImageView);
        profileNameTv=findViewById(R.id.profileNameTv);
        profileUsernameTv=findViewById(R.id.profileUsernameTv);
        profileEmailTv=findViewById(R.id.profileEmailTv);
        profilePhoneTv=findViewById(R.id.profilePhoneTv);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.cartMenu){
            Intent intent=new Intent(this,CartActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
