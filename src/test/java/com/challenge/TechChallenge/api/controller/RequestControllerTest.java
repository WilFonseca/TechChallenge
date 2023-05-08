package com.challenge.TechChallenge.api.controller;

import com.challenge.TechChallenge.api.model.Payment;
import com.challenge.TechChallenge.api.model.Request;
import com.challenge.TechChallenge.exception.ValidationException;
import com.challenge.TechChallenge.service.RequestService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class RequestControllerTest {

    @Mock
    private RequestService requestService;

    @InjectMocks
    private RequestController requestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this );
    }

    @Test
    @SneakyThrows
    void calculatePayments() {
        List<Payment> paymentList = new ArrayList<>();

        when(requestService.calculatePayment(any(Request.class))).thenReturn(paymentList);

        assert(requestController.calculatePayments(new Request()) != null);
    }

    @Test
    @SneakyThrows
    void calculatePaymentsError() {
        List<Payment> paymentList = new ArrayList<>();

        when(requestService.calculatePayment(any(Request.class))).thenThrow(ValidationException.class);

        assert(requestController.calculatePayments(new Request()) == null);
    }

    @Test
    void getRequests() {
        List<Request> requestList = new ArrayList<>();

        when(requestService.getAllRequests()).thenReturn(requestList);

        assert(requestController.getRequests() != null);
    }

    @Test
    void getPayments() {
        List<Payment> paymentList = new ArrayList<>();

        when(requestService.getAllPayments()).thenReturn(paymentList);

        assert(requestController.getPayments() != null);
    }
}