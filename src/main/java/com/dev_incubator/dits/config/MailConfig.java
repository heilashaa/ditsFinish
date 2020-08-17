package com.dev_incubator.dits.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.dev_incubator.dits")
@PropertySource(value = {"classpath:application.properties"})
@AllArgsConstructor
public class MailConfig {

    private final Environment environment;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(environment.getRequiredProperty("mail.host"));
        mailSender.setPort(Integer.parseInt(environment.getRequiredProperty("mail.port")));
        mailSender.setProtocol(environment.getRequiredProperty("mail.protocol"));
        mailSender.setUsername(environment.getRequiredProperty("mail.username"));
        mailSender.setPassword(environment.getRequiredProperty("mail.password"));
        mailSender.setDefaultEncoding(environment.getRequiredProperty("mail.encoding"));
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.auth", environment.getRequiredProperty("mail.smtp.auth"));
        javaMailProperties.put("mail.smtp.starttls.enable", environment.getRequiredProperty("mail.smtp.starttls.enable"));
        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }
}
