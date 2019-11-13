package com.example.shoponline.apdapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.shoponline.R;
import com.example.shoponline.activity.OrderDetailActivity;
import com.example.shoponline.model.Order;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class OrderAdapter extends BaseAdapter {
    private Activity activity;
    private int layout;
    private List<Order> orderList;

    public OrderAdapter(Activity activity, int layout, List<Order> orderList) {
        this.activity = activity;
        this.layout = layout;
        this.orderList = orderList;
    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView historyOrderDateTv,orderIdTv,paymentTv;
        Button viewDetailBtn;
        if(convertView==null){
            convertView=activity.getLayoutInflater().inflate(layout,null);
            historyOrderDateTv=convertView.findViewById(R.id.historyOrderDateTv);
            orderIdTv=convertView.findViewById(R.id.orderIdTv);
            paymentTv=convertView.findViewById(R.id.paymentTv);
            viewDetailBtn=convertView.findViewById(R.id.viewDetailBtn);

            convertView.setTag(R.id.historyOrderDateTv,historyOrderDateTv);
            convertView.setTag(R.id.orderIdTv,orderIdTv);
            convertView.setTag(R.id.paymentTv,paymentTv);
            convertView.setTag(R.id.viewDetailBtn,viewDetailBtn);
        }else{
            historyOrderDateTv= (TextView) convertView.getTag(R.id.historyOrderDateTv);
            orderIdTv= (TextView) convertView.getTag(R.id.orderIdTv);
            paymentTv= (TextView) convertView.getTag(R.id.paymentTv);
            viewDetailBtn= (Button) convertView.getTag(R.id.viewDetailBtn);
        }
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
        ParsePosition parsePosition = new ParsePosition(0);
        Date date=simpleDateFormat.parse(orderList.get(position).getOrderDate(),parsePosition);
        simpleDateFormat=new SimpleDateFormat("dd MMM, yyyy");
        historyOrderDateTv.setText(simpleDateFormat.format(date));
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        orderIdTv.setText("Order num: #"+String.valueOf(orderList.get(position).getOrderId()));
        paymentTv.setText(decimalFormat.format(orderList.get(position).getPayment())+" Đ");
        viewDetailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity, OrderDetailActivity.class);
                intent.putExtra("order",orderList.get(position));
                activity.startActivity(intent);
            }
        });

        return convertView;
    }
}
