package com.example.shoponline.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shoponline.R;
import com.example.shoponline.apdapter.OrderDetailAdapter;
import com.example.shoponline.constant.Constant;
import com.example.shoponline.db.DBHelper;
import com.example.shoponline.model.Cart;
import com.example.shoponline.model.CartItem;
import com.example.shoponline.model.Order;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {
    private Toolbar orderDetailToolBar;
    private TextView orderDetailNumberTv,orderDetailTotalTv;
    private ListView orderDetailListView;
    private OrderDetailAdapter orderDetailAdapter;
    private DBHelper dbHelper=null;
    private SQLiteDatabase db=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        mapping();
        actionToolBar();

    }

    private void actionToolBar() {
        setSupportActionBar(orderDetailToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        orderDetailToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void mapping() {
        orderDetailToolBar=findViewById(R.id.orderDetailToolBar);
        orderDetailNumberTv=findViewById(R.id.orderDetailNumberTv);
        orderDetailTotalTv=findViewById(R.id.orderDetailTotalTv);
        orderDetailListView=findViewById(R.id.orderDetailListView);
        List<CartItem> cartItemList=new ArrayList<>();
        dbHelper=new DBHelper(getApplicationContext(),"product.db",null,1);
        db=dbHelper.getReadableDatabase();
        Order order= (Order) getIntent().getSerializableExtra("order");
        int orderId=order.getOrderId();
        Cursor cursor=db.rawQuery(Constant.SELECT_CART_ITEM,new String[]{String.valueOf(orderId)});
        while(cursor.moveToNext()){
            CartItem cartItem=new CartItem();
            cartItem.setProductId(cursor.getInt(cursor.getColumnIndex("productId")));
            cartItem.setNameProduct(cursor.getString(cursor.getColumnIndex("productName")));
            cartItem.setPriceProduct(cursor.getLong(cursor.getColumnIndex("unitPrice")));
            cartItem.setImageProduct(cursor.getInt(cursor.getColumnIndex("productImage")));
            cartItem.setQuantity(cursor.getInt(cursor.getColumnIndex("quantity")));
            cartItemList.add(cartItem);
        }

        orderDetailAdapter=new OrderDetailAdapter(this,R.layout.order_detail_list_view,cartItemList);
        orderDetailListView.setAdapter(orderDetailAdapter);

        Cart cart=new Cart();
        cart.setCartItemList(cartItemList);
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        orderDetailTotalTv.setText("Price: "+decimalFormat.format(cart.getTotalprice())+" Đ");
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
