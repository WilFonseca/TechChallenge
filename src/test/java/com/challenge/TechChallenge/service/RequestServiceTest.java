package com.challenge.TechChallenge.service;

import com.challenge.TechChallenge.api.model.Payment;
import com.challenge.TechChallenge.api.model.Request;
import com.challenge.TechChallenge.exception.ValidationException;
import com.challenge.TechChallenge.repository.PaymentRepository;
import com.challenge.TechChallenge.repository.RequestRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class RequestServiceTest {

    private Payment payment1 = new Payment();
    private Payment payment2 = new Payment();
    private Payment payment3 = new Payment();
    private Payment payment4 = new Payment();
    private Request request1 = new Request();
    private Request request2 = new Request();

    @Mock
    private RequestRepository requestRepository;
    @Mock
    private Environment env;
    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private RequestService requestService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this );
    }

    @SneakyThrows
    @Test
    void calculatePayment() {
        request1.setRate(6D);
        request1.setAmount(5000D);
        request1.setTerms(4);
        payment1.setId(1);
        payment1.setPayment_number(1);
        payment1.setAmount(1297.22D);
        payment1.setPayment_date(new Date(2023,05,14));
        payment1.setRequestId(1);
        payment2.setId(1);
        payment2.setPayment_number(2);
        payment2.setAmount(1297.22D);
        payment2.setPayment_date(new Date(2023,05,21));
        payment2.setRequestId(1);
        payment3.setId(1);
        payment3.setPayment_number(3);
        payment3.setAmount(1297.22D);
        payment3.setPayment_date(new Date(2023,05,28));
        payment3.setRequestId(1);
        payment4.setId(1);
        payment4.setPayment_number(4);
        payment4.setAmount(1297.22D);
        payment4.setPayment_date(new Date(2023,06,04));
        payment4.setRequestId(1);

        List<Payment> paymentList = new ArrayList();
        paymentList.add(payment1);
        paymentList.add(payment2);
        paymentList.add(payment3);
        paymentList.add(payment4);

        when(env.getProperty(anyString())).thenReturn("4").thenReturn("12").thenReturn("1").thenReturn("99")
                .thenReturn("1").thenReturn("999999");
        request1.setId(1);
        when(requestRepository.save(any(Request.class))).thenReturn(request1);
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment1).thenReturn(payment2)
                .thenReturn(payment3).thenReturn(payment4);
        request1.setStatus("Success");
        when(requestRepository.save(any(Request.class))).thenReturn(request1);
        when(paymentRepository.findByRequestId(anyInt())).thenReturn(paymentList);

        assert(requestService.calculatePayment(request1).size() == 4);
    }

    @Test
    void calculatePaymentFailInTerms() {
        String errorMsg = "";

        request1.setRate(6D);
        request1.setAmount(5000D);
        request1.setTerms(3);

        when(env.getProperty(anyString())).thenReturn("4").thenReturn("12");
        request1.setId(1);
        when(requestRepository.save(any(Request.class))).thenReturn(request1);

        try {
            requestService.calculatePayment(request1);
        } catch (ValidationException e) {
            errorMsg = e.getMessage();
        }
        assert(errorMsg == "The terms validation was failed!");

    }

    @Test
    void calculatePaymentFailInRate() {
        String errorMsg = "";

        request1.setRate(100D);
        request1.setAmount(5000D);
        request1.setTerms(4);

        when(env.getProperty(anyString())).thenReturn("4").thenReturn("12").thenReturn("1").thenReturn("99");
        request1.setId(1);
        when(requestRepository.save(any(Request.class))).thenReturn(request1);

        try {
            requestService.calculatePayment(request1);
        } catch (ValidationException e) {
            errorMsg = e.getMessage();
        }
        assert(errorMsg == "The rate validation was failed!");

    }

    @Test
    void calculatePaymentFailInAmount() {
        String errorMsg = "";

        request1.setRate(6D);
        request1.setAmount(0D);
        request1.setTerms(4);

        when(env.getProperty(anyString())).thenReturn("4").thenReturn("12").thenReturn("1").thenReturn("99")
                .thenReturn("1").thenReturn("999999");
        request1.setId(1);
        when(requestRepository.save(any(Request.class))).thenReturn(request1);

        try {
            requestService.calculatePayment(request1);
        } catch (ValidationException e) {
            errorMsg = e.getMessage();
        }
        assert(errorMsg == "The amount validation was failed!");
    }

    @Test
    void saveRequest() {
        request1.setRate(6D);
        request1.setAmount(1000D);
        request1.setTerms(4);
        request1.setStatus("example");
        request1.setErrorMsg("example 2");

        when(requestRepository.save(any(Request.class))).thenReturn(request1);
        request1.setId(1);

        requestService.saveRequest(request1, "Success", null);

        assert(request1.getStatus() == "Success" && request1.getErrorMsg() == null);
    }

    @Test
    void getAllRequests() {
        request1.setRate(6D);
        request1.setAmount(1000D);
        request1.setTerms(4);
        request1.setStatus("example");
        request1.setErrorMsg("example 2");
        request1.setId(1);

        request2.setRate(10D);
        request2.setAmount(1500D);
        request2.setTerms(7);
        request2.setStatus("example again");
        request2.setErrorMsg("example again 2");
        request2.setId(2);

        List<Request> requestList = new ArrayList<>();
        requestList.add(request1);
        requestList.add(request2);

        when(requestService.getAllRequests()).thenReturn(requestList);

        assert(requestService.getAllRequests().size() == 2);
    }

    @Test
    void getAllPayments() {
        payment1.setId(1);
        payment1.setPayment_number(1);
        payment1.setAmount(1297.22D);
        payment1.setPayment_date(new Date(2023,05,14));
        payment1.setRequestId(1);
        payment2.setId(1);
        payment2.setPayment_number(2);
        payment2.setAmount(1297.22D);
        payment2.setPayment_date(new Date(2023,05,21));
        payment2.setRequestId(1);
        payment3.setId(1);
        payment3.setPayment_number(3);
        payment3.setAmount(1297.22D);
        payment3.setPayment_date(new Date(2023,05,28));
        payment3.setRequestId(1);
        payment4.setId(1);
        payment4.setPayment_number(4);
        payment4.setAmount(1297.22D);
        payment4.setPayment_date(new Date(2023,06,04));
        payment4.setRequestId(1);

        List<Payment> paymentList = new ArrayList();
        paymentList.add(payment1);
        paymentList.add(payment2);
        paymentList.add(payment3);
        paymentList.add(payment4);

        when(requestService.getAllPayments()).thenReturn(paymentList);

        assert(requestService.getAllPayments().size() == 4);
    }
}