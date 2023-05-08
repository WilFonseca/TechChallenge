package com.challenge.TechChallenge.HealthCheck;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthMemoryData implements HealthIndicator {
    @Override
    public Health health() {
        long freeMemory = Runtime.getRuntime().freeMemory();
        long totalMemory = Runtime.getRuntime().totalMemory();
        double freeMemoryPercent = ((double) freeMemory / (double) totalMemory) * 100;
        if (freeMemoryPercent > 25) {
            return Health.up()
                    .withDetail("free_memory", String.format("%.2f MB",  freeMemory/1000000D))
                    .withDetail("total_memory", String.format("%.2f MB",  totalMemory/1000000D))
                    .withDetail("free_memory_percent", String.format("%.2f", freeMemoryPercent))
                    .build();
        } else {
            return Health.down()
                    .withDetail("free_memory", freeMemory + " bytes")
                    .withDetail("total_memory", totalMemory + " bytes")
                    .withDetail("free_memory_percent", freeMemoryPercent + "%")
                    .build();
        }
    }
}
