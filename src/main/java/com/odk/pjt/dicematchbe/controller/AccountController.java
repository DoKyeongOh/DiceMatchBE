package com.odk.pjt.dicematchbe.controller;

import com.odk.pjt.dicematchbe.account.BasicAccountDTO;
import com.odk.pjt.dicematchbe.account.basic.BasicAccountService;
import com.odk.pjt.dicematchbe.exception.DiceMatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("account")
public class AccountController {

    private final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private final BasicAccountService basicAccountService;

    @Autowired
    public AccountController(BasicAccountService basicAccountService) {
        this.basicAccountService = basicAccountService;
    }

    @PostMapping("basic")
    @Transactional
    public String registerBasicAccount(@RequestBody BasicAccountDTO dto) throws DiceMatchException {
        basicAccountService.register(dto);
        logger.info("basic account registered: {}", dto.getIdentity());
        return "success";
    }

    @PutMapping("basic")
    public String updateUserIdMapping(String accountId, String userId) throws DiceMatchException {
        basicAccountService.updateUserIdMapping(accountId, userId);
        logger.info("basic account {} mapped to {}", accountId, userId);
        return "success";
    }

}
