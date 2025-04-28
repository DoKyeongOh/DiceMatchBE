package com.odk.pjt.dicematchbe.user;

import com.odk.pjt.dicematchbe.exception.BadEntityInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUser(String userId) {
        return userRepository.findById(userId);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User addNewUser() {
        User user = new User();
        user.userId = UUID.randomUUID().toString();
        return userRepository.save(user);
    }

    public User addUser(User user) throws BadEntityInputException {
        if (user.userId == null) {
            return userRepository.save(user);
        }

        throw new BadEntityInputException("addUser fail: "+user.userId);
    }

    public User editUser(User user) throws BadEntityInputException {
        if (user.userId != null) {
            return userRepository.save(user);
        }

        throw new BadEntityInputException("editUser fail: "+user.userId);
    }

    public String deleteUser(String userId) {
        userRepository.deleteById(userId);
        return userId;
    }

}
