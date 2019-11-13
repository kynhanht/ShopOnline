package com.example.shoponline.model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

public class TypeProduct implements Serializable {
    private int typeProductId;
    private String typeNameProduct;
    private int image;

    public TypeProduct() {
    }

    public TypeProduct(int typeProductId, String typeNameProduct, int image) {
        this.typeProductId = typeProductId;
        this.typeNameProduct = typeNameProduct;
        this.image = image;
    }

    public int getTypeProductId() {
        return typeProductId;
    }

    public void setTypeProductId(int typeProductId) {
        this.typeProductId = typeProductId;
    }

    public String getTypeNameProduct() {
        return typeNameProduct;
    }

    public void setTypeNameProduct(String typeNameProduct) {
        this.typeNameProduct = typeNameProduct;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return typeNameProduct;
    }
}
