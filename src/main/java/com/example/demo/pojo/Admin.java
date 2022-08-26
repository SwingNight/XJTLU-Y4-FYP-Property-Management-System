package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    private int id;
    private String account;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String office;
    private String duty;
}
