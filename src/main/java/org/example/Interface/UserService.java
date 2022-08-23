package org.example.Interface;

import org.example.model.User;

import java.util.List;

public interface UserService {

    List<User> insert(User user);

    List<User> listUsers() ;

    void update(User user);

    void delete(int id);

    User search(int id);

}