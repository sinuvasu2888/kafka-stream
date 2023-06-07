package com.mostafa.springcloud.kafkastreamwithdlq.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.SubscribableChannel;

public interface Sinks extends Sink {
    String INPUT2 = "input2";
    String INPUT3 = "input3";

    @Input(INPUT2)
    SubscribableChannel input2();

    @Input(INPUT3)
    SubscribableChannel input3();
}
