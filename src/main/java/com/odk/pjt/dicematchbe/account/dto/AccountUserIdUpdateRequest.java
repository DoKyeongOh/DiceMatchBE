package com.odk.pjt.dicematchbe.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountUserIdUpdateRequest {
    private String accountId;
    private String userId;
}
