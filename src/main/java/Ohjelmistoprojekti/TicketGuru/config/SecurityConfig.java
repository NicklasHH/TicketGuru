package Ohjelmistoprojekti.TicketGuru.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user1 = User.withUsername("user1").password(passwordEncoder().encode("pass1")).roles("USER")
				.build();
		UserDetails user2 = User.withUsername("user2").password(passwordEncoder().encode("pass2")).roles("USER")
				.build();
		UserDetails admin = User.withUsername("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN")
				.build();
		return new InMemoryUserDetailsManager(user1, user2, admin);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests(authorize -> {
			authorize.anyRequest().authenticated(); // Kaikki polut vaativat autentikoinnin
//                    authorize.requestMatchers("/esimerkki").permitAll(); // Sallii kaikki polut kaikille
//                    authorize.requestMatchers("/adminEsimerkki").hasRole("ADMIN"); // Vaatii admin roolin
		})
//                .formLogin(Customizer.withDefaults())
				.csrf(AbstractHttpConfigurer::disable).httpBasic(Customizer.withDefaults())
				// Sallii H2-Consoleen pääsyn
				.headers(headers -> headers.frameOptions().sameOrigin());
		return http.build();
	}
}