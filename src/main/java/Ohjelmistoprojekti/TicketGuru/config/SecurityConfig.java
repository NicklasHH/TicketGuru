package Ohjelmistoprojekti.TicketGuru.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	// Luo salasanan käsittelijän, joka käyttää BCrypt-salausta
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Määrittää tietoturvasuodattimen asetukset
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authorize) -> authorize
	            .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                .requestMatchers("/api/postalcodes", "/").permitAll() // Salli kaikille pääsy tähän reittiin
				.anyRequest().authenticated()
				) 

				.csrf(AbstractHttpConfigurer::disable) // Poistaa käytöstä CSRF-suojauksen
				.httpBasic(Customizer.withDefaults()) // Konfiguroi oletusarvoisen HTTP Basic -autentikoinnin
				.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin())); // Sallii H2-Consoleen pääsyn ja asettaa kehysasetukset

		return http.build();
	}

}
