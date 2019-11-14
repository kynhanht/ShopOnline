package com.example.shoponline.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.shoponline.R;
import com.example.shoponline.apdapter.NewProductAdapter;
import com.example.shoponline.apdapter.TypeProductAdapter;
import com.example.shoponline.constant.Constant;
import com.example.shoponline.db.DBHelper;
import com.example.shoponline.model.Cart;
import com.example.shoponline.model.CartItem;
import com.example.shoponline.model.Product;
import com.example.shoponline.model.SpacesItemDecoration;
import com.example.shoponline.model.TypeProduct;
import com.example.shoponline.model.User;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewFlipper viewFlipper;
    private RecyclerView recyclerView;
    private NavigationView navigationView;
    private ListView listViewNav;
    private DrawerLayout drawerLayout;
    private TypeProductAdapter typeProductAdapter;
    private NewProductAdapter newProductAdapter;
    public static Cart cart;
    public static User user;
    private DBHelper dbHelper=null;
    private SQLiteDatabase db=null;
    //user
    private ImageView userImageView;
    private TextView nameUserTv,emailUserTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        actionBar();
        actionViewFlipper();
        addUser();
        dbHelper=new DBHelper(this,"product.db",null,1);


    }

    //get information for header navbar
    private void addUser() {
        if(user==null){
            user= (User) getIntent().getSerializableExtra("user");
        }
        nameUserTv.setText(user.getName());
        emailUserTv.setText(user.getEmail());
    }

    //mapping all view of layout
    private void mapping(){
        toolbar=findViewById(R.id.toolBar);
        viewFlipper=findViewById(R.id.viewFlipper);
        recyclerView=findViewById(R.id.recyclerView);
        navigationView=findViewById(R.id.navigationView);
        drawerLayout=findViewById(R.id.drawerLayout);
        userImageView=findViewById(R.id.userImageView);
        recyclerView.addItemDecoration(new SpacesItemDecoration(20));

        //mapping view  about information of user
        View headerLayout=navigationView.getHeaderView(0);
        nameUserTv=headerLayout.findViewById(R.id.nameUserTv);
        emailUserTv=headerLayout.findViewById(R.id.emailUserTv);
        //set action navigation
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //catch event for every item navigation
                if(menuItem.getItemId()==R.id.homeItem){//click home
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else if(menuItem.getItemId()==R.id.lapTopItem){// click laptop item
                    Intent intent=new Intent(getApplicationContext(),ProductActivity.class);
                    intent.putExtra("type",1);
                    startActivity(intent);
                }else if(menuItem.getItemId()==R.id.smartPhoneItem){// click SmartPhone item
                    Intent intent=new Intent(getApplicationContext(),ProductActivity.class);
                    intent.putExtra("type",2);
                    startActivity(intent);
                }else if(menuItem.getItemId()==R.id.watchItem){// click watch item
                    Intent intent=new Intent(getApplicationContext(),ProductActivity.class);
                    intent.putExtra("type",3);
                    startActivity(intent);
                }else if(menuItem.getItemId()==R.id.myProfileItem){// click profile item
                    Intent intent=new Intent(getApplicationContext(),ProfileUserActivity.class);
                    startActivity(intent);
                }else if(menuItem.getItemId()==R.id.myOrderItem){// click my order
                    Intent intent=new Intent(getApplicationContext(),HistoryOrderActivity.class);
                    startActivity(intent);
                }else if(menuItem.getItemId()==R.id.aboutUsItem){//click about information
                    Intent intent=new Intent(getApplicationContext(),AboutUsActivity.class);
                    startActivity(intent);
                }else{//logout
                    cart=null;
                    user=null;
                    Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                //close navigation
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        if(cart!=null){

        }else{// when first init main
            cart=new Cart();
        }
    }

    private void actionBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }


    private void actionViewFlipper() {
        //add link image for Flipper
//        List<String> listFlipper=new ArrayList<>();
//        listFlipper.add("https://hoanghamobile.com/tin-tuc/wp-content/uploads/2017/06/j7-pro.png");
//        listFlipper.add("https://www.sapo.vn/blog/wp-content/uploads/2014/10/banner-quang-cao-du-khach-hang-hieu-qua-3.jpg");
//        listFlipper.add("http://www.chupsanpham.vn/uploads/2/1/6/8/21683184/3568618_orig.jpg");
        // convert item of Flipper into ImageView

//        for(int i=0;i<listFlipper.size();i++){
//            ImageView imageView=new ImageView(getApplicationContext());
//            Picasso.with(getApplicationContext()).load(listFlipper.get(i)).into(imageView);
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            viewFlipper.addView(imageView);
//        }
        ImageView imageView=new ImageView(getApplicationContext());
        imageView.setImageResource(R.drawable.banner);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        viewFlipper.addView(imageView);
        //set animation for viewFlipper
        Animation animationSlideIn= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animationSlideOut= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animationSlideIn);
        viewFlipper.setOutAnimation(animationSlideOut);
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
    }



    //get new product when init or resume
    @Override
    protected void onResume() {
        super.onResume();
        if(dbHelper!=null){
            db=dbHelper.getReadableDatabase();
            List<Product> productList=new ArrayList<>();
            Cursor cursor=db.rawQuery(Constant.SELECT_NEW_PRODUCT,null);
            while(cursor.moveToNext()){
                Product product=new Product();
                product.setIdProduct(cursor.getInt(cursor.getColumnIndex("productId")));
                product.setNameProduct(cursor.getString(cursor.getColumnIndex("productName")));
                product.setPrice(cursor.getLong(cursor.getColumnIndex("productPrice")));
                product.setImageProduct(cursor.getInt(cursor.getColumnIndex("productImage")));
                product.setDescriptionProduct(cursor.getString(cursor.getColumnIndex("productDescription")));
                product.setIdTypeProduct(cursor.getInt(cursor.getColumnIndex("typeProductId")));
                product.setQuantity(cursor.getInt(cursor.getColumnIndex("quantity")));
                productList.add(product);
            }
            newProductAdapter=new NewProductAdapter(this,R.layout.new_product,productList);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));
            recyclerView.setAdapter(newProductAdapter);
        }

    }
    // set option menu for cart item
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
