package br.com.github.chat.persistence

import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment

@Configuration
class DataSourceConfiguration(
    val environment: Environment
) {

    @Bean
    fun dataSource() = DataSourceBuilder
        .create()
        .username(environment.getRequiredProperty("spring.datasource.user"))
        .password(environment.getRequiredProperty("spring.datasource.password"))
        .driverClassName(environment.getRequiredProperty("spring.datasource.driver-class-name"))
        .url(environment.getRequiredProperty("spring.datasource.url"))
        .build()
}