package com.example.blog_webapp.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class FileCompressorService {
    // Run every 5 seconds
    @Scheduled(fixedRate = 10000)
    public void fixedRateTask() {
        System.out.println("Fixed Rate Task - " + System.currentTimeMillis() / 1000);
    }

//    // Run 5 seconds after the previous execution completes
//    @Scheduled(fixedDelay = 5000)
//    public void fixedDelayTask() {
//        System.out.println("Fixed Delay Task - " + System.currentTimeMillis() / 1000);
//    }

    // Run at a specific time (e.g., every day at 10 AM)
//    @Scheduled(cron = "0 0 10 * * ?")
//    public void cronTask() {
//        System.out.println("Cron Task - " + System.currentTimeMillis() / 1000);
//    }

}
