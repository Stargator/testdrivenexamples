package com.tddinaction.mockobjects;

import org.junit.Test;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

public class OrderProcessorEasymockTest {
    @Test
    public void testOrderProcessorWithEasyMock() throws Exception {
        // arrange
        float initialBalance = 100.0f;
        float listPrice = 30.0f;
        float discount = 10.0f;
        float expectedBalance =
                initialBalance - (listPrice * (1 - discount / 100));
        Customer customer = new Customer(initialBalance);
        Product product = new Product("TDD in Action", listPrice);

        // record expected collaboration with mock PricingService
        PricingService mock = createMock(PricingService.class);
        expect(mock.getDiscountPercentage(customer, product))
                .andReturn(discount);
        replay(mock);

        // act
        OrderProcessor processor = new OrderProcessor();
        processor.setPricingService(mock);
        processor.process(new Order(customer, product));

        // assert
        assertEquals(expectedBalance, customer.getBalance(),
                0.001f);
        verify(mock);
    }
}