package com.blog.config;

import com.blog.config.auth.CustomExpiredSessionStrategy;
import com.blog.config.auth.MyAuthenticationFailureHandler;
import com.blog.config.auth.MyAuthenticationSuccessHandler;
import com.blog.config.captcha.CaptchaFilter;
import com.blog.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author: Wenzhao
 * Date: 2021/8/1
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Resource
    MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Resource
    MyUserDetailsService myUserDetailsService;
    @Resource
    private DataSource datasource;
    @Resource
    private CaptchaFilter captchaFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class).csrf().disable()
            .logout().deleteCookies("JSESSIONID")
            .and().rememberMe()
                .tokenValiditySeconds(2*24*60*60)
                .tokenRepository(persistentTokenRepository())
            .and().formLogin()
                .loginPage("/login.html").permitAll()   //登录页面位置
                .loginProcessingUrl("/login").permitAll()  //登录访问路径
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)
            .and().authorizeRequests()
                .antMatchers("login.html","/login","/captcha","register.html","/register").permitAll()
                .anyRequest().access("@RBAC.hasPermission(request,authentication)")
//                .antMatchers("/main","/write","/about").hasAnyAuthority("ROLE_user","ROLE_admin")
//                .antMatchers("/admin").hasAuthority("/admin")
            .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .sessionFixation().migrateSession() //session保护
                .invalidSessionUrl("/login.html")
                .maximumSessions(1) //多端登录下线
                .maxSessionsPreventsLogin(false)
                .expiredSessionStrategy(new CustomExpiredSessionStrategy());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password(passwordEncoder().encode("123456"))
//                .roles("user")
//            .and()
//                .withUser("admin")
//                .password(passwordEncoder().encode("123456"))
//                .roles("admin")
//            .and()
//                .passwordEncoder(passwordEncoder());
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        //将项目中静态资源路径开放出来
        web.ignoring().antMatchers("/css/**","/icons/**","/js/**","/vendor/**","/fonts/**","/images/**","/upload/**","/fonts/**","/editormd/**");
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(datasource);
        return tokenRepository;
    }
}
