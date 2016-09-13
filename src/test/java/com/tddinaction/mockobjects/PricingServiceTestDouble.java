package com.tddinaction.mockobjects;

public class PricingServiceTestDouble extends PricingService {

    private float discount;

    public PricingServiceTestDouble(float discount) {
        super();
        this.discount = discount;
    }

    @Override
    public float getDiscountPercentage(Customer c, Product p) {
        return discount;
    }
}