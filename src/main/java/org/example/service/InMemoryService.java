package org.example.service;

import org.example.Interface.UserService;
import org.example.model.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class InMemoryService implements UserService {
    private List<User> userList = new ArrayList<>();

    @Override
    public List<User> insert(User user) {
        userList.add(user);
        return userList;
    }

    @Override
    public void update(User user) {
        ListIterator<User> userListIterator = userList.listIterator();
        while (userListIterator.hasNext()) {
            User existingUser = userListIterator.next();
            if (existingUser.getId() == user.getId()) {
                existingUser.setName(user.getName());
                existingUser.setGender(user.getGender());
                existingUser.setAddressId(user.getAddressId());
                existingUser.setDob(user.getDob());
            } else {
                System.out.println("User not found");
            }
        }
    }

    @Override
    public List<User> listUsers() {
        return userList;
    }

    @Override
    public void delete(int id) {
        Iterator<User> userIterator = userList.iterator();
        while (userIterator.hasNext()) {
            User userObject = userIterator.next();
            if (userObject.getId() == id) {
                userIterator.remove();
            }
        }
    }

    @Override
    public User search(int searchId) {
        for (User user : userList) {
            if (user.getId() == searchId) {
                return user;
            }
        }
        return null;
    }
}
