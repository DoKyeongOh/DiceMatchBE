package com.odk.pjt.dicematchbe.account;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicAccountDTO {
    private String identity;
    private String password;
}
