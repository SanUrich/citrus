package com.citrus.myproject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.citrus.myproject.reporting.ExtentReporter;

/**
 * @author Christoph Deppisch
 */
@Configuration
public class EndpointConfig {

    @Bean
    public ExtentReporter extentReporter() {
        return new ExtentReporter();
    }
}
