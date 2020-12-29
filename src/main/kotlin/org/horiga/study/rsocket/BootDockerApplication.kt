package org.horiga.study.rsocket

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration

annotation class NoArgs

@SpringBootApplication
class BootDockerApplication

fun main(args: Array<String>) {
    runApplication<BootDockerApplication>(*args)
}

@Configuration
class RsocketServerConfiguration
