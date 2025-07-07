package com.odk.pjt.dicematchbe.account.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicAccountDTO {
    @NotBlank
    private String identity;
    @NotBlank
    private String password;
}
