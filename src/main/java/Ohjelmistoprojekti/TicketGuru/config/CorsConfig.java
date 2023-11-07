package Ohjelmistoprojekti.TicketGuru.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:8080", "http://ticketguru-ohjelmistoprojekti.rahtiapp.fi/")
                .allowedOriginPatterns("*") // Sallii kaikki CORS pyynnöt, Ylempi vaihtoehto vain tietyt.
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "HEAD", "OPTIONS", "REDIRECT")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(36000); // Kuinka kauan selain pitää CORS konffin muistissa.
    }
}