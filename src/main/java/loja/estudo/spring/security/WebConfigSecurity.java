package loja.estudo.spring.security;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebConfigSecurity extends WebSecurityConfigurerAdapter implements HttpSessionIdListener{

	@Override
	public void sessionIdChanged(HttpSessionEvent se, String oldSessionId) {
		
	}
	
	/*Para liberar end-points*/
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers(HttpMethod.GET, "/salvarAcesso", "/deleteAcesso", "/obterAcesso/{id}", "/buscarPorDesc/{desc}")
		.antMatchers(HttpMethod.POST, "/salvarAcesso", "/deleteAcesso")
		.antMatchers(HttpMethod.DELETE, "/deleteAcessoPorId/{id}");
	}

}