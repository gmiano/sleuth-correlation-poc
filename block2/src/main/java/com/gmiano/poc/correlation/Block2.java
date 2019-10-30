package com.gmiano.poc.correlation;
/*
 * Insert a description here.
 *
 * Bugs: none known
 *
 * @author  gmiano <mianogc@gmail.com>
 * @createDate  29/10/2019
 *
 */

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Slf4j
public class Block2 {
  @Autowired
  KafkaTemplate<String, String> kafkaTemplate;

  public static void main(String[] args) {
    SpringApplication.run(Block2.class, args);
  }

  @KafkaListener(topics = "com.gmiano.poc.correlation.first", groupId = "consumer1")
  public void consume(@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String messageKey, @Payload String message) {
    log.info("consuming message..");
    log.info(String.format("Message %s, key %s", message, messageKey));
    produce(message);
  }

  public void produce(String message) {
    log.info("sending message...");
    kafkaTemplate.send("com.gmiano.poc.correlation.second", UUID.randomUUID().toString(), message);
  }

  @GetMapping("/produce")
  public void produce() {
    log.info("producing event...");
    produce("hello world 2");
  }
}
