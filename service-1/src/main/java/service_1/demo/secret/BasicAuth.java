package service_1.demo.secret;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.OncePerRequestFilter;
import service_1.demo.service.AuthService;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BasicAuth extends OncePerRequestFilter {
    @Autowired
    AuthService authService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        UserDetails userDetails = getUserDetails(httpServletRequest);
        if (userDetails != null) {
            if (userDetails.isEnabled()
                    && userDetails.isAccountNonExpired()
                    && userDetails.isAccountNonLocked()
                    && userDetails.isCredentialsNonExpired()) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }


    public UserDetails getUserDetails(HttpServletRequest httpServletRequest) {
        try {
            String tokenClient = httpServletRequest.getHeader("Authorization");
            if (tokenClient != null) {
               if (tokenClient.startsWith("Basic ")) {
                    String[] userNameAndPassword = getUserNameAndPassword(tokenClient);
                    UserDetails userDetails = authService.loadUserByUsername(userNameAndPassword[0]);
                    if (userDetails != null && passwordEncoder.matches(userNameAndPassword[1], userDetails.getPassword())) {
                        return userDetails;
                    }
                }
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    public String[] getUserNameAndPassword(String token) {
        String base64Credentials = token.substring("Basic".length()).trim();
        byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(credDecoded, StandardCharsets.UTF_8);
        return credentials.split(":", 2);
    }

}
