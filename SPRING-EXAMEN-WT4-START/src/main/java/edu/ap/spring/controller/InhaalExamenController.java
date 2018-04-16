package edu.ap.spring.controller;


import edu.ap.spring.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InhaalExamenController {

    private RedisService service;

    @Autowired
    public void setService(RedisService service) {
        this.service = service;
    }

    @RequestMapping("/")
    public String index() {
        return "Eightball";
    }

}
