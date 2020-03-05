package br.com.softplan.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("rodrigo").password("user").roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.authorizeRequests().anyRequest().authenticated().and().httpBasic().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.cors().and().authorizeRequests()
            .antMatchers("/api-syscad/pessoa/auth").permitAll()
            .antMatchers("/api-syscad/pessoa/source").permitAll()
            .antMatchers("/api-syscad/pessoa/adicionar").hasRole("ADMIN")
            .antMatchers("/api-syscad/pessoa/atualizar/*").hasRole("ADMIN")
            .antMatchers("/api-syscad/pessoa/deletar/*").hasRole("ADMIN")
            .antMatchers("/api-syscad/pessoa/listar_todos").hasRole("ADMIN")
            .anyRequest().authenticated().and().httpBasic().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.csrf().disable();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
