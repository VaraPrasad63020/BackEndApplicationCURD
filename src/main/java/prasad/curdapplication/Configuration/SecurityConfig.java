package prasad.curdapplication.Configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
		UserDetails  admin = User.withUsername("varaprasad")
				.password(encoder.encode("pwd"))
				.roles("admin")
				.build();
		UserDetails  user = User.withUsername("Lakshmi")
				.password(encoder.encode("pwd"))
				.roles("user")
				.build();
		return new InMemoryUserDetailsManager(user,admin);
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(
								authorize -> authorize.requestMatchers("/course").hasAnyAuthority("user")
								.requestMatchers("/course/**").authenticated()
								.requestMatchers("/course/**").hasAnyAuthority("admin")
								.anyRequest().authenticated()
								);
		return http.build();
	}

}

