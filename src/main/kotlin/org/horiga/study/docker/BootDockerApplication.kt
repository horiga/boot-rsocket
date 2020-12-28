package org.horiga.study.docker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@SpringBootApplication
class BootDockerApplication

fun main(args: Array<String>) {
	runApplication<BootDockerApplication>(*args)
}

@Configuration
class RsocketServerConfiguration {
}


