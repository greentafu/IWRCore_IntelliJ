package mit.iwrcore.IWRCore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.client.RestTemplate;

@Configuration
public class FileuploadConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
