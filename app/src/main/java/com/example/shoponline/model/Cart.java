package com.example.shoponline.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cart {
    private List<CartItem> cartItemList;

    public Cart() {
        cartItemList=new ArrayList<>();
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public void addCartItem(CartItem newCartItem){
        if(cartItemList.isEmpty()){
            cartItemList.add(newCartItem);
        }else{
            int productId=newCartItem.getProductId();
            int quantity=newCartItem.getQuantity();
            for (int i = 0; i <cartItemList.size() ; i++) {
                if(cartItemList.get(i).getProductId()==productId){
                    cartItemList.get(i).setQuantity(cartItemList.get(i).getQuantity()+quantity);
                    return;
                }
            }
            cartItemList.add(newCartItem);
        }
    }
    
    public long getTotalprice(){
        if(cartItemList.isEmpty()) return 0;
        long totalPrice=0;
        for (int i=0;i<cartItemList.size();i++){
            totalPrice+=cartItemList.get(i).getPriceProduct()*cartItemList.get(i).getQuantity();
        }
        return totalPrice;
    }
}
