package com.example.shoponline.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.shoponline.R;
import com.example.shoponline.model.CartItem;
import com.example.shoponline.model.Product;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class DetailProductActivity extends AppCompatActivity {

    private Toolbar detailToolBar;
    private ImageView detailProductImageView;
    private TextView detailNameProductTv,detailPriceProductTv,detailDescriptionTv;
    private Spinner quantitySpinner;
    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        mapping();
        actionToolBar();
        getProduct();
    }

    public void mapping(){
        detailToolBar=findViewById(R.id.detailProductToolBar);
        detailProductImageView=findViewById(R.id.detaiProductImageView);
        detailNameProductTv=findViewById(R.id.detailNameProductTv);
        detailPriceProductTv=findViewById(R.id.detailPriceProductTv);
        detailDescriptionTv=findViewById(R.id.detailDescriptionTv);
        quantitySpinner=findViewById(R.id.quantitySpinner);
        addBtn=findViewById(R.id.addBtn);

        Integer[] quantity=new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,quantity);
        quantitySpinner.setAdapter(arrayAdapter);


    }
    public void actionToolBar(){
        setSupportActionBar(detailToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        detailToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void getProduct(){

        Product product= (Product) getIntent().getSerializableExtra("product");
        int idProduct=product.getIdProduct();
        String nameProduct=product.getNameProduct();
        long price=product.getPrice();
        int imageProduct=product.getImageProduct();
        String descriptionProduct=product.getDescriptionProduct();
        int idTypeProduct=product.getIdTypeProduct();
        int quantity=product.getQuantity();
        detailProductImageView.setImageResource(imageProduct);
        detailNameProductTv.setText(nameProduct);
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        detailPriceProductTv.setText("Price: "+decimalFormat.format(price)+" Đ");
        detailDescriptionTv.setText(descriptionProduct);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity=Integer.parseInt(quantitySpinner.getSelectedItem().toString());
                CartItem cartItem=new CartItem();
                cartItem.setProductId(product.getIdProduct());
                cartItem.setNameProduct(product.getNameProduct());
                cartItem.setImageProduct(product.getImageProduct());
                cartItem.setPriceProduct(product.getPrice());
                cartItem.setQuantity(quantity);
                MainActivity.cart.addCartItem(cartItem);
                Intent intent=new Intent(getApplicationContext(),CartActivity.class);
                startActivity(intent);
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
