package com.odk.pjt.dicematchbe.controller;

import com.odk.pjt.dicematchbe.account.Account;
import com.odk.pjt.dicematchbe.account.AccountService;
import com.odk.pjt.dicematchbe.account.AccountType;
import com.odk.pjt.dicematchbe.account.data.google.GoogleAccountData;
import com.odk.pjt.dicematchbe.account.data.google.GoogleAccountDataService;
import com.odk.pjt.dicematchbe.exception.DiceMatchException;
import com.odk.pjt.dicematchbe.util.JWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoogleAuthController {

    @Value("${app.token.secret-key}")
    private static String secretKey;

    @Value("${app.token.expire-seconds}")
    private static long tokenDuration;

    private AccountService accountService;
    private GoogleAccountDataService accountDataService;

    @PostMapping
    @Transactional
    public String register(GoogleAccountData accountData) throws DiceMatchException {
        Account account = accountService.add(AccountType.GOOGLE);
        accountData.accountId = account.id;

        // add -> logging
        GoogleAccountData googleAccountData = accountDataService.register(accountData);
        return "success";
    }

    @GetMapping("google")
    public String login(String id, String password) throws DiceMatchException {
        if (id == null || password == null) {
            throw new DiceMatchException("login fail: null input");
        }

        // 존재하면 JWT 발급
        // Factory에서 발급하도록
        String jwtKey = JWTUtil.createJWT("user id", secretKey, tokenDuration);

        // 쿠키에 넣어주기

        // user id 리턴
        return "user id";
    }
}
