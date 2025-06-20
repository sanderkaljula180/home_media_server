package dev.sanderk.home_media_server;

import dev.sanderk.home_media_server.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class HomeMediaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeMediaServerApplication.class, args);
	}

}
