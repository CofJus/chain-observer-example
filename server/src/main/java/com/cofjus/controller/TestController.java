package com.cofjus.controller;

import com.cofjus.test.TestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestClient testClient;

    @GetMapping("/validate")
    public Boolean validate(@RequestParam("userId") String userId) {
        return testClient.test(userId);
    }
}
