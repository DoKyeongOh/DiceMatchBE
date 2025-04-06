package com.odk.pjt.dicematchbe.account.google;

import com.odk.pjt.dicematchbe.account.Account;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity(name = "google_account")
public class GoogleAccount extends Account {

    @Column(name = "email")
    private String email;
}
