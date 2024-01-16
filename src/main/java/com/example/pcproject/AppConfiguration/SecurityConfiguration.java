package com.example.pcproject.AppConfiguration;

import com.example.pcproject.Repository.UserRepository;
import com.example.pcproject.Service.impl.PcShopUserService;
import com.example.pcproject.models.eunums.RoleType;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableMethodSecurity
public class  SecurityConfiguration {
    private final String rememberMeKey;

    public SecurityConfiguration(@Value("${pcproject.remember.me.key}")
                                 String rememberMeKey) {
        this.rememberMeKey = rememberMeKey;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(
                authorizeRequest -> authorizeRequest
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/", "/users/login", "/users/register", "/users/login-error").permitAll()
                        .requestMatchers("/service/service").permitAll()
                        .requestMatchers("/product/all", "/product/laptop", "/product/computer").permitAll()
                        .requestMatchers("/contact").permitAll()
                        .requestMatchers("/about", "/search").permitAll()
                        .requestMatchers("/api/send").permitAll()
                        .requestMatchers("/api/viewIp").permitAll()
                        .requestMatchers("/admin/user", "/admin/admin-panel", "/admin/viewIp",
                                "/admin/brandsAdd").hasRole(RoleType.ADMIN.name())
                        .anyRequest().authenticated()


        ).formLogin(
                formLogin -> {
                    formLogin
                            .loginPage("/users/login")
                            .usernameParameter("username")
                            .passwordParameter("password")
                            .authenticationDetailsSource(authenticationDetailsSource())
                            .defaultSuccessUrl("/", true)
                            .failureForwardUrl("/users/login-error");
                }
        ).logout(
                logout -> {
                    logout.logoutUrl("/users/logout")
                            .logoutSuccessUrl("/")
                            .invalidateHttpSession(true);

                }
        ).rememberMe(
                rememberMe -> {
                    rememberMe
                            .key(rememberMeKey)
                            .rememberMeParameter("remember-me")
                            .rememberMeCookieName("remember-me");
                }
        ).csrf(
                csfr -> {
                    csfr.ignoringRequestMatchers("/fonts/poppins/poppins-v5-latin-italic.ttf");
                    csfr.ignoringRequestMatchers(new AntPathRequestMatcher("/api/send/**"));
                }
        );


        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new PcShopUserService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

    @Bean
    public AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource() {
        return WebAuthenticationDetails::new;
    }
}
