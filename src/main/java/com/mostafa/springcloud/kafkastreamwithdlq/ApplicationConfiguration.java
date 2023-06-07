package com.mostafa.springcloud.kafkastreamwithdlq;

import brave.Span;
import brave.Tracer;
import com.mostafa.springcloud.kafkastreamwithdlq.messaging.Sinks;
import com.mostafa.springcloud.kafkastreamwithdlq.messaging.Sources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;

import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class ApplicationConfiguration {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Tracer tracer;

    @StreamListener(Sinks.INPUT)
    @SendTo(Sources.OUTPUT)
    public Message<String> process(@Header("deliveryAttempt") AtomicInteger attemptNumber, @Payload String message) throws Exception {
        Span span = tracer.nextSpan().start().name("process 1");
        span.tag("message body", message);
        logger.info("{}th attempt in the first step of processing message: {}", attemptNumber.get(), message);
        if (message.contains("error-1")) {
            Exception e = new Exception("error in the first step");
            span.error(e);
            throw e;
        } else {
            span.finish();
            return MessageBuilder.withPayload("step 1-" + message).build();
        }
    }

    @StreamListener(Sinks.INPUT2)
    @SendTo(Sources.OUTPUT2)
    public Message<String> process2(@Header("deliveryAttempt") AtomicInteger attemptNumber, @Payload String message) throws Exception {
        Span span = tracer.nextSpan().start().name("process 2");
        span.tag("message body", message);
        logger.info("{}th attempt in the second step of processing message: {}", attemptNumber.get(), message);
        if (message.contains("error-2")) {
            Exception e = new Exception("error in the second step");
            span.error(e);
            throw e;
        } else {
            span.finish();
            return MessageBuilder.withPayload("step 2-" + message).build();
        }
    }

    @StreamListener(Sinks.INPUT3)
    @SendTo(Sources.OUTPUT3)
    public Message<String> process3(@Header("deliveryAttempt") AtomicInteger attemptNumber, @Payload String message) throws Exception {
        Span span = tracer.nextSpan().start().name("process 3");
        span.tag("message body", message);
        logger.info("{}th attempt in the third step of processing message: {}", attemptNumber.get(), message);
        if (message.contains("error-3")) {
            Exception e = new Exception("error in the third step");
            span.error(e);
            throw e;
        } else {
            span.finish();
            return MessageBuilder.withPayload("step 3-" + message).build();
        }
    }
}
