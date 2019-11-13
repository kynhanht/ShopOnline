package com.example.shoponline.model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private int orderId;
    private String orderName;
    private String username;
    private long payment;
    private String orderDate;

    public Order() {
    }


    public Order(int orderId, String orderName, String username, long payment, String orderDate) {
        this.orderId = orderId;
        this.orderName = orderName;
        this.username = username;
        this.payment = payment;
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getPayment() {
        return payment;
    }

    public void setPayment(long payment) {
        this.payment = payment;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Order(int orderId) {
        this.orderId = orderId;
    }

    @NonNull
    @Override
    public String toString() {
        return orderName;
    }
}
