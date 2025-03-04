package com.odk.pjt.dicematchbe.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String userId) {
        Optional<UserEntity> entity = userRepository.findById(userId);
        return entity.map(UserEntity::toModel).orElse(null);
    }

    public List<User> getUsers() {
        return userRepository.findAll().stream().map(UserEntity::toModel).collect(Collectors.toList());
    }

    public User addUser(User user) {
        return userRepository.save(new UserEntity(user)).toModel();
    }

    public User editUser(User user) {
        return userRepository.save(new UserEntity(user)).toModel();
    }

    public String deleteUser(String userId) {
        userRepository.deleteById(userId);
        return userId;
    }

}
