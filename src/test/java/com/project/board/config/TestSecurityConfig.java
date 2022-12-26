package com.project.board.config;

import com.project.board.domain.UserAccount;
import com.project.board.dto.UserAccountDto;
import com.project.board.repository.UserAccountRepository;
import com.project.board.service.UserAccountService;
import org.mockito.ArgumentMatchers;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

@Import(SecurityConfig.class)
public class TestSecurityConfig {

    @MockBean
    private UserAccountService userAccountService;

    @BeforeTestMethod
    public void securitySetup() {
        given(userAccountService.searchUser(ArgumentMatchers.anyString()))
                .willReturn(Optional.of(createUserAccountDto()));
        given(userAccountService.saveUser(ArgumentMatchers.anyString(),ArgumentMatchers.anyString(),ArgumentMatchers.anyString(),ArgumentMatchers.anyString(),ArgumentMatchers.anyString()))
                .willReturn(createUserAccountDto());
    }

    private UserAccountDto createUserAccountDto(){
        return UserAccountDto.of(
                "wndusTest",
                "pw",
                "wndus-test@email.com",
                "wndus-test",
                "test memo"
        );
    }
}