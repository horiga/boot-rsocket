package org.horiga.study.rsocket

import io.micrometer.core.instrument.MeterRegistry
import io.rsocket.micrometer.MicrometerRSocketInterceptor
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.rsocket.server.ServerRSocketFactoryProcessor
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

annotation class NoArgs

@SpringBootApplication
class BootDockerApplication

fun main(args: Array<String>) {
    runApplication<BootDockerApplication>(*args)
}

@Configuration
class RsocketServerConfiguration {

    companion object {
        val log = LoggerFactory.getLogger(RsocketServerConfiguration::class.java)!!
    }

    // TODO: `ServerRSocketFactoryProcessor` is deprecated. should change to use `RSocketServerCustomizer`.
    @Bean
    fun serverRSocketFactoryProcessor(meterRegistry: MeterRegistry) = ServerRSocketFactoryProcessor { factory ->
        factory.addResponderPlugin(MicrometerRSocketInterceptor(meterRegistry))
    }
//
//    @Bean
//    fun rSocketServerCustomizer(): RSocketServerCustomizer = RSocketServerCustomizer { server ->
//        server.resume(Resume()
//                          .sessionDuration(Duration.ofMinutes(5))
//                          .retry(
//                              Retry.fixedDelay(Long.MAX_VALUE, Duration.ofSeconds(5)).doBeforeRetry {
//                                  log.info("Disconnected. trying to resume...")
//                              }
//                          )
//        )
//    }
}
