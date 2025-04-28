package com.odk.pjt.dicematchbe.account.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicAccountDto {
    public String identity;
    public String password;
}
