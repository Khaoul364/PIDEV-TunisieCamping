package tn.esprit.spring.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService implements IUser{

    UserRepository userRepository;
    @Override
    public User ajouterUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        if (user.getPassword() != null) {
            return userRepository.save(user);

        }


        return user;
    }
    @Override
    public User removeuser(Long id) {

        userRepository.deleteById(id);
        return null;
    }


    public User blockUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setBlocked(true);

        return userRepository.save(user);
    }

    public User unblockUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setBlocked(false);

        return userRepository.save(user);
    }


}

