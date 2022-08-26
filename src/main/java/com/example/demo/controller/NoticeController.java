package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.mapper.NoticeMapper;
import com.example.demo.pojo.Notice;
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
public class NoticeController {
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    HttpServletRequest request;

    @GetMapping("/getNoticeList")
    public List<Notice> getAllNotice(){
        List<Notice> noticeList = noticeMapper.getNoticeList();
        for(int i=0;i<noticeList.size();i++){
            String content = noticeList.get(i).getContent();
            noticeList.get(i).setContent(HtmlUtils.htmlUnescape(content));
        }

        return noticeList;
    }

    @PostMapping("/adminAddArticle")
    public int addNotice(@RequestParam("title") String title, @RequestParam("category") String category, @RequestParam("content") String content){
        Notice notice = new Notice();
        notice.setTitle(title);

        String temp = HtmlUtils.htmlEscapeHex(content);
        notice.setContent(temp);

        int adminId = Integer.parseInt(String.valueOf(request.getSession().getAttribute("adminId")));
        Admin admin = adminMapper.getAdminById(adminId);
        String author = admin.getAccount();
        notice.setAuthor(author);

        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = dateFormat.format(date);
        notice.setTime(time);

        notice.setCategory(category);

        noticeMapper.addNotice(notice);
        return 0;
    }

    @PostMapping("/adminDelNotice")
    public int delNotice(@RequestParam("id") int id){
        noticeMapper.deleteNotice(id);
        return 0;
    }
}
