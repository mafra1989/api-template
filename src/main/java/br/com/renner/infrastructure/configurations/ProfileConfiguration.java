package br.com.renner.infrastructure.configurations;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Component
@PropertySource(ignoreResourceNotFound = true, value = "classpath:profile.${ambiente}.properties")
public class ProfileConfiguration {

    @Value("${spring.feature.toggle.external-api-caller}")
    private Boolean featureToggleExternalAPICaller;

}
