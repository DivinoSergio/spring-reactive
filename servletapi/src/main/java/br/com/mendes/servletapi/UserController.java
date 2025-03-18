package br.com.mendes.servletapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping
    User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping
    Iterable<User> list() {
        return userRepository.findAll();
    }
}
