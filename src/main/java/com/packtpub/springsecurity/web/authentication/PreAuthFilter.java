package com.packtpub.springsecurity.web.authentication;

import com.packtpub.springsecurity.core.userdetails.CalendarUserDetailsService;
import com.packtpub.springsecurity.domain.CalendarUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class PreAuthFilter  extends AbstractPreAuthenticatedProcessingFilter {
    private CalendarUserDetailsService calendarUserDetailsService;

    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {

        String oidcData = request.getHeader("x-amzn-oidc-data");
        if (oidcData == null) {
            return null;
        }

        UserDetails userDetails = calendarUserDetailsService.loadUserByUsername("user1@example.com");

        Enumeration<String> headerNames = request.getHeaderNames();

        StringBuilder note = new StringBuilder();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                note.append(headerName).append(": ").append(request.getHeader(headerName)).append(System.lineSeparator());
            }
        }

        CalendarUser calendarUser = (CalendarUser)  userDetails;
        calendarUser.setNote(note.toString());
        return userDetails;
    }

    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return request.getHeader("x-amzn-oidc-data") != null ? "user1" : null;
    }

    public void setCalendarUserDetailsService(CalendarUserDetailsService calendarUserDetailsService) {
        this.calendarUserDetailsService = calendarUserDetailsService;
    }

    public void afterPropertiesSet() {
        Assert.notNull(calendarUserDetailsService, "calendarUserDetailsService must be set");
    }
}
