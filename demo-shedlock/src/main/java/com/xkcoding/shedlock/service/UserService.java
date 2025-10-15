package com.xkcoding.shedlock.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void cost(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
