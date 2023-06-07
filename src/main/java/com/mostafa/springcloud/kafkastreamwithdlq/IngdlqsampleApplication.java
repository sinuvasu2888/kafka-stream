package com.mostafa.springcloud.kafkastreamwithdlq;

import com.mostafa.springcloud.kafkastreamwithdlq.messaging.Sinks;
import com.mostafa.springcloud.kafkastreamwithdlq.messaging.Sources;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;


@SpringBootApplication
@EnableBinding({Sinks.class, Sources.class})
public class IngdlqsampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(IngdlqsampleApplication.class, args);
    }
}
