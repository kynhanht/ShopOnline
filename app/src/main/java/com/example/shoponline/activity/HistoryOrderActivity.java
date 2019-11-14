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
import com.example.shoponline.apdapter.OrderAdapter;
import com.example.shoponline.constant.Constant;
import com.example.shoponline.db.DBHelper;
import com.example.shoponline.model.Order;
import com.example.shoponline.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryOrderActivity extends AppCompatActivity {

    private Toolbar historyOrderToolBar;
    private TextView historyOrderTv;
    private ListView historyOrderListView;
    private OrderAdapter orderAdapter;
    private DBHelper dbHelper=null;
    private SQLiteDatabase db=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_order);
        mapping();
        actionToolBar();

    }

    private void mapping() {
        historyOrderToolBar=findViewById(R.id.historyOrderToolBar);
        historyOrderTv=findViewById(R.id.historyOrderTv);
        historyOrderListView=findViewById(R.id.historyOrderListView);
        dbHelper=new DBHelper(getApplicationContext(),"product.db",null,1);
        db=dbHelper.getReadableDatabase();
        User user=MainActivity.user;
        Cursor cursor=db.rawQuery(Constant.SELECT_ORDER_BY_USER_NAME,new String[]{user.getUsername()});
        List<Order> orderList=new ArrayList<>();
        while (cursor.moveToNext()){
            Order order=new Order();
            order.setOrderId(cursor.getInt(cursor.getColumnIndex("orderId")));
            order.setOrderName(cursor.getString(cursor.getColumnIndex("orderName")));
            order.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            order.setPayment(cursor.getLong(cursor.getColumnIndex("payment")));
            order.setOrderDate(cursor.getString(cursor.getColumnIndex("orderDate")));
            orderList.add(order);

        }

        if(orderList.isEmpty()){
            historyOrderTv.setVisibility(View.VISIBLE);
            historyOrderListView.setVisibility(View.INVISIBLE);
            return;
        }

        historyOrderTv.setVisibility(View.INVISIBLE);
        historyOrderListView.setVisibility(View.VISIBLE);
        orderAdapter=new OrderAdapter(this,R.layout.history_oder_list_view,orderList);
        historyOrderListView.setAdapter(orderAdapter);


    }
    private void actionToolBar(){
        setSupportActionBar(historyOrderToolBar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        historyOrderToolBar.setNavigationOnClickListener(new View.OnClickListener() {
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
