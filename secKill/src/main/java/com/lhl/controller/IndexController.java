package com.lhl.controller;

import com.lhl.domain.Demo;
import com.lhl.domain.User;
import com.lhl.redis.RedisService;
import com.lhl.redis.UserKey;
import com.lhl.result.Result;
import com.lhl.service.DemoService;
import com.lhl.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
    @Autowired
    DemoService demoService;
    @Autowired
    RedisService redisService;

    @RequestMapping("index")
    public String index(Model model, LoginVo lv){
        System.out.println("**********"+lv.toString());
        model.addAttribute("d",new Demo("jack","rich"));
        return "index";
    }
    @ResponseBody
    @RequestMapping("re")
    public Result<String> re(Model model){
        return Result.success("sss");
    }

    @ResponseBody
    @RequestMapping("in")
    public Result<String> in(){
        demoService.tx();
        return Result.success("插入");
    }

    @ResponseBody
    @RequestMapping("get")
    public Demo get(){
        return demoService.getByName("s");
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet() {
        User  user  = redisService.get(UserKey.getById, ""+1, User.class);
        return Result.success(user);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet() {
        User user  = new User();
        user.setId(1);
        user.setName("1111");
        redisService.set(UserKey.getById, ""+1, user);//UserKey:id1
        return Result.success(true);
    }
}
