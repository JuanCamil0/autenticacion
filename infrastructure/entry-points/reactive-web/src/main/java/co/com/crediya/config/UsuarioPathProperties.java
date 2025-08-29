package co.com.crediya.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "routes.paths")
public record UsuarioPathProperties(
    String users
) {}
