package com.challenge.TechChallenge.api.model;

import com.challenge.TechChallenge.service.RequestService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PaymentTest {

    private Payment payment = new Payment();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this );
    }

    @Test
    @SneakyThrows
    void getPayment_number() {
        payment.setPayment_number(1);

        assert(payment.getPayment_number() == 1);
    }

    @Test
    void getAmount() {
        payment.setAmount(123D);

        assert(payment.getAmount() == 123D);
    }

    @Test
    void getPayment_date() {
        payment.setPayment_date(new Date());

        assert(payment.getPayment_date() != "");
    }
}