package com.odk.pjt.dicematchbe.user;

import com.odk.pjt.dicematchbe.exception.BadEntityInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{userId}")
    public User getUser(@PathVariable("userId") String userId) {
        return userService.getUser(userId).orElse(null);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    // 이거는 뭔가 jwt에 accountId, userId같은걸 복합적으로 갖고있게하고, 메서드에서 어노테이션을 붙히는것만으로 그 값을 불러올 수 있도록 해야할듯
    @PutMapping
    public User updateUser(@RequestBody User user) throws BadEntityInputException {
        return userService.updateUser(user);
    }

    @DeleteMapping("{userId}")
    public String deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
        return userId;
    }

}
