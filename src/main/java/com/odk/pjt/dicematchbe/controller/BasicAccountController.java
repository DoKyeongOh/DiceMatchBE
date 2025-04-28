package com.odk.pjt.dicematchbe.controller;

import com.odk.pjt.dicematchbe.account.basic.BasicAccount;
import com.odk.pjt.dicematchbe.account.dto.UpdateAccountRequest;
import com.odk.pjt.dicematchbe.account.dto.BasicAccountDto;
import com.odk.pjt.dicematchbe.account.basic.BasicAccountService;
import com.odk.pjt.dicematchbe.auth.SessionManagementService;
import com.odk.pjt.dicematchbe.exception.DiceMatchException;
import com.odk.pjt.dicematchbe.exception.EntityNotFoundException;
import com.odk.pjt.dicematchbe.user.User;
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
    private final String LOGIN_COOKIE_NAME = "jwtToken";
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
    public String register(@RequestBody BasicAccountDto dto) throws DiceMatchException {
        BasicAccount account = basicAuthService.register(dto);
        User user = userService.addNewUser();

        basicAuthService.updateUserIdMapping(account.accountId, user.userId);
        logger.info("basic account registered: {}", dto.getIdentity());

        return account.userId;
    }

    @PostMapping("login")
    public String login(@RequestBody BasicAccountDto dto, HttpServletResponse response) throws Exception {
        BasicAccount account = basicAuthService.getBasicAccount(dto)
                .orElseThrow(() -> new EntityNotFoundException(dto.getIdentity()));

        response.addCookie(sessionManagementService.login(account.userId));
        logger.info("user login with account, user: {}, {}", account.accountId, account.userId);

        return account.userId;
    }

    @PutMapping
    public String updateUserId(UpdateAccountRequest request) throws DiceMatchException {
        return basicAuthService.updateUserIdMapping(request.getAccountId(), request.getUserId()).userId;
    }

    @PostMapping("logout")
    public String logout(@CookieValue(LOGIN_COOKIE_NAME) String jwtToken,
                         HttpServletResponse response) throws Exception {
        response.addCookie(sessionManagementService.logout(jwtToken));
        return "success";
    }

}
