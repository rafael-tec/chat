package br.com.github.chat.application

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["br.com.github.chat"])
@EntityScan(basePackages = ["br.com.github.chat.entities"])
class Boot

fun main() {
    SpringApplication.run(Boot:: class.java)
}
