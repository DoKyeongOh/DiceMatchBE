package com.odk.pjt.dicematchbe.account;

import com.odk.pjt.dicematchbe.account.dto.BasicAccountDTO;
import com.odk.pjt.dicematchbe.account.basic.BasicAccountService;
import com.odk.pjt.dicematchbe.auth.SessionManagementService;
import com.odk.pjt.dicematchbe.exception.EntityNotFoundException;
import com.odk.pjt.dicematchbe.user.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

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
    public String register(@RequestBody BasicAccountDTO dto) throws NoSuchAlgorithmException {
        return basicAuthService.register(dto).getUserId();
    }

    @PostMapping("login")
    public String login(@RequestBody BasicAccountDTO dto, HttpServletResponse response) throws Exception {
        Account account = basicAuthService.getAccount(dto)
                .orElseThrow(() -> new EntityNotFoundException(dto.getIdentity()));

        response.addCookie(sessionManagementService.login(account.getUserId()));
        logger.info("user login with account, user: {}, {}", account.getAccountId(), account.getUserId());

        return account.getUserId();
    }

    @PostMapping("logout")
    public String logout(@CookieValue(LOGIN_COOKIE_NAME) String jwtToken,
                         HttpServletResponse response) throws Exception {
        response.addCookie(sessionManagementService.logout(jwtToken));
        return "success";
    }

}
