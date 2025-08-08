package com.xkcoding.helloworld.event;

import com.xkcoding.helloworld.entity.User;
import org.springframework.context.ApplicationEvent;

/**
 * Description: TODO
 *
 * @author wangzhen
 * @since 2024/4/8 10:51
 */
public class UserEvent extends ApplicationEvent {
  /**
   * Create a new ApplicationEvent.
   *
   * @param source the object on which the event initially occurred (never {@code null})
   */

  private User user;

  public UserEvent(Object source) {
    super(source);
  }

  public UserEvent(Object source, User user) {
    super(source);
    this.user = user;
  }

  public User getUser() {
    return user;
  }
}
