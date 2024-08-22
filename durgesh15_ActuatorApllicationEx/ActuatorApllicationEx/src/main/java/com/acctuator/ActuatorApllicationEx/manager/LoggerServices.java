package com.acctuator.ActuatorApllicationEx.manager;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class LoggerServices implements HealthIndicator {

    private final String LOGGER_SERVICE= "LOGGER Service";

    @Override
    public Health health() {
        if(isLoggerServiceGood())
        {
            return Health.up().withDetail(LOGGER_SERVICE,"Service is running").build();
        }
        return Health.down().withDetail(LOGGER_SERVICE,"Service is not available").build();
    }

    private  boolean isLoggerServiceGood(){
        //logic
        return false;
    }
}
