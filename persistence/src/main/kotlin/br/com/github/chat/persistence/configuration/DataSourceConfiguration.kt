package br.com.github.chat.persistence.configuration

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["br.com.github.chat.persistence"])
class DataSourceConfiguration(
    val environment: Environment
) {

    @Bean
    @SuppressWarnings
    fun dataSource() = DataSourceBuilder
        .create()
        .username(environment.getRequiredProperty("spring.datasource.username"))
        .password(environment.getRequiredProperty("spring.datasource.password"))
        .driverClassName(environment.getRequiredProperty("spring.datasource.driver-class-name"))
        .url(environment.getRequiredProperty("spring.datasource.url"))
        .build()
}