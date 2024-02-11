package com.zerobase.fastlms.configuration;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

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

        super.onAuthenticationSuccess(request, response, authentication);
    }

}
