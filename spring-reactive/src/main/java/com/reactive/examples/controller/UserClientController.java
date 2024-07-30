package com.reactive.examples.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.reactive.examples.client.UserClient;
import com.reactive.examples.model.User;

import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/client/users")
public class UserClientController {

    @Autowired
    private UserClient userClient;

    @GetMapping("/{userId}")
    public Mono<ResponseEntity<User>> getUserById(@PathVariable String userId){
        Mono<User> user = userClient.getUser(userId);
        return user.map( u -> ResponseEntity.ok(u))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Flux<User> getAllUsers(){
        return userClient.getAllUsers().onBackpressureBuffer(10, BufferOverflowStrategy.DROP_OLDEST)
            .delayElements(Duration.ofMillis(100))
            .log();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> create(@RequestBody User user){
        return userClient.createUser(user);
    }


}
