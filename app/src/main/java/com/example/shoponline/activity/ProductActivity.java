package com.example.shoponline.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shoponline.R;
import com.example.shoponline.apdapter.ProductApdater;
import com.example.shoponline.constant.Constant;
import com.example.shoponline.db.DBHelper;
import com.example.shoponline.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {

    private ProductApdater productApdater;
    private Toolbar productToolBar;
    private ListView productListView;
    private DBHelper dbHelper=null;
    private SQLiteDatabase db=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        mapping();
        actionToolBar();
        dbHelper=new DBHelper(this,"product.db",null,1);

    }

    private void mapping(){
        productToolBar=findViewById(R.id.productToolBar);
        productListView=findViewById(R.id.productListView);
        productListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),DetailProductActivity.class);
                Product product= (Product) productApdater.getItem(position);
                intent.putExtra("product",product);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(dbHelper!=null){
            Intent intent=getIntent();
            int type=intent.getIntExtra("type",1);
            db=dbHelper.getReadableDatabase();
            Cursor cursor=db.rawQuery(Constant.SELECT_PRODUCT_BY_TYPE_PRODUCT,new String[]{String.valueOf(type)});
            List<Product> productList=new ArrayList<>();
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

            if(type==1){
                productToolBar.setTitle("Laptop");
            }else if(type==2){
                productToolBar.setTitle("SmartPhone");
            }else{
                productToolBar.setTitle("Watch");
            }
            productApdater=new ProductApdater(this,R.layout.product,productList);
            productListView.setAdapter(productApdater);
        }
    }

    private void actionToolBar(){
        setSupportActionBar(productToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        productToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
