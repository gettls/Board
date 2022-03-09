package lab.board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lab.board.config.oauth.Oauth2PrincipalService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired Oauth2PrincipalService oauth2PrincipalService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/board/create").authenticated()
			.antMatchers("/board/edit").authenticated()
		.and()
			.formLogin()
			.loginPage("/member/login")
			.loginProcessingUrl("/member/login")
			.defaultSuccessUrl("/")
		.and()
			.logout()
			.logoutUrl("/member/logout")
			.logoutSuccessUrl("/member/login")
		.and()
			.oauth2Login()
			.loginPage("/member/login")
			.userInfoEndpoint()
			.userService(oauth2PrincipalService);
	}
}
