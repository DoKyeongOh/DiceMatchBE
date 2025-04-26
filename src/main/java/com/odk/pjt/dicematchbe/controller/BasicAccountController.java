package com.odk.pjt.dicematchbe.controller;

import com.odk.pjt.dicematchbe.account.dto.UpdateAccountRequest;
import com.odk.pjt.dicematchbe.account.dto.BasicAccountDto;
import com.odk.pjt.dicematchbe.account.basic.BasicAccountService;
import com.odk.pjt.dicematchbe.auth.SessionManagementService;
import com.odk.pjt.dicematchbe.exception.DiceMatchException;
import com.odk.pjt.dicematchbe.exception.EntityNotFoundException;
import com.odk.pjt.dicematchbe.user.User;
import com.odk.pjt.dicematchbe.user.UserDto;
import com.odk.pjt.dicematchbe.user.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("account/basic")
public class BasicAccountController {
    private static final Logger logger = LoggerFactory.getLogger(BasicAccountController.class);

    private final BasicAccountService basicAuthService;
    private final SessionManagementService sessionManagementService;
    private final UserService userService;

    @Autowired
    public BasicAccountController(BasicAccountService accountService, SessionManagementService sessionService,
                                  UserService userService) {
        this.basicAuthService = accountService;
        this.sessionManagementService = sessionService;
        this.userService = userService;
    }

    @PostMapping
    @Transactional
    public UserDto register(@RequestBody BasicAccountDto dto) throws DiceMatchException {
        String userId = basicAuthService.register(dto).getUserId();
        User user = userService.getUser(userId).orElse(null);
        logger.info("basic account registered: {}", dto.getIdentity());

        return UserDto.from(user);
    }

    @PostMapping("login")
    public UserDto login(BasicAccountDto dto, HttpServletResponse response) throws Exception {
        String userId = basicAuthService.getBasicAccount(dto)
                .orElseThrow(() -> new EntityNotFoundException(dto.getIdentity())).getUserId();

        response.addCookie(sessionManagementService.login(userId));

        User user = userService.getUser(userId).orElse(null);
        logger.info("user login - {}", userId);

        return UserDto.from(user);
    }

    @PutMapping
    public String updateUserId(UpdateAccountRequest request) throws DiceMatchException {
        return basicAuthService.updateUserIdMapping(request).getUserId();
    }

    @PostMapping("logout")
    public String logout(@CookieValue("jwtToken") String jwtToken, HttpServletResponse response) throws Exception {
        response.addCookie(sessionManagementService.logout(jwtToken));
        return "success";
    }

}
