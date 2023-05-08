package com.challenge.TechChallenge.HealthCheck;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class HealthMemoryDataTest {

    @Mock
    private Runtime runtime;

    @InjectMocks
    private HealthMemoryData healthMemoryData;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this );
    }

    @Test
    void healthUp() {
    }

    @Test
    void healthDown() {

    }
}