package com.challenge.TechChallenge.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer requestId;
    private Integer payment_number;
    private Double amount;
    private Date payment_date;

    public Payment(Integer payment_number, Double amount, Date payment_date, Integer requestId) {
        this.payment_number = payment_number;
        this.amount = amount;
        this.payment_date = payment_date;
        this.requestId = requestId;
    }

    @JsonView(Payment.class)
    public Integer getPayment_number() {
        return payment_number;
    }

    @JsonView(Payment.class)
    public Double getAmount() {
        return Double.parseDouble(String.format("%.2f", amount));
    }

    @JsonView(Payment.class)
    public String getPayment_date() {
        return new SimpleDateFormat("dd/MM/yyyy").format(payment_date);
    }
}
