package com.odk.pjt.dicematchbe.user;

import com.odk.pjt.dicematchbe.exception.BadEntityInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) throws BadEntityInputException {
        if (user.id == null) {
            return userRepository.save(user);
        }

        throw new BadEntityInputException("addUser fail: "+user.id);
    }

    public User editUser(User user) throws BadEntityInputException {
        if (user.id != null) {
            return userRepository.save(user);
        }

        throw new BadEntityInputException("editUser fail: "+user.id);
    }

    public String deleteUser(String userId) {
        userRepository.deleteById(userId);
        return userId;
    }

}
