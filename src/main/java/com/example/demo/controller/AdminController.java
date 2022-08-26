package com.example.demo.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.pojo.Admin;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    HttpServletRequest request;

    @GetMapping("/getAdminSession")
    public int getAdminSession(){
        HttpSession session = request.getSession();
        if(request.getSession().getAttribute("adminId")==null){
            return 0;
        }else {
            return 1;
        }
    }

    @GetMapping("/getAdminList")
    public List<Admin> getAdminList(){
        List<Admin> adminList = adminMapper.getAdminList();
        return adminList;
    }

    @PostMapping("/adminLogin")
    public int adminLogin(@RequestParam("account") String account , @RequestParam("password") String password){
        Admin admin = adminMapper.getAdminByAccount(account);
        if (admin == null){
            return 0;
        } else if (admin.getPassword().equals(password)){
            HttpSession session = request.getSession();
            session.setAttribute("adminId",admin.getId());
            return 1;
        } else {
            return 2;
        }
    }

    @GetMapping("/getAdminInfo")
    public JSONObject userInfo() {
        int adminId = Integer.parseInt(String.valueOf(request.getSession().getAttribute("adminId")));
        Admin admin = adminMapper.getAdminById(adminId);

        JSONObject json = new JSONObject();
        json.put("id", admin.getId());
        json.put("account", admin.getAccount());
        json.put("password", admin.getPassword());
        json.put("name", admin.getName());
        json.put("phone", admin.getPhone());
        json.put("email", admin.getEmail());
        json.put("office", admin.getOffice());
        json.put("duty", admin.getDuty());
        return json;
    }

    @PostMapping("/adminInfoChange")
    public int changeUserInfo(@RequestParam("name") String name , @RequestParam("phone") String phone, @RequestParam("email") String email , @RequestParam("office") String office, @RequestParam("duty") String duty){
        int adminId = Integer.parseInt(String.valueOf(request.getSession().getAttribute("adminId")));
        Admin admin = adminMapper.getAdminById(adminId);
        admin.setName(name);
        admin.setPhone(phone);
        admin.setEmail(email);
        admin.setOffice(office);
        admin.setDuty(duty);
        adminMapper.updateAdmin(admin);
        return 0;
    }

    @GetMapping("/adminLogout")
    public int adminLogout(){
        request.getSession().removeAttribute("adminId");
        return 0;
    }



}
