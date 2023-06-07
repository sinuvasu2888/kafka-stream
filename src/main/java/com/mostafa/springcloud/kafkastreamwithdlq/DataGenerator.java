package com.mostafa.springcloud.kafkastreamwithdlq;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataGenerator {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("./data/messages.txt");
        if(Files.exists(path)){
            Files.delete(path);
        }
        Files.write(path,
                IntStream.range(1, 1000).boxed().map(String::valueOf).collect(Collectors.toList()),
                StandardOpenOption.CREATE_NEW);

    }
}
