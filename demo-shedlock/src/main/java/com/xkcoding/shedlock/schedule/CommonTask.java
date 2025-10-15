package com.xkcoding.shedlock.schedule;

import com.xkcoding.shedlock.service.UserService;
import com.xkconding.common.entity.User;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: TODO
 *
 * @author wangzhen
 * @since 2025/7/12 00:17
 */
@Slf4j
@Component
@EnableSchedulerLock(defaultLockAtMostFor = "PT30S", interceptMode = EnableSchedulerLock.InterceptMode.PROXY_METHOD)
public class CommonTask {

    @Autowired
    private UserService userService;

//    @Scheduled(cron = "*/3 * * * * ?")
    @Scheduled(fixedRateString = "PT3s")
    @SchedulerLock(name = "common", lockAtMostFor = "PT2S", lockAtLeastFor = "PT1S")
    public void run() {
        LocalDateTime now = LocalDateTime.now();
        log.info("执行common任务时间: {}", now);
        try {
            Thread.sleep(1000);
            userService.cost(500);
            log.info("任务common: {} 执行完成", LocalDateTime.now());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Byte[] bb = new Byte[now.getSecond() * 1024];
        List<User> l = new ArrayList<>();
        for (int i = 0; i < now.getSecond() * 1000; i++) {
            User u = new User();
            l.add(u);
        }
    }


//     @Scheduled(cron = "*/3 * * * * ?")
     @SchedulerLock(name = "test", lockAtMostFor = "PT2S", lockAtLeastFor = "PT1S")
     public void test() {
         log.info("执行test任务时间: {}", LocalDateTime.now());
         try {
             Thread.sleep(2500);
             log.info("任务test: {} 执行完成", LocalDateTime.now());
         } catch (InterruptedException e) {
             throw new RuntimeException(e);
         }
     }
}
