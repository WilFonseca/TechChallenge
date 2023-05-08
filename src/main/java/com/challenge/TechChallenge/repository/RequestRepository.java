package com.challenge.TechChallenge.repository;

import com.challenge.TechChallenge.api.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository <Request, Integer> {
}
