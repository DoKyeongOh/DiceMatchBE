package com.odk.pjt.dicematchbe.controller.account;

import com.odk.pjt.dicematchbe.account.dto.AccountUserIdUpdateRequest;
import com.odk.pjt.dicematchbe.account.dto.BasicAccountDTO;
import com.odk.pjt.dicematchbe.account.basic.BasicAccountService;
import com.odk.pjt.dicematchbe.exception.DiceMatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("account/basic")
public class BasicAccountController {

    private final Logger logger = LoggerFactory.getLogger(BasicAccountController.class);

    private final BasicAccountService basicAccountService;

    @Autowired
    public BasicAccountController(BasicAccountService basicAccountService) {
        this.basicAccountService = basicAccountService;
    }

    @PostMapping
    @Transactional
    public String registerBasicAccount(@RequestBody BasicAccountDTO dto) throws DiceMatchException {
        basicAccountService.register(dto);
        logger.info("basic account registered: {}", dto.getIdentity());
        return "success";
    }

    @PutMapping
    public String updateBasicAccountUserId(AccountUserIdUpdateRequest request) throws DiceMatchException {
        basicAccountService.updateUserIdMapping(request);
        return "success";
    }
}
