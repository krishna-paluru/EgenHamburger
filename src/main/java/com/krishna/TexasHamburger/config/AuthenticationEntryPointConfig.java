//package com.krishna.TexasHamburger.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//@Configuration
//public class AuthenticationEntryPointConfig extends BasicAuthenticationEntryPoint {
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
//        response.addHeader("WWW-Authenticate","Basic"+getRealmName());
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        PrintWriter writer = response.getWriter();
//        writer.println("HTTP Status 401 - "+ authException.getMessage());
//    }
//
//    @Override
//    public void afterPropertiesSet() {
//        setRealmName("TexasHamburger");
//        super.afterPropertiesSet();
//    }
//}
