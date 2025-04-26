package com.odk.pjt.dicematchbe.controller;

import com.odk.pjt.dicematchbe.account.dto.UpdateAccountRequest;
import com.odk.pjt.dicematchbe.account.google.GoogleAccountService;
import com.odk.pjt.dicematchbe.exception.DiceMatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("account/google")
public class GoogleAccountController {

    private final GoogleAccountService googleAccountService;

    @Autowired
    public GoogleAccountController(GoogleAccountService googleAccountService) {
        this.googleAccountService = googleAccountService;
    }

    @PostMapping
    @Transactional
    public String registerOrLogin(Map<String, Object> body) {
        googleAccountService.register("email");
        return "success";
    }

    @PutMapping
    public String updateGoogleAccountUserId(UpdateAccountRequest request) throws DiceMatchException {
        googleAccountService.updateUserIdMapping(request);
        return "success";
    }
}
