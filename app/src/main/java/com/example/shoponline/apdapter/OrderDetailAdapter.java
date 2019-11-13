package com.example.shoponline.apdapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoponline.R;
import com.example.shoponline.model.CartItem;

import java.text.DecimalFormat;
import java.util.List;

public class OrderDetailAdapter extends BaseAdapter {
    private Activity activity;
    private int layout;
    private List<CartItem> cartItemList;

    public OrderDetailAdapter(Activity activity, int layout, List<CartItem> cartItemList) {
        this.activity = activity;
        this.layout = layout;
        this.cartItemList = cartItemList;
    }

    @Override
    public int getCount() {
        return cartItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return cartItemList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView orderDetailImageView;
        TextView nameOrderDetailTv,priceOrderDetailTv,quantityOrderDetailTv;
        if(convertView==null){
            convertView=activity.getLayoutInflater().inflate(layout,null);
            orderDetailImageView=convertView.findViewById(R.id.orderDetailImageView);
            nameOrderDetailTv=convertView.findViewById(R.id.nameOrderDetailTv);
            priceOrderDetailTv=convertView.findViewById(R.id.priceOrderDetailTv);
            quantityOrderDetailTv=convertView.findViewById(R.id.quantityOrderDetailTv);

            convertView.setTag(R.id.orderDetailImageView,orderDetailImageView);
            convertView.setTag(R.id.nameOrderDetailTv,nameOrderDetailTv);
            convertView.setTag(R.id.priceOrderDetailTv,priceOrderDetailTv);
            convertView.setTag(R.id.quantityOrderDetailTv,quantityOrderDetailTv);

        }else{
            orderDetailImageView= (ImageView) convertView.getTag(R.id.orderDetailImageView);
            nameOrderDetailTv= (TextView) convertView.getTag(R.id.nameOrderDetailTv);
            priceOrderDetailTv= (TextView) convertView.getTag(R.id.priceOrderDetailTv);
            quantityOrderDetailTv= (TextView) convertView.getTag(R.id.quantityOrderDetailTv);
        }
        CartItem cartItem=cartItemList.get(position);
        orderDetailImageView.setImageResource(cartItem.getImageProduct());
        nameOrderDetailTv.setText(cartItem.getNameProduct());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        priceOrderDetailTv.setText("Price: "+decimalFormat.format(cartItem.getPriceProduct())+ " Đ");
        quantityOrderDetailTv.setText("Quantity: "+cartItem.getQuantity());
        return convertView;
    }
}
