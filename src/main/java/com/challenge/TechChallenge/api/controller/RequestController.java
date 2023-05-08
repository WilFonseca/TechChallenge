package com.challenge.TechChallenge.api.controller;

import com.challenge.TechChallenge.api.model.Payment;
import com.challenge.TechChallenge.api.model.Request;
import com.challenge.TechChallenge.exception.ValidationException;
import com.challenge.TechChallenge.service.RequestService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RequestController {
    private RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/interest")
    @JsonView(Payment.class)
    public List<Payment> calculatePayments(@RequestBody Request request) {
        try {
            return requestService.calculatePayment(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/getRequests")
    public List<Request> getRequests() {
        return requestService.getAllRequests();
    }

    @GetMapping("/getPayments")
    public List<Payment> getPayments() {
        return requestService.getAllPayments();
    }
}
