package it.ing.av.gdpr.gdprdashboard.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, proxyTargetClass = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Configuration
	@Order(1)
	public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

		private final Logger log = LoggerFactory.getLogger(this.getClass());

		// @Autowired
		// private UserRepository userRepository;

		// @Autowired
		// private Environment env;
		
//		@Autowired
//		FindByIndexNameSessionRepository<? extends Session> sessions;
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {

			// Comment to enable CSRF attacks
			http.csrf().disable();

			http.authorizeRequests().antMatchers("/css/**", "/js/**", "/img/**").permitAll();
			// http.authorizeRequests().antMatchers("/fileBase64").permitAll();

			http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
					.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.logoutSuccessUrl("/login").invalidateHttpSession(true);

		}

		// @Bean
		// public SessionRegistry sessionRegistry() {
		// SessionRegistry sessionRegistry = new SessionRegistryImpl();
		// return sessionRegistry;
		// }

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(this.userDetailsService());
		}

		@Bean
		@Override
		protected UserDetailsService userDetailsService() {
			return new UserDetailsService() {
				@Override
				public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

					log.info("> loadUserByUsername(username={})", username);
					PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
					// outputs
					// {bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG
					// remember the password that is printed out and use in the
					// next step
					log.info(" password encode = {}", encoder.encode("password"));
					UserDetails user = User.withUsername("user").password(encoder.encode("password")).roles("USER")
							.build();
					
//					sessions.createSession().setAttribute(
//							FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, username);
					
					// org.springframework.security.core.userdetails.User
					// userSecurity = null;
					// Set<GrantedAuthority> grantedAuthorities = new
					// HashSet<GrantedAuthority>();
					// userSecurity = new
					// org.springframework.security.core.userdetails.User("gdpr",
					// "gdpr",
					// grantedAuthorities);
					// grantedAuthorities.add(new
					// SimpleGrantedAuthority("ROLE_USER"));
					return user;
					// return loadUserByUsernameFromDb(username);
				}
			};
		};

	}

}
