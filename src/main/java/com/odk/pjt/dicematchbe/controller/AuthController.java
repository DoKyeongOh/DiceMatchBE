package com.odk.pjt.dicematchbe.controller;

import com.odk.pjt.dicematchbe.account.dto.BasicAccountDTO;
import com.odk.pjt.dicematchbe.exception.DiceMatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController() {

    }

    @PostMapping("basic")
    public String login(BasicAccountDTO dto) throws DiceMatchException {
        logger.info("basic account {}", dto);
        return "success";
    }

    @PostMapping("google")
    public String login(Map<String, Object> body) throws DiceMatchException {
        for (Map.Entry<String, Object> entry : body.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        return "success";
    }

}
