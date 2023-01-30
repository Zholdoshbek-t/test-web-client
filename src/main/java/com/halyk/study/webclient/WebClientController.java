package com.halyk.study.webclient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api-client")
public class WebClientController {

    @GetMapping("/a")
    public Optional<Output> getFactOpt() {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build()
                .get()
                .uri("https://catfact.ninja/fact")
                .retrieve()
                .bodyToMono(Output.class)
                .blockOptional();
    }

    @GetMapping("/b")
    public Output getFactBlock() {
        Mono<Output> outputMono = WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build()
                .get()
                .uri("https://catfact.ninja/fact")
                .retrieve()
                .bodyToMono(Output.class);

        return outputMono.block();
    }

    @GetMapping("/c")
    public Mono<Output> getFact() {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build()
                .get()
                .uri("https://catfact.ninja/fact")
                .retrieve()
                .bodyToMono(Output.class);
    }

}
