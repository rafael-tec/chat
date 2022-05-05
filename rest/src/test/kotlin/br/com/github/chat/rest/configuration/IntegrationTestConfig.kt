package br.com.github.chat.rest.configuration

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = ["br.com.github.chat.*"])
class IntegrationTestConfig