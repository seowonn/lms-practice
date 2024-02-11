package com.zerobase.fastlms.configuration;

import com.zerobase.fastlms.member.entity.LoginHistory;
import com.zerobase.fastlms.member.repository.LoginHistoryRepository;
import com.zerobase.fastlms.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class UserAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final LoginHistoryRepository loginHistoryRepository;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {
        String msg = "로그인 되었습니다.";

        if (authentication instanceof InternalAuthenticationServiceException) {
            msg = ((InternalAuthenticationServiceException) authentication).getMessage();
        }

        request.setAttribute("message", msg);
        System.out.println("로그인에 성공하였습니다.");

        String accessIp = RequestUtils.getClientIP(request);
        String userAgent = RequestUtils.getUserAgent(request);

        LoginHistory loginHistory = LoginHistory.builder()
                .userId(authentication.getName())
                .logDt(LocalDateTime.now())
                .accessIp(accessIp)
                .userAgent(userAgent)
                .build();

        loginHistoryRepository.save(loginHistory);

        super.onAuthenticationSuccess(request, response, authentication);
    }

}
