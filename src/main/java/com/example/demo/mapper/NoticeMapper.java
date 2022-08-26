package com.example.demo.mapper;

import com.example.demo.pojo.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NoticeMapper {
    List<Notice> getNoticeList();
    Notice getNoticeById(int id);
    int addNotice(Notice notice);
    int updateNotice(Notice notice);
    int deleteNotice(int id);
}
