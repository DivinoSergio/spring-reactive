package br.com.mendes.reactiveapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping
    Mono<User> create(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping
    Flux<User> list() {
        return userRepository.findAll();
    }
}
