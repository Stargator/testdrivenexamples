package com.tddinaction.mockobjects;

public class OrderProcessor {
    private PricingService pricingService;

    public void setPricingService(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    public void process(Order order) {
        Customer customer = order.getCustomer();
        Product product = order.getProduct();
        float discountPercentage = pricingService.getDiscountPercentage(customer, product);
        float newBalance =
                (customer.getBalance() -
                        (product.getListPrice() * (1 - discountPercentage/100)));
        customer.setBalance(newBalance);
    }
}
