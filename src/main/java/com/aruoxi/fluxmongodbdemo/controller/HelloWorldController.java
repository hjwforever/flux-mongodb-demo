package com.aruoxi.fluxmongodbdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Copyright(C), 2020-2021, aruoxi.com
 * FileName: HelloWorldController
 * Author: hjwforever
 * Date: 2021/3/14 001414:30
 * Description:
 */
@RestController
public class HelloWorldController {
    @GetMapping("/helloworld")
    public Mono<String> helloworld() {
        return Mono.just("This is WebFlux demo");
    }
}
