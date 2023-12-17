package org.delivery.api.account;

import lombok.RequiredArgsConstructor;
import org.delivery.api.account.model.AccountMeResponse;
import org.delivery.db.account.AccountEntity;
import org.delivery.db.account.AccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountAoiController {

    private final AccountRepository accountRepository;

    @GetMapping("me")
    public AccountMeResponse save(){
        return AccountMeResponse.builder()
                .name("고승조")
                .email("rhtmdwh@naver.com")
                .registeredAt(LocalDateTime.now())
                .build();
    }
}
