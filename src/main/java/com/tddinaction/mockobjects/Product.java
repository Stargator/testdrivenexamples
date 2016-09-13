package com.tddinaction.mockobjects;

public class Product {
    private float listPrice;

    public Product(String productName, float listPrice) {
        this.listPrice = listPrice;
    }

    public float getListPrice() {
        return listPrice;
    }
}
