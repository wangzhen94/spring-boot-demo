package com.xkcoding.cache.redis.controller;

import com.xkcoding.cache.redis.entity.User;
import com.xkcoding.cache.redis.service.UserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description: TODO
 *
 * @author wangzhen
 * @since 2024/3/5 22:24
 */
@RestController
@RequestMapping(value = "/user")
@ResponseBody
public class UserController {

    @Resource
    private UserService service;

    @PostMapping()
    public User saveOrUpdate(
        @RequestParam(required = false, value = "id") Long id,
        @RequestParam(value = "name") String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return service.saveOrUpdate(user);
    }

    /**
     * 获取用户
     *
     * @param id key值
     * @return 返回结果
     */
    @GetMapping(value = "user")
    public User get(Long id) {
        return null;
    }

    /**
     * 删除
     *
     * @param id key值
     */
    @DeleteMapping(value = "user")
    public void delete(Long id) {
        return;
    }


}
