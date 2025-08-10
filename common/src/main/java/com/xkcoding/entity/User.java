package com.xkcoding.entity;

import lombok.Data;

/**
 * Description: TODO
 *
 * @author wangzhen
 * @since 2024/2/25 23:18
 */
@Data
public class User {
  private Long id;
  private String name;
  private int age;
  private String createTime;
}
