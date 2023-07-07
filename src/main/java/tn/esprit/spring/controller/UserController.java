package tn.esprit.spring.controller;


import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.IUser;
import tn.esprit.spring.service.UserService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@CrossOrigin(maxAge = 3600)
@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    IUser iUser;
    UserService userService;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/current")
    public Optional<User> getCurrentUser(Authentication authentication){
        return userRepository.findByUsername(authentication.getName());
    }
    @GetMapping("/allUsers")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Transactional
    @PostMapping("/ajouterUser")
    User ajouterUser(@RequestBody User user){
        return iUser.ajouterUser(user);
    }


    @Transactional
    @PutMapping("updateUser/{id}")
    public User updateUser(@PathVariable(value = "id", required = false) final Long id, @RequestBody User user) {
        User result = userRepository.save(user);
        return result;
    }

    @PutMapping("/{userId}/block")
    public ResponseEntity<User> blockUser(@PathVariable Long userId) {
        User blockedUser = userService.blockUser(userId);
        return ResponseEntity.ok(blockedUser);
    }

    @PutMapping("/{userId}/unblock")
    public ResponseEntity<User> unblockUser(@PathVariable Long userId) {
        User unblockedUser = userService.unblockUser(userId);
        return ResponseEntity.ok(unblockedUser);
    }



    @Transactional
    @DeleteMapping("/removeuser/{id}")
    public void removefeedback(@PathVariable("id") long id) {

        iUser.removeuser(id);

    }





}
