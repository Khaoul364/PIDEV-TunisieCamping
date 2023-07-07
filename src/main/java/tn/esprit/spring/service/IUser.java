package tn.esprit.spring.service;

import tn.esprit.spring.entity.User;

public interface IUser {
    User ajouterUser(User user);
    User updateUser (User user);
    User removeuser(Long  id);
}
