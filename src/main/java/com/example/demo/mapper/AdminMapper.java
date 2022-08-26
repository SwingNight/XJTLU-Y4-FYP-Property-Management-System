package com.example.demo.mapper;

import com.example.demo.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface AdminMapper {
    List<Admin> getAdminList();
    Admin getAdminById(int id);
    Admin getAdminByAccount(String account);
    int addAdmin(Admin admin);
    int updateAdmin(Admin admin);
    int deleteAdmin(int id);
}