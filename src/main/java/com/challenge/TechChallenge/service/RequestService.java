package com.challenge.TechChallenge.service;

import com.challenge.TechChallenge.api.model.Payment;
import com.challenge.TechChallenge.api.model.Request;
import com.challenge.TechChallenge.exception.ValidationException;
import com.challenge.TechChallenge.repository.PaymentRepository;
import com.challenge.TechChallenge.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RequestService {
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private Environment env;
    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> calculatePayment(Request request) throws ValidationException{
        saveRequest(request, "Pending", null);
        if (request.getTerms() < Integer.parseInt(env.getProperty("min.terms"))
                || request.getTerms() > Integer.parseInt(env.getProperty("max.terms"))) {
            saveRequest(request, "Failed", "The terms validation was failed");
            throw new ValidationException("The terms validation was failed!");
        } else if (request.getRate() < Integer.parseInt(env.getProperty("min.rate"))
                || request.getRate() > Integer.parseInt(env.getProperty("max.rate"))) {
            saveRequest(request, "Failed", "The rate validation was failed");
            throw new ValidationException("The rate validation was failed!");
        } else if (request.getAmount() < Double.parseDouble(env.getProperty("min.amount"))
                || request.getAmount() > Double.parseDouble(env.getProperty("max.amount"))) {
            saveRequest(request, "Failed", "The amount validation was failed");
            throw new ValidationException("The amount validation was failed!");
        }

        double monthlyRate = request.getRate() / 100 / request.getTerms();
        double monthlyPayment = request.getAmount() * monthlyRate / (1 - Math.pow(1 + monthlyRate, -request.getTerms()));
        Date date = new Date();
        for (int i = 1; i <= request.getTerms(); i++) {
            date.setTime(date.getTime() + 604800000);
            paymentRepository.save(new Payment(i, monthlyPayment, new Date(date.getTime()), request.getId()));
        }

        saveRequest(request, "Success", null);
        return paymentRepository.findByRequestId(request.getId());
    }

    public void saveRequest(Request request, String status, String errorMsg) {
        request.setStatus(status);
        request.setErrorMsg(errorMsg);
        requestRepository.save(request);
    }

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
