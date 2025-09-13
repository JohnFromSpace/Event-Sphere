package bg.sofia.uni.fmi.piss.project.eventsphere;

import bg.sofia.uni.fmi.piss.project.eventsphere.jwt.JWTAuthenticationFilter;
import bg.sofia.uni.fmi.piss.project.eventsphere.jwt.JWTTokenVerifier;
import bg.sofia.uni.fmi.piss.project.eventsphere.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private DetailsService detailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
        http
            .cors().and()
            .csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilter(new JWTAuthenticationFilter(authManager))
            .addFilterAfter(new JWTTokenVerifier(), JWTAuthenticationFilter.class)
            .authorizeRequests()
            .requestMatchers("/registrationForm", "/css/**", "/images/**", "/js/**").permitAll()
            .requestMatchers("/index", "/main", "/events/**", "/settings", "/loginForm").permitAll()
            .requestMatchers("/user/**", "/userProfile", "/users", "/file/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/index")
            .permitAll()
            .and()
            .logout()
            .permitAll();

        return http.build();
    }

    @Bean(name = "multipartResolver")
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
}
