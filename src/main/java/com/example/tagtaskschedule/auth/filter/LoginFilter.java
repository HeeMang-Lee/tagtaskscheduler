package com.example.tagtaskschedule.auth.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

/**
 * 세션 기반 로그인 필텉
 * 인증되지 않은 사용자의 요청을 차단합니다.
 * 허용된 경로(화이트리스트)는 필터를 통과시킵니다.
 */
@Slf4j
public class LoginFilter implements Filter {

    /**
     * 인증이 필요 없는 URL 목록
     */
    private static final String[] WHITE_LIST = {"/", "/user/signup", "/api/auth/login", "api/auth/logout", "/api/authors"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpServletRequest.getRequestURI();
        log.info("LoginFilter 실행 - 요청 URI : {}", requestURI);

        if (!isWhiteList(requestURI)) {
            HttpSession session = httpServletRequest.getSession();

            if (session == null || session.getAttribute("LOGIN_AUTHOR") == null) {
                throw new RuntimeException("로그인이 필요합니다.");
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * 요청 URI가 화이트리스트에 포함되는지 검사합니다.
     *
     * @param uri 요청 URI
     * @return 포함되면 TRUE
     */
    private boolean isWhiteList(String uri) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, uri);
    }
}
