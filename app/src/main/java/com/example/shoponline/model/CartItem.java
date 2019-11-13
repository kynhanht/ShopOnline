package com.example.shoponline.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class CartItem implements Serializable {
    private int productId;
    private String nameProduct;
    private long priceProduct;
    private int imageProduct;
    private int quantity;
    private int realQuantity;

    public CartItem() {
    }


    public CartItem(int productId, String nameProduct, long priceProduct, int imageProduct, int quantity, int realQuantity) {
        this.productId = productId;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.imageProduct = imageProduct;
        this.quantity = quantity;
        this.realQuantity = realQuantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public long getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(long priceProduct) {
        this.priceProduct = priceProduct;
    }

    public int getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(int imageProduct) {
        this.imageProduct = imageProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getRealQuantity() {
        return realQuantity;
    }

    public void setRealQuantity(int realQuantity) {
        this.realQuantity = realQuantity;
    }

    @NonNull
    @Override
    public String toString() {
        return nameProduct;
    }
}
