package com.aruoxi.fluxmongodbdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright(C), 2020-2021, aruoxi.com
 * FileName: User
 * Author: hjwforever
 * Date: 2021/3/14 001414:35
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String name;
    private int age;
}
