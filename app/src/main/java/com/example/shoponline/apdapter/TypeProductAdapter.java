package com.example.shoponline.apdapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.shoponline.R;
import com.example.shoponline.model.TypeProduct;
import java.util.List;

public class TypeProductAdapter extends BaseAdapter {
    private Activity activity;
    private int layout;
    private List<TypeProduct> typeProductList;

    public TypeProductAdapter(Activity activity, int layout, List<TypeProduct> typeProductList) {
        this.activity = activity;
        this.layout = layout;
        this.typeProductList = typeProductList;
    }

    @Override
    public int getCount() {
        return typeProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return typeProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView productImage;
        TextView typeNameProductTv;
        if(convertView==null){
            convertView=activity.getLayoutInflater().inflate(layout,null);
            productImage=convertView.findViewById(R.id.typeProductImage);
            typeNameProductTv=convertView.findViewById(R.id.typeNameProductTv);
            convertView.setTag(R.id.typeProductImage,productImage);
            convertView.setTag(R.id.typeNameProductTv,typeNameProductTv);
        }else{
            productImage= (ImageView) convertView.getTag(R.id.typeProductImage);
            typeNameProductTv= (TextView) convertView.getTag(R.id.typeNameProductTv);
        }
        TypeProduct typeProduct=typeProductList.get(position);
        productImage.setImageResource(typeProduct.getImage());
        typeNameProductTv.setText(typeProduct.getTypeNameProduct());
        return convertView;
    }
}
