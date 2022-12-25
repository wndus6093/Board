package com.project.board.config;

import com.project.board.domain.UserAccount;
import com.project.board.repository.UserAccountRepository;
import org.mockito.ArgumentMatchers;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

@Import(SecurityConfig.class)
public class TestSecurityConfig {

    @MockBean
    private UserAccountRepository userAccountRepository;

    @BeforeTestMethod
    public void securitySetup() {
        given(userAccountRepository.findById(ArgumentMatchers.anyString())).willReturn(Optional.of(UserAccount.of(
                "wndusTest",
                "pw",
                "wndus-test@email.com",
                "wndus-test",
                "test memo"
        )));
    }
}