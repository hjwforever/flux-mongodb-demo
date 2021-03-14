package com.aruoxi.fluxmongodbdemo.controller;

import com.aruoxi.fluxmongodbdemo.entity.User;
import com.aruoxi.fluxmongodbdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.Duration;

/**
 * Copyright(C), 2020-2021, aruoxi.com
 * FileName: UserController
 * Author: hjwforever
 * Date: 2021/3/14 001414:37
 * Description:
 */
@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    /**
     * 获取所有用户
     *
     * @return
     */
    @GetMapping("/list")
    public Flux<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/listdelay", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<User> getAllDelay() {
        return userRepository.findAll().delayElements(Duration.ofSeconds(1));
    }

    /**
     * 获取单个用户
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<User>> getUser(@PathVariable String id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * 创建用户
     *
     * @param user
     * @return
     */
    @PostMapping("")
    public Mono<User> addUser(User user) {
        return userRepository.save(user);
    }

    /**
     * 修改用户
     *
     * @param id
     * @param user
     * @return
     */
    @PutMapping("/{id}")
    public Mono updateUser(@PathVariable(value = "id") String id, @Valid User user) {
        return userRepository.findById(id)
                .flatMap(existingUser -> {
                    existingUser.setName(user.getName());
                    existingUser.setAge(user.getAge());
                    return userRepository.save(existingUser);
                })
                .map(updateUser -> new ResponseEntity<>(updateUser, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable(value = "id") String id) {
        return userRepository.findById(id)
                .flatMap(existingUser ->
                    userRepository.delete(existingUser)
                            .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}