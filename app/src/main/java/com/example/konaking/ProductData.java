package com.example.konaking;

import java.io.Serializable;

public class ProductData implements Serializable {
    private String Nickname="";
    private String Product="";
    private String CartProduct="";
    private int Price=0;
    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price += price;
    }
    public void ClearPrice() {
        Price =0;
    }
    public void ClearCartProduct() {
        CartProduct ="";
    }
    public String getCartProduct() {
        return CartProduct;
    }

    public void setCartProduct(String cartProduct) {
        CartProduct = cartProduct;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product=Product.concat(product);
    }
    public void ClearProduct() {
        Product="";
    }


}
