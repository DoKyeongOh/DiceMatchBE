package com.odk.pjt.dicematchbe.account.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAccountRequest {
    @NotBlank
    private String accountId;
    @NotBlank
    private String userId;
}
