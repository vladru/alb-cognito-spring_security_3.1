package com.packtpub.springsecurity.authentication;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.packtpub.springsecurity.domain.CalendarUser;

public final class DomainUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private final String domain;
    private final String note;

    public DomainUsernamePasswordAuthenticationToken(String principal, String credentials, String domain, String note) {
        super(principal, credentials);
        this.domain = domain;
        this.note = note;

    }

    public DomainUsernamePasswordAuthenticationToken(CalendarUser principal, String credentials, String domain,
            Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.domain = domain;
        this.note = principal.getNote();
    }

    public String getDomain() {
        return domain;
    }

    public String getNote() {
        return note;
    }

    private static final long serialVersionUID = -5138870746127783L;
}
