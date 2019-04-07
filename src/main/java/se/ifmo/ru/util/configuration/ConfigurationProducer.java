package se.ifmo.ru.util.configuration;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.InjectionPoint;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.Properties;

/**
 * Read the application.properties file from the classpath and produce values that can be injected with @{@link Configurable}.
 * <p>
 * It's a simple and lightweight alternative to the Apache DeltaSpike Configuration Mechanism.
 */
@ApplicationScoped
public class ConfigurationProducer {

    private Properties properties;

    ConfigurationProducer() {
        properties = new Properties();
        properties.put("authentication.jwt.secret", "secret");
        properties.put("authentication.jwt.issuer", "http://example.org");
        properties.put("authentication.jwt.audience", "http://example.org");
        properties.put("authentication.jwt.clockSkew", "10");
        properties.put("authentication.jwt.validFor", "36000");
        properties.put("authentication.jwt.refreshLimit", "1");
        properties.put("authentication.jwt.claimNames.authorities", "authorities");
        properties.put("authentication.jwt.claimNames.refreshCount", "refreshCount");
        properties.put("authentication.jwt.claimNames.refreshLimit", "refreshLimit");
    }

    @Produces
    @Configurable
    public String produceString(InjectionPoint ip) {
        return properties.getProperty(getKey(ip));
    }

    @Produces
    @Configurable
    public Integer produceInteger(InjectionPoint ip) {
        return Integer.valueOf(properties.getProperty(getKey(ip)));
    }

    @Produces
    @Configurable
    public Long produceLong(InjectionPoint ip) {
        return Long.valueOf(properties.getProperty(getKey(ip)));
    }

    @Produces
    @Configurable
    public Boolean produceBoolean(InjectionPoint ip) {
        return Boolean.valueOf(this.properties.getProperty(getKey(ip)));
    }

    private String getKey(InjectionPoint ip) {
        return ip.getAnnotated().getAnnotation(Configurable.class).value();
    }
}