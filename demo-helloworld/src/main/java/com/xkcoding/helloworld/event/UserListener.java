package com.xkcoding.helloworld.event;

import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Description: TODO
 *
 * @author wangzhen
 * @since 2024/4/8 10:52
 */
@Component
public class UserListener {
  @EventListener
  public void createUser(UserEvent userEvent) {
    System.out.println(JSONUtil.toJsonStr(userEvent.getUser()));
  }
}
