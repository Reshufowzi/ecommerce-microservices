package com.example.cart.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping
    public String addToCart(@RequestParam String userId,
                           @RequestParam String product) {

        redisTemplate.opsForList().rightPush(userId, product);
        return "Added";
    }

    @GetMapping("/{userId}")
    public List<Object> getCart(@PathVariable String userId) {
        return redisTemplate.opsForList().range(userId, 0, -1);
    }
}
