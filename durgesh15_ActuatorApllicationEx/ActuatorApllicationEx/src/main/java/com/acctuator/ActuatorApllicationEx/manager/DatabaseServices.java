package com.acctuator.ActuatorApllicationEx.manager;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class DatabaseServices implements HealthIndicator {

    private final String DATABASE_SERVICE= "DatabaseService";

    @Override
    public Health health() {
        if(isDatabaseHealthGood())
        {
            return Health.up().withDetail(DATABASE_SERVICE,"Service is running").build();
        }
        return Health.down().withDetail(DATABASE_SERVICE,"Service is not available").build();
    }

    private  boolean isDatabaseHealthGood(){
        //custom logic
        return true;
    }
}
