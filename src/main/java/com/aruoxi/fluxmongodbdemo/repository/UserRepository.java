package com.aruoxi.fluxmongodbdemo.repository;

import com.aruoxi.fluxmongodbdemo.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Copyright(C), 2020-2021, aruoxi.com
 * FileName: UserRepository
 * Author: hjwforever
 * Date: 2021/3/14 001421:51
 * Description:
 */
public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
