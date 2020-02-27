package br.com.softplan.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import br.com.softplan.api.dao.UsuarioRepository;
import br.com.softplan.api.domain.Usuario;
import br.com.softplan.api.util.Md5Encode;

@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

	@Autowired
	private UsuarioRepository usuarioRepository ;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.userDetailsService(userDetailsService()).authorizeRequests()
        .antMatchers("/api-syscad/pessoa/auth").permitAll()
        .antMatchers("/api-syscad/pessoa/adicionar").hasRole("ADMIN")
        .antMatchers("/api-syscad/pessoa/atualizar/*").hasRole("ADMIN")
        .antMatchers("/api-syscad/pessoa/deletar/*").hasRole("ADMIN")
        .antMatchers("/api-syscad/pessoa/listar_todos").hasRole("ADMIN")
        .anyRequest().authenticated().and().httpBasic().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		
		
	}
	
	 @Override
	   protected UserDetailsService userDetailsService() {
		 
		 List<Usuario> usuarios = usuarioRepository.findAll();
		 String senha;
		 Md5Encode md5 = new Md5Encode();
		 
		 List<UserDetails> users = new ArrayList<>();
		 
		 for (Usuario u: usuarios){
			 senha = md5.md5(u.getSenha());
	      UserDetails user = new User(u.getNome(), senha, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_"+u.getPapel()));
	      users.add(user);
		 }
	      
	      return new InMemoryUserDetailsManager(users);
	   }
}
