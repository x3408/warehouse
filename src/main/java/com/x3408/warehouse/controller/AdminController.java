package com.x3408.warehouse.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.x3408.warehouse.entity.Admin;
import com.x3408.warehouse.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("/adminLogin")
    public String adminLogin(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             HttpSession session, Map<String, String> map) {
        if (!StringUtils.isEmpty(username) && "123".equals(password)) {
            session.setAttribute("loginUser", username);
            return "redirect:/index";
        } else {
            map.put("loginMsg", "密码错误");
            return "login";
        }
    }

    @ResponseBody
    @PostMapping("/adminRegister")
    public String RegisterAdmin(Admin admin) {
        Integer successCount = adminService.registerAdmin(admin);
        Map<String, String> map = new HashMap<>();
        if (successCount==1)
            map.put("data", "1");
        else
            map.put("data", "0");
        //设置提示
        return JSONUtils.toJSONString(map);
    }
}
