package com.example.shoponline.apdapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoponline.R;
import com.example.shoponline.activity.DetailProductActivity;
import com.example.shoponline.activity.ProductActivity;
import com.example.shoponline.model.Product;

import java.text.DecimalFormat;
import java.util.List;

public class NewProductAdapter extends RecyclerView.Adapter<NewProductAdapter.ProductHolder> {
    private Activity activity;
    private int layout;
    private List<Product> productList;

    public NewProductAdapter(Activity activity, int layout, List<Product> productList) {
        this.activity = activity;
        this.layout = layout;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(layout,null);
        ProductHolder productHolder=new ProductHolder(v);
        return productHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        Product product=productList.get(position);
        holder.getNameProductTv().setText(product.getNameProduct());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        holder.getPriceProductTv().setText("Price: "+decimalFormat.format(product.getPrice())+" Đ");
        holder.getProductImageView().setImageResource(product.getImageProduct());
        holder.getProductImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity, DetailProductActivity.class);
                intent.putExtra("product",productList.get(position));
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder{

        private ImageView productImageView;
        private TextView nameProductTv,priceProductTv;

        public ImageView getProductImageView() {
            return productImageView;
        }

        public void setProductImageView(ImageView productImageView) {
            this.productImageView = productImageView;
        }

        public TextView getNameProductTv() {
            return nameProductTv;
        }

        public void setNameProductTv(TextView nameProductTv) {
            this.nameProductTv = nameProductTv;
        }

        public TextView getPriceProductTv() {
            return priceProductTv;
        }

        public void setPriceProductTv(TextView priceProductTv) {
            this.priceProductTv = priceProductTv;
        }

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            productImageView=itemView.findViewById(R.id.hotImageView);
            nameProductTv=itemView.findViewById(R.id.hotNameTv);
            priceProductTv=itemView.findViewById(R.id.hotPriceTv);
        }
    }

}
