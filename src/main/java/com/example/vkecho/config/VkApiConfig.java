package com.example.vkecho.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Component
@Getter
@Setter
@PropertySource(value = "classpath:vk.properties")
@ConfigurationProperties(prefix = "vk.api")
public class VkApiConfig {
    @NotBlank
    private String accessToken;
    @NotBlank
    private Double v;
    @NotBlank
    private String secret;
    @NotBlank
    private String confirmation;
}
