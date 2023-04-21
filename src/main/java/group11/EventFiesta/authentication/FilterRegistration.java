package group11.EventFiesta.authentication;

import group11.EventFiesta.application.PropertiesReader;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Properties;

@Configuration
public class FilterRegistration {

    @Bean
    public FilterRegistrationBean registerAuthenticationFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(authenticationFilter());
        registration.addUrlPatterns("/*");
        Properties applicationProperties = PropertiesReader.getInstance().getProperties();
        registration.addInitParameter("EXCLUDE_URL_PATTERN", applicationProperties.getProperty("event_fiesta.excluded_url"));
        registration.setName("authenticationFilter");
        registration.setOrder(1);
        return registration;
    }

    private Filter authenticationFilter() {
        return new AuthenticationFilter();
    }

}
