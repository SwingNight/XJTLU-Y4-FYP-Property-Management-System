package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.Admin;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    HttpServletRequest request;

    @GetMapping("/getUserSession")
    public int getAdminSession(){
        HttpSession session = request.getSession();
        if(request.getSession().getAttribute("userId")==null){
            return 0;
        }else {
            return 1;
        }
    }

    @GetMapping("/getUserList")
    public List<User> getUserList(){
        List<User> userList = userMapper.getUserList();
        return userList;
    }

    @PostMapping("/userLogin")
    public int userLogin(@RequestParam("account") String account , @RequestParam("password") String password){
        User user = userMapper.getUserByAccount(account);
        if (user == null){
            return 0;
        } else if (user.getPassword().equals(password)){
            HttpSession session = request.getSession();
            session.setAttribute("userId",user.getId());
            return 1;
        } else {
            return 2;
        }
    }

    @GetMapping("/getUserInfo")
    public JSONObject userInfo() {
        int userId = Integer.parseInt(String.valueOf(request.getSession().getAttribute("userId")));
        User user = userMapper.getUserById(userId);

        JSONObject json = new JSONObject();
        json.put("id", user.getId());
        json.put("account", user.getAccount());
        json.put("password", user.getPassword());
        json.put("name", user.getName());
        json.put("phone", user.getPhone());
        json.put("email", user.getEmail());
        json.put("relation", user.getRelation());
        return json;
    }

    @PostMapping("/userInfoChange")
    public int changeUserInfo(@RequestParam("name") String name , @RequestParam("phone") String phone, @RequestParam("email") String email , @RequestParam("relation") String relation){
        int userId = Integer.parseInt(String.valueOf(request.getSession().getAttribute("userId")));
        User user = userMapper.getUserById(userId);
        user.setName(name);
        user.setPhone(phone);
        user.setEmail(email);
        user.setRelation(relation);
        userMapper.updateUser(user);
        return 0;
    }

    @PostMapping("/userPasswordReset")
    public int resetuserPassword(@RequestParam("pwd1") String pwd1 , @RequestParam("pwd2") String pwd2, @RequestParam("pwd3") String pwd3){
        int userId = Integer.parseInt(String.valueOf(request.getSession().getAttribute("userId")));
        User user = userMapper.getUserById(userId);
        if(!Objects.equals(pwd1, user.getPassword())){
            return 1;
        }else {
            user.setPassword(pwd2);
            userMapper.updateUser(user);
            return 0;
        }
    }

    @GetMapping("/userLogout")
    public int userLogout(){
        request.getSession().removeAttribute("userId");
        return 0;
    }

    @PostMapping("/delUser")
    public int delUser(@RequestParam("id") int id){
        userMapper.deleteUser(id);
        return 0;
    }

    @PostMapping("/addUser")
    public int addUser(@RequestParam("acc") String acc, @RequestParam("pass") String pass){
        User checkUser = userMapper.getUserByAccount(acc);
        if(checkUser==null){
            User user = new User();
            user.setAccount(acc);
            user.setPassword(pass);
            userMapper.addUser(user);
            return 0;
        } else {
            return 1;
        }
    }

    @PostMapping("/queryUserList")
    public List<User> queryUserList(@RequestParam("acc") String acc){
        List<User> userList = userMapper.queryUserByAccount(acc);
        return userList;
    }

}
