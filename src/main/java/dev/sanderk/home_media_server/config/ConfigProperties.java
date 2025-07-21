package dev.sanderk.home_media_server.config;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "list")
public class ConfigProperties {

    @NotBlank
    @Max(10)
    @Min(2)
    private int maximumNumberOfItemsOnList;

}
