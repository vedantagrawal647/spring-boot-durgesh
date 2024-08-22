package com.scheduler.SchedulerApplication;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulingDemo {

    @Scheduled(initialDelay = 0, fixedDelay = 1000)
    public void m1()
    {
        System.out.println("sidhar m1");
    }

    @Scheduled(initialDelay = 3000, fixedDelay = 1000)
    public void m2()
        {
            System.out.println("hello m2");
        }


    @Scheduled(initialDelay = 5000, fixedDelay = 1000)
    public void m3()
    {
        System.out.println("komi m3");
    }

    @Scheduled(cron= "*/1 41 15 * * ?" )
    public void m4()
    {
        System.out.println("sidhar m1");
    }



}
