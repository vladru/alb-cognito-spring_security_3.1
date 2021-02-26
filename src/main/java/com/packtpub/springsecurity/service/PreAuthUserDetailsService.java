package com.packtpub.springsecurity.service;

import com.packtpub.springsecurity.core.userdetails.CalendarUserDetailsService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.Assert;

public class PreAuthUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken>, InitializingBean
{
    private CalendarUserDetailsService calendarUserDetailsService;

    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
        // UserDetails user = calendarUserDetailsService.loadUserByUsername(token.getName());
        return (UserDetails) token.getPrincipal();
    }


    public void setCalendarUserDetailsService(CalendarUserDetailsService calendarUserDetailsService) {
        this.calendarUserDetailsService = calendarUserDetailsService;
    }

    public void afterPropertiesSet() {
        Assert.notNull(calendarUserDetailsService, "calendarUserDetailsService must be set");
    }
}
