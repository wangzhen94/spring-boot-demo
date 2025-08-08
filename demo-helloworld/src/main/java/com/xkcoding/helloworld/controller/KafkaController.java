package com.xkcoding.helloworld.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.xkcoding.entity.User;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Description: TODO
 *
 * @author wangzhen
 * @since 2024/2/25 23:13
 */
@RestController
@ResponseBody
public class KafkaController {

  @Resource
  KafkaTemplate<String, String> kafkaTemplate;

  @GetMapping("/message")
  public String createTopic() {
    new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        User user = new User();
        user.setId(Long.parseLong(String.valueOf(i)));
        // user.setName(name(i));
        // user.setAge(age());
        user.setCreateTime(DateUtil.formatTime(new Date()));
        kafkaTemplate.send("user", JSONUtil.toJsonStr(user));
        try {
          Thread.sleep(600);
        } catch (InterruptedException e) {
        }
      }
    }) {
    }.start();

    return "SUCCESS";
  }
  //
  // public static String name(int length) {
  //   // 定义可能包含在随机字符串中的字符
  //   final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
  //   final int CHAR_LIST_SIZE = CHAR_LIST.length();
  //   final Random random = new Random();
  //   final StringBuilder randomStringBuilder = new StringBuilder(length);
  //
  //   for (int i = 0; i < length; i++) {
  //     // 随机选择一个字符并添加到字符串构建器中
  //     randomStringBuilder.append(CHAR_LIST.charAt(random.nextInt(CHAR_LIST_SIZE)));
  //   }
  //
  //   return randomStringBuilder.toString();
  // }
  //
  //
  // public static int age() {
  //   Random random = new Random();
  //
  //   // 生成一个随机整数
  //   return random.nextInt(100); // 生成一个 0 到 99 之间的随机整数
  // }
}
