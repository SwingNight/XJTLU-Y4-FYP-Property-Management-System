package com.example.demo.mapper;

import com.example.demo.pojo.Message;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface MessageMapper {
    List<Message> getMessageList();
    List<Message> getMessageListBySender(String sender);
    List<Message> getMessageListByReceiver(String receiver);
    Message getMessageById(int id);
    int addMessage(Message message);
}

