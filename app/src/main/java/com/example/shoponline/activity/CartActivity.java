package com.example.shoponline.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoponline.R;
import com.example.shoponline.apdapter.CartItemAdapter;
import com.example.shoponline.constant.Constant;
import com.example.shoponline.db.DBHelper;
import com.example.shoponline.model.Cart;
import com.example.shoponline.model.CartItem;
import com.example.shoponline.model.User;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CartActivity extends AppCompatActivity  {

    private Toolbar cartToolBar;
    private TextView cartTv;
    public static TextView totalPriceTv;
    private ListView cartListView;
    private Button checkOutBtn,continueBtn;
    CartItemAdapter cartItemAdapter;
    private DBHelper dbHelper=null;
    private SQLiteDatabase db=null;
    private DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        mapping();
        actionToolBar();
        checkCartItemList();
    }

    private void checkCartItemList() {
        if(MainActivity.cart.getCartItemList().isEmpty()){
            cartTv.setVisibility(View.VISIBLE);
            cartListView.setVisibility(View.INVISIBLE);

        }else{
            cartItemAdapter=new CartItemAdapter(this,R.layout.cart_item,MainActivity.cart.getCartItemList());
            cartListView.setAdapter(cartItemAdapter);
            cartTv.setVisibility(View.INVISIBLE);
            cartListView.setVisibility(View.VISIBLE);
            String totalPrice=decimalFormat.format(MainActivity.cart.getTotalprice());
            totalPriceTv.setText(totalPrice+" Đ");
        }
    }

    public void checkOut(View view){
        if(MainActivity.cart.getCartItemList().isEmpty()){
            Toast.makeText(this,"List your cart is empty",Toast.LENGTH_LONG).show();
            return;
        }

        AlertDialog alertDialog=null;
        AlertDialog.Builder builder=new AlertDialog.Builder(new ContextThemeWrapper(this,R.style.myDialog));
        builder.setTitle("Check Out!");
        builder.setMessage("Do you Want check out your cart?");
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbHelper=new DBHelper(getApplicationContext(),"product.db",null,1);
                db=dbHelper.getWritableDatabase();
                User user=MainActivity.user;
                String username=user.getUsername();
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
                String orderDate=simpleDateFormat.format(new Date());
                //insert Order
                db.execSQL(Constant.INSERT_ORDER,new Object[]{"Order "+username,username,MainActivity.cart.getTotalprice(),orderDate});
                //insert OrderLine
                Cursor cursor=db.rawQuery(Constant.SELECT_COUNT_ORDER,null);
                int orderId=0;
                while (cursor.moveToNext()){
                    orderId=cursor.getInt(cursor.getColumnIndex("count"));
                }

                List<CartItem> cartItemList=MainActivity.cart.getCartItemList();
                for(int i=0;i<cartItemList.size();i++){
                    db.execSQL(Constant.INSERT_ORDER_LINE,new Object[]{
                            orderId,
                            cartItemList.get(i).getProductId(),
                            cartItemList.get(i).getQuantity(),
                            cartItemList.get(i).getPriceProduct()
                    });
                }
                //update quantity for product
                for(int i=0;i<cartItemList.size();i++){
                    int newQuantity=cartItemList.get(i).getRealQuantity()-cartItemList.get(i).getQuantity();
                    int productId=cartItemList.get(i).getProductId();
                    db.execSQL(Constant.UPDATE_QUANTITY_PRODUCT,new Object[]{newQuantity,productId});
                }
                Toast.makeText(getApplicationContext(),"Check out successful!",Toast.LENGTH_LONG).show();
                finish();
                MainActivity.cart=new Cart();

            }});

            alertDialog=builder.create();
            alertDialog.show();

    }

    public void continueShopping(View view){
        finish();

    }

    private void actionToolBar() {
        setSupportActionBar(cartToolBar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cartToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void mapping() {
        cartToolBar=findViewById(R.id.cartToolBar);
        cartTv=findViewById(R.id.cartTv);
        totalPriceTv=findViewById(R.id.totalPriceTv);
        cartListView=findViewById(R.id.cartListView);
        checkOutBtn=findViewById(R.id.checkOutBtn);
        continueBtn=findViewById(R.id.continueBtn);
        registerForContextMenu(cartListView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu,menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.deleteCart){
            AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            int pos =info.position;
            MainActivity.cart.getCartItemList().remove(pos);
            cartItemAdapter.notifyDataSetChanged();
            String totalPrice=decimalFormat.format(MainActivity.cart.getTotalprice());
            totalPriceTv.setText(totalPrice+" Đ");
            if(MainActivity.cart.getCartItemList().isEmpty()){
                cartTv.setVisibility(View.VISIBLE);
                cartListView.setVisibility(View.INVISIBLE);
                totalPriceTv.setText("");
            }

        }
        return super.onContextItemSelected(item);
    }
}
