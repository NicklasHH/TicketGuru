package Ohjelmistoprojekti.TicketGuru.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	// Luo salasanan käsittelijän, joka käyttää BCrypt-salausta
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Määrittää tietoturvasuodattimen asetukset
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> {
			authorize.anyRequest().authenticated(); // Määrittää, että kaikki polut vaativat autentikoinnin
			// authorize.requestMatchers("/esimerkki").permitAll(); // Sallii kaikki polut
			// kaikille
			// authorize.requestMatchers("/adminEsimerkki").hasRole("ADMIN"); // Vaatii
			// admin roolin
		}).formLogin(Customizer.withDefaults()) // Konfiguroi oletusarvoisen kirjautumisen
				.csrf(AbstractHttpConfigurer::disable) // Poistaa käytöstä CSRF-suojauksen
				.httpBasic(Customizer.withDefaults()) // Konfiguroi oletusarvoisen HTTP Basic -autentikoinnin
				.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin())); // Sallii H2-Consoleen pääsyn ja asettaa kehysasetukset

		return http.build();
	}
}
