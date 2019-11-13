package com.example.shoponline.apdapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoponline.R;
import com.example.shoponline.model.Product;

import java.text.DecimalFormat;
import java.util.List;

public class ProductApdater extends BaseAdapter {
    private Activity activity;
    private int layout;
    private List<Product> productList;

    public ProductApdater(Activity activity, int layout, List<Product> productList) {
        this.activity = activity;
        this.layout = layout;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView productImageView;
        TextView nameProductTv,priceProductTv,descriptionProductTv;
        if(convertView==null){
            convertView=activity.getLayoutInflater().inflate(layout,null);
            productImageView=convertView.findViewById(R.id.productImageView);
            nameProductTv=convertView.findViewById(R.id.nameProductTv);
            priceProductTv=convertView.findViewById(R.id.priceProductTv);
            descriptionProductTv=convertView.findViewById(R.id.descriptionProductTv);

            convertView.setTag(R.id.productImageView,productImageView);
            convertView.setTag(R.id.nameProductTv,nameProductTv);
            convertView.setTag(R.id.priceProductTv,priceProductTv);
            convertView.setTag(R.id.descriptionProductTv,descriptionProductTv);
        }else{
            productImageView= (ImageView) convertView.getTag(R.id.productImageView);
            nameProductTv= (TextView) convertView.getTag(R.id.nameProductTv);
            priceProductTv= (TextView) convertView.getTag(R.id.priceProductTv);
            descriptionProductTv= (TextView) convertView.getTag(R.id.descriptionProductTv);
        }
        Product product=productList.get(position);
        productImageView.setImageResource(product.getImageProduct());
        nameProductTv.setText(product.getNameProduct());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        priceProductTv.setText("Price: "+decimalFormat.format(product.getPrice())+" Đ");
        descriptionProductTv.setText(product.getDescriptionProduct());
        descriptionProductTv.setMaxLines(2);
        descriptionProductTv.setEllipsize(TextUtils.TruncateAt.END);

        return convertView;
    }
}
