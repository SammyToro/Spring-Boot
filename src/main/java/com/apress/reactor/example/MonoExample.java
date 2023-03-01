package com.apress.reactor.example;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apress.reactor.example.domain.ToDo;

import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoProcessor;
import reactor.core.scheduler.Schedulers;

@Configuration
public class MonoExample {

    private static Logger LOG = LoggerFactory.getLogger(MonoExample.class);
    
    @Bean
	public CommandLineRunner runMonoExample(){
        return args -> {
            MonoProcessor<ToDo> promise = MonoProcessor.create();
            Mono<ToDo> result = promise
                .doOnSuccess(p -> LOG.info("MONO >> ToDo: {}", p.getDescription()))
                .doOnTerminate(() -> LOG.info("MONO >> Done"))
                .doOnError(t -> LOG.error(t.getMessage(), t))
                .subscribeOn(Schedulers.single());

                promise.onNext(new ToDo("Buy my ticket for SpringOne Platform 2018"));
                //promise.onError(new IllegalArgumentException("There is an error processing the ToDo ..."));
                //promise.onComplete();

                result.block(Duration.ofMillis(1000));
        };
    }
}
