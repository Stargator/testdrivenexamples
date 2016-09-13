package com.tddinaction.mockobjects;

public class PricingService {
    // Assume this class represents a service that has an issue similar to the following:
    // - What if it takes a full second to execute its calculateDiscountedPrice(Order) method?
    // - What if we needed to create a huge amount of test data for the PricingService to work on?
    // - What if the PricingService required a live connection to a database?
    // - What if we haven’t yet implemented the PricingService in the first place?

    public PricingService() {
    }

    public PricingService(float discount) {
    }

    public float getDiscountPercentage(Customer c, Product p) {
        return 0;
    }
}
