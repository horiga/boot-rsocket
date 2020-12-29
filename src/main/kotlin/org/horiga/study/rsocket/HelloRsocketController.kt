package org.horiga.study.rsocket

import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration
import kotlin.random.Random

@Controller
class HelloRsocketController {

    companion object {
        val log = LoggerFactory.getLogger(HelloRsocketController::class.java)!!
    }

    @NoArgs
    data class RequestMessage(
        var message: String
    )

    @NoArgs
    data class ResponseMessage(
        var message: String
    )

    // request-response
    @MessageMapping("ping")
    fun ping(request: Mono<RequestMessage>): Mono<ResponseMessage> = request.map {
        log.info("Handle, request-response message!! message={}", it.message)
        ResponseMessage("Hello ${it.message}")
    }

    // fire-and-forget
    @MessageMapping("bye")
    fun bye(request: Mono<RequestMessage>): Mono<Void> = request.map {
        log.info("Handle, fire-and-forget message!! message={}", it.message)
    }.then()

    // request-stream
    @MessageMapping("feed")
    fun feed(request: Mono<RequestMessage>): Flux<ResponseMessage> = request.flatMapMany {
        log.info("Handle, request-stream message!! message={}", it.message)
        Flux.range(0, Random.nextInt(5, 30))
            .delayElements(Duration.ofMillis(500))
            .map { index ->
                log.info("send stream response message ({})", index)
                ResponseMessage("Response message stream index '$index', request.message=${it.message}")
            }
    }
}