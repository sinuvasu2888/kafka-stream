package com.mostafa.springcloud.kafkastreamwithdlq.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;

public interface Sources extends Source {
    String OUTPUT2 = "output2";
    String OUTPUT3 = "output3";

    @Output(OUTPUT2)
    MessageChannel output2();

    @Output(OUTPUT3)
    MessageChannel output3();
}
