package com.example.tagtaskschedule.config;

import com.example.tagtaskschedule.filter.LoginFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 웹 필터 등록 설정 클래스
 */
@Configuration
public class WebConfig {

    /**
     * 로그인 필터 등록
     * 모든 요청에 대해 실행되며, 화이트리스트 제외 URL에만 인증 수행
     *
     * @return FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean<Filter> loginFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LoginFilter());
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
