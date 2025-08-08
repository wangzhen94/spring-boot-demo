package com.xkcoding.shedlock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * <p>
 * 启动器
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019-03-31 22:24
 */
@SpringBootApplication
@EnableScheduling
public class SpringBootDemoShedLockApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoShedLockApplication.class, args);
    }

}

