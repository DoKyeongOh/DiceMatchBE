package com.odk.pjt.dicematchbe.controller;

import com.odk.pjt.dicematchbe.account.BasicAccountDTO;
import com.odk.pjt.dicematchbe.account.basic.BasicAccountService;
import com.odk.pjt.dicematchbe.exception.DiceMatchException;
import com.odk.pjt.dicematchbe.util.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AccountController {

    @Value("${app.token.secret-key}")
    private static String secretKey;

    @Value("${app.token.expire-seconds}")
    private static long tokenDuration;

    private final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private final BasicAccountService basicAccountService;

    @Autowired
    public AccountController(BasicAccountService basicAccountService) {
        this.basicAccountService = basicAccountService;
    }

    @PostMapping("login/basic")
    public String loginWithBasicAccount(@RequestBody BasicAccountDTO dto) throws DiceMatchException {
        // 존재하면 JWT 발급
        // Factory에서 발급하도록
        String jwtKey = JWTUtil.createJWT("user id", secretKey, tokenDuration);

        // 쿠키에 넣어주기

        // user id 리턴
        return "user id";
    }

    @PostMapping("register/basic")
    @Transactional
    public String registerBasicAccount(@RequestBody BasicAccountDTO dto) {
        try {
            basicAccountService.register(dto);
            logger.info("basic account registered: {}", dto.getIdentity());
            return "success";
        } catch (Exception e) {
            return "fail";
        }
    }

}
