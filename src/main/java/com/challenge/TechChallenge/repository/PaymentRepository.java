package com.challenge.TechChallenge.repository;

import com.challenge.TechChallenge.api.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository <Payment, Integer> {
    List<Payment> findByRequestId(Integer requestId);
}
