package com.yangzhe.hello;

import com.alibaba.druid.support.json.JSONUtils;
import com.yangzhe.entity.User;
import com.yangzhe.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class SampleController {

    @Autowired
    IUserService userService;

    @RequestMapping(value = "/{userName}",method = RequestMethod.GET)
    @ResponseBody
    String home(@PathVariable("userName") String userName) {
        return userService.findByUsername(userName).getUserName();

    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    String register(User u) {
        userService.save(u);
        return "register success:"+u.getUserName();
    }

    @RequestMapping(value = "/id/{id}",method = RequestMethod.GET)
    @ResponseBody
    String findById(@PathVariable("id") long id){
        return JSONUtils.toJSONString(userService.getUserById(id));
    }
}
