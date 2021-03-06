package com.software.rateit.controllers;

import com.software.rateit.Entity.User;
import com.software.rateit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    Iterable<User> getAllUsers() {
        return repository.findAll();
    }

    @GetMapping("/users/{id}")
    User getUsersById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CouldNotFindException(id));
    }
    @GetMapping("/UserByNick")
    public User findUserByNick(
            @RequestParam("nick") String nick) {
        if(nick != null)
            return repository.findByNick(nick);
        else
            throw new CouldNotFindException(nick);
    }
    @GetMapping("/UserByEmail")
    public User findUserByEmail(
            @RequestParam("email") String email) {
        if(email != null)
            return repository.findByEmail(email);
        else
            throw new CouldNotFindException(email);
    }

    @PostMapping("/users")
    User newUsers(@RequestBody User newUser){
        return repository.save(newUser);
    }

    @PutMapping("/users/{id}")
    User replaceUsers(@RequestBody User newUser, @PathVariable Long id){
        return repository.findById(id)
                .map(user -> {
                    user.setBadges(newUser.getBadges());
                   // user.setCd(newUser.getCd());
                    user.setEmail(newUser.getEmail());
                    user.setNick(newUser.getNick());
                    user.setPassword(newUser.getPassword());
                    user.setScore(newUser.getScore());
                    return repository.save(user);
                })
                .orElseGet(() ->{
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    @DeleteMapping("/users/{id}")
    void deleteUsers(@PathVariable Long id){
        repository.deleteById(id);
    }


}
