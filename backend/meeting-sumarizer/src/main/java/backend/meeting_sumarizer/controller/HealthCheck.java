package backend.meeting_sumarizer.controller;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Component
public class HealthCheck implements HealthIndicator {
    @Override
    public Health health() {

        boolean isHealthy = checkCustomHealth();
        if (isHealthy) {
            return Health.up().withDetail("CustomHealth", "Looks good Copy that").build();
        } else {
            return Health.down().withDetail("CustomHealth", "We got a problem").build();
        }
    }

    private boolean checkCustomHealth() {

        return true;
    }
}
