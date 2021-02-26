# ALB-Cognito integration test

Simple java web application with spring security 3.1 authentication
for testing ALB-Cognito integration.

The application based on en example from the book
"Spring Security 3.1" by Robert Winch, Peter Mularien, chapter 3.

## Implementation

- CalendarUser class extended with `note` string property
  
- PreAuthFilter is added
    - authenticates Principal if `x-amzn-oidc-data` header is present in the HTTP request
    - includes list of request's headers to the Principal note property

- preAuthProvider is configured to use standard Spring Security PreAuthenticatedAuthenticationProvider
with our calendarUserDetailsService

- WelcomeController changed to provide CalendarUser.note property to the view
  - renders `index.jsp` for the root ('/') and '/tstcognito' paths 

- index.jsp displays provided note property

For the testing purpose https://calendar.test-on.me web site is used.

ALB listener is configured for `/tstcognito` path to pass through the Cognito authorizer
before forwarding to the application.
