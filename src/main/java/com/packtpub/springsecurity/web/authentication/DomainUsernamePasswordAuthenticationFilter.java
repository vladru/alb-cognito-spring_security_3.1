package com.packtpub.springsecurity.web.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.packtpub.springsecurity.authentication.DomainUsernamePasswordAuthenticationToken;

import java.util.Enumeration;

/**
 * An extension to the existing {@link UsernamePasswordAuthenticationFilter} that obtains a domain parameter and then
 * creates a {@link DomainUsernamePasswordAuthenticationToken}.
 *
 * @author Rob Winch
 *
 */
public final class DomainUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        String username;
        String password;
        String domain;
        String note;

        String oidcData = request.getHeader("x-amzn-oidc-data");

        Enumeration headerNames = request.getHeaderNames();

        if (headerNames != null) {
            oidcData = "";
            while (headerNames.hasMoreElements()) {
                String headerName = (String) headerNames.nextElement();
                oidcData += headerName + ": " + request.getHeader(headerName) + System.lineSeparator();
            }
        }

        if (oidcData != null) {
            username = "user1";
            password = "user1";
            domain = "example.com";
            note = oidcData;
        } else {
            if (!request.getMethod().equals("POST")) {
                throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
            }

            username = obtainUsername(request);
            password = obtainPassword(request);
            domain = request.getParameter("domain");
            note = "No x-amzn-oidc-data header found.";
        }

        DomainUsernamePasswordAuthenticationToken authRequest = new DomainUsernamePasswordAuthenticationToken(username,
                password, domain, note);

        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}