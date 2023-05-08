package com.challenge.TechChallenge.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    @Id
    @GeneratedValue
    private Integer id;
    private Double amount;
    private Integer terms;
    private Double rate;
    private String status;
    private String errorMsg;
}
