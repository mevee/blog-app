package com.example.blog_webapp.config;

//@EnableWebSecurity
public class WebSecurity /*extends WebSecurityConfigurerAdapter*/ {

    private final String uri = "/custom/*";

  /*  @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().httpStrictTransportSecurity().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Authorize sub-folders permissions
        http.antMatcher(uri).authorizeRequests().anyRequest().permitAll();
    }*/
}
