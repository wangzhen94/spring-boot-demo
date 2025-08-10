package com.xkcoding.shedlock.schedule;

import com.xkconding.common.entity.User;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

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

    @Scheduled(cron = "*/3 * * * * ?")
    @SchedulerLock(name = "common", lockAtMostFor = "PT2S", lockAtLeastFor = "PT1S")
    public void run() {
        log.info("执行任务时间: {}", LocalDateTime.now());
        try {
            Thread.sleep(5500);
            log.info("任务: {} 执行完成", LocalDateTime.now());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        User user = new User();
    }


    // @Scheduled(cron = "*/3 * * * * ?")
    // @SchedulerLock(name = "test", lockAtMostFor = "PT2S", lockAtLeastFor = "PT1S")
    // public void test() {
    //     log.info("执行test任务时间: {}", LocalDateTime.now());
    //     try {
    //         Thread.sleep(2500);
    //     } catch (InterruptedException e) {
    //         throw new RuntimeException(e);
    //     }
    // }
}
