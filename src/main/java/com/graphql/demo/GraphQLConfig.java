package com.graphql.demo;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLConfig {

    @Bean
    public ServletRegistrationBean<CustomGraphQLHttpServlet> graphQLServletRegistrationBean() {
        ServletRegistrationBean<CustomGraphQLHttpServlet> registrationBean = new ServletRegistrationBean<>(new CustomGraphQLHttpServlet(), "/graphql");
        return registrationBean;
    }

    
}
