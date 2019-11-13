package com.example.shoponline.apdapter;

import android.app.Activity;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoponline.R;
import com.example.shoponline.activity.CartActivity;
import com.example.shoponline.activity.MainActivity;
import com.example.shoponline.model.CartItem;

import java.text.DecimalFormat;
import java.util.List;

public class CartItemAdapter extends BaseAdapter {
    private Activity activity;
    private int layout;
    private List<CartItem> cartItemList;

    public CartItemAdapter(Activity activity, int layout, List<CartItem> cartItemList) {
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
        return cartItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView cartImageView;
        TextView nameCartItemTv,priceCartItemTv;
        Button cartMinusBtn,cartValueBtn,cartAddBtn;
        if(convertView==null){
            convertView=activity.getLayoutInflater().inflate(layout,null);
            cartImageView=convertView.findViewById(R.id.cartImageView);
            nameCartItemTv=convertView.findViewById(R.id.nameCartItemTv);
            priceCartItemTv=convertView.findViewById(R.id.priceCartItemTv);
            cartMinusBtn=convertView.findViewById(R.id.cartMinusBtn);
            cartValueBtn=convertView.findViewById(R.id.cartValueBtn);
            cartAddBtn=convertView.findViewById(R.id.cartAddBtn);

            convertView.setTag(R.id.cartImageView,cartImageView);
            convertView.setTag(R.id.nameCartItemTv,nameCartItemTv);
            convertView.setTag(R.id.priceCartItemTv,priceCartItemTv);
            convertView.setTag(R.id.cartMinusBtn,cartMinusBtn);
            convertView.setTag(R.id.cartValueBtn,cartValueBtn);
            convertView.setTag(R.id.cartAddBtn,cartAddBtn);
        }else{
            cartImageView= (ImageView) convertView.getTag(R.id.cartImageView);
            nameCartItemTv= (TextView) convertView.getTag(R.id.nameCartItemTv);
            priceCartItemTv= (TextView) convertView.getTag(R.id.priceCartItemTv);
            cartMinusBtn= (Button) convertView.getTag(R.id.cartMinusBtn);
            cartValueBtn= (Button) convertView.getTag(R.id.cartValueBtn);
            cartAddBtn= (Button) convertView.getTag(R.id.cartAddBtn);
        }

        CartItem cartItem=cartItemList.get(position);

        cartImageView.setImageResource(cartItem.getImageProduct());
        nameCartItemTv.setText(cartItem.getNameProduct());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        long price=cartItem.getPriceProduct()*cartItem.getQuantity();
        priceCartItemTv.setText("Price: "+decimalFormat.format(price)+" Đ");
        cartValueBtn.setText(String.valueOf(cartItem.getQuantity()));


        int quantity=Integer.parseInt(cartValueBtn.getText().toString());
        if(quantity>=cartItem.getRealQuantity()){
            cartAddBtn.setVisibility(View.INVISIBLE);
            cartMinusBtn.setVisibility(View.VISIBLE);
        }else if(quantity<=1){
            cartAddBtn.setVisibility(View.VISIBLE);
            cartMinusBtn.setVisibility(View.INVISIBLE);
        }else{
            cartAddBtn.setVisibility(View.VISIBLE);
            cartMinusBtn.setVisibility(View.VISIBLE);
        }

        cartMinusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newQuantity=Integer.parseInt(cartValueBtn.getText().toString())-1;
                cartValueBtn.setText(String.valueOf(newQuantity));
                MainActivity.cart.getCartItemList().get(position).setQuantity(newQuantity);
                long newPrice=MainActivity.cart.getCartItemList().get(position).getPriceProduct()*newQuantity;
                priceCartItemTv.setText("Price: "+decimalFormat.format(newPrice)+" Đ");
                CartActivity.totalPriceTv.setText(decimalFormat.format(MainActivity.cart.getTotalprice())+" Đ");
                if(newQuantity>=cartItem.getRealQuantity()){
                    cartAddBtn.setVisibility(View.INVISIBLE);
                    cartMinusBtn.setVisibility(View.VISIBLE);
                }else if(newQuantity<=1){
                    cartAddBtn.setVisibility(View.VISIBLE);
                    cartMinusBtn.setVisibility(View.INVISIBLE);
                }else{
                    cartAddBtn.setVisibility(View.VISIBLE);
                    cartMinusBtn.setVisibility(View.VISIBLE);
                }

            }
        });
        cartAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newQuantity=Integer.parseInt(cartValueBtn.getText().toString())+1;
                cartValueBtn.setText(String.valueOf(newQuantity));
                MainActivity.cart.getCartItemList().get(position).setQuantity(newQuantity);
                long newPrice=MainActivity.cart.getCartItemList().get(position).getPriceProduct()*newQuantity;
                priceCartItemTv.setText("Price: "+decimalFormat.format(newPrice)+" Đ");
                CartActivity.totalPriceTv.setText(decimalFormat.format(MainActivity.cart.getTotalprice())+" Đ");
                if(newQuantity>=cartItem.getRealQuantity()){
                    cartAddBtn.setVisibility(View.INVISIBLE);
                    cartMinusBtn.setVisibility(View.VISIBLE);
                }else if(newQuantity<=1){
                    cartAddBtn.setVisibility(View.VISIBLE);
                    cartMinusBtn.setVisibility(View.INVISIBLE);
                }else{
                    cartAddBtn.setVisibility(View.VISIBLE);
                    cartMinusBtn.setVisibility(View.VISIBLE);
                }

            }
        });

        return convertView;
    }
}
