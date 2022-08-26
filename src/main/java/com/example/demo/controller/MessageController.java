package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.mapper.MessageMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.Message;
import com.example.demo.pojo.Admin;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class MessageController {
    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    HttpServletRequest request;

    @GetMapping("/getMessageList")
    public List<Message> getMessageList(){
        List<Message> messageList = messageMapper.getMessageList();
        for(int i=0;i<messageList.size();i++){
            String content = messageList.get(i).getContent();
            messageList.get(i).setContent(HtmlUtils.htmlUnescape(content));
        }
        return messageList;
    }

    @PostMapping("/adminAddMessage")
    public int adminAddMessage(@RequestParam("receiver") String receiver,
                               @RequestParam("subject") String subject,
                               @RequestParam("content") String content){
        Admin checkAdminReceiver = adminMapper.getAdminByAccount(receiver);
        User checkUserReceiver = userMapper.getUserByAccount(receiver);
        if (checkAdminReceiver == null && checkUserReceiver == null){
            return 1;
        } else {
            Message message = new Message();

            int adminId = Integer.parseInt(String.valueOf(request.getSession().getAttribute("adminId")));
            Admin admin = adminMapper.getAdminById(adminId);
            String sender = admin.getAccount();
            message.setSender(sender);
            message.setReceiver(receiver);
            message.setSubject(subject);

            String temp = HtmlUtils.htmlEscapeHex(content);
            message.setContent(temp);

            Date date = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String time = dateFormat.format(date);
            message.setTime(time);

            messageMapper.addMessage(message);
            return 0;
        }
    }

    @PostMapping("/userAddMessage")
    public int userAddMessage(@RequestParam("receiver") String receiver,
                              @RequestParam("subject") String subject,
                              @RequestParam("content") String content){
        Admin checkAdminReceiver = adminMapper.getAdminByAccount(receiver);
        User checkUserReceiver = userMapper.getUserByAccount(receiver);
        if (checkAdminReceiver == null && checkUserReceiver == null){
            return 1;
        } else {
            Message message = new Message();

            int userId = Integer.parseInt(String.valueOf(request.getSession().getAttribute("userId")));
            User user = userMapper.getUserById(userId);
            String sender = user.getAccount();
            message.setSender(sender);
            message.setReceiver(receiver);
            message.setSubject(subject);

            String temp = HtmlUtils.htmlEscapeHex(content);
            message.setContent(temp);

            Date date = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String time = dateFormat.format(date);
            message.setTime(time);

            messageMapper.addMessage(message);
            return 0;
        }
    }

    @GetMapping("/adminGetMessageListByReceiver")
    public List<Message> adminGetMessageListByReceiver(){
        int adminId = Integer.parseInt(String.valueOf(request.getSession().getAttribute("adminId")));
        Admin admin = adminMapper.getAdminById(adminId);
        List<Message> messageList = messageMapper.getMessageListByReceiver(admin.getAccount());
        for(int i=0;i<messageList.size();i++){
            String content = messageList.get(i).getContent();
            messageList.get(i).setContent(HtmlUtils.htmlUnescape(content));
        }
        return messageList;
    }

    @GetMapping("/adminGetMessageListBySender")
    public List<Message> adminGetMessageListBySender(){
        int adminId = Integer.parseInt(String.valueOf(request.getSession().getAttribute("adminId")));
        Admin admin = adminMapper.getAdminById(adminId);
        List<Message> messageList = messageMapper.getMessageListBySender(admin.getAccount());
        for(int i=0;i<messageList.size();i++){
            String content = messageList.get(i).getContent();
            messageList.get(i).setContent(HtmlUtils.htmlUnescape(content));
        }
        return messageList;
    }

    @GetMapping("/userGetMessageListByReceiver")
    public List<Message> userGetMessageListByReceiver(){
        int userId = Integer.parseInt(String.valueOf(request.getSession().getAttribute("userId")));
        User user = userMapper.getUserById(userId);
        List<Message> messageList = messageMapper.getMessageListByReceiver(user.getAccount());
        for(int i=0;i<messageList.size();i++){
            String content = messageList.get(i).getContent();
            messageList.get(i).setContent(HtmlUtils.htmlUnescape(content));
        }
        return messageList;
    }

    @GetMapping("/userGetMessageListBySender")
    public List<Message> userGetMessageListBySender(){
        int userId = Integer.parseInt(String.valueOf(request.getSession().getAttribute("userId")));
        User user = userMapper.getUserById(userId);
        List<Message> messageList = messageMapper.getMessageListBySender(user.getAccount());
        for(int i=0;i<messageList.size();i++){
            String content = messageList.get(i).getContent();
            messageList.get(i).setContent(HtmlUtils.htmlUnescape(content));
        }
        return messageList;
    }




}
