package com.example.restaurant_collection_fturgunov.controller.ipa882;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Validated
@Configuration
@ConfigurationProperties(prefix = "nspcredentials")
@Data
public class AdminCredentialsProperties {
    @NotBlank
    private String login;
    @NotBlank
    private String password;
}