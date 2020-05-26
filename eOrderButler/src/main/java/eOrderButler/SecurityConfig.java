//package eOrderButler;
//
//import javax.sql.DataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//
//	@Autowired
//	private DataSource dataSource;
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//		.csrf().disable()
//		.formLogin()
//			.loginPage("/login")
//			.permitAll()
//		.and()
//		.authorizeRequests()
//		.antMatchers("/home").authenticated()
//		.antMatchers("/orderDetails").authenticated()
////		.antMatchers("/cart/**").hasAuthority("ROLE_USER")
////		.antMatchers("/get*/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
////		.antMatchers("/admin*/**").hasAuthority("ROLE_ADMIN")
//		.anyRequest().permitAll()
//		.and()
//		.logout()
//			.logoutUrl("/logout");
//	}
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//			.inMemoryAuthentication().withUser("789@789.com").password("789");
//	
//		auth
//			.jdbcAuthentication()
//			.dataSource(dataSource)
//			.usersByUsernameQuery("SELECT userId, password FROM User WHERE userId=?")
//			.authoritiesByUsernameQuery("SELECT userId, password FROM authorities WHERE userId=?");
//	}
//	
//	@Bean
//	public static NoOpPasswordEncoder  passwordEncoder() {
//		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//	}
//}
