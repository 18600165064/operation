package com.data.display.util.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.data.display.service.CustomUserService;
import com.data.display.service.MyFilterSecurityInterceptor;
/**
 *
 * 功能描述: 自定义登录验证
 * @author CYN
 * @date 2019/1/15 9:50
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;
    @Autowired
    private CustomUserService customUserService;
    @Autowired
    private MyAuthenctiationSuccessHandler myAuthenctiationSuccessHandler;
    @Autowired
    private MyAuthenctiationFailureHandler myAuthenctiationFailureHandler;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //user Details Service验证
        auth.userDetailsService(customUserService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated() //任何请求,登录后可以访问
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/api/login")
                .passwordParameter("password")
                .usernameParameter("username")
                .successHandler(myAuthenctiationSuccessHandler)
                .failureHandler(myAuthenctiationFailureHandler)
                .permitAll();//登录页面用户任意访问
                //注销
                http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET"))
                .logoutSuccessUrl("/login")
                //指定是否在注销时让HttpSession无效
                .invalidateHttpSession(true).permitAll();
        http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
//        http.csrf().ignoringAntMatchers("/ueditor/dispather");
//        http.csrf().ignoringAntMatchers("/delivery/notify");
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
    	
        web.ignoring().antMatchers("/assets/**");
        web.ignoring().antMatchers("/photo/**");
        web.ignoring().antMatchers("/delivery/**");
    }
}