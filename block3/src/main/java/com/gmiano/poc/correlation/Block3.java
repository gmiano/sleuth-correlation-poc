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

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

@SpringBootApplication
@Slf4j
public class Block3 {
  public static void main(String[] args) {
    SpringApplication.run(Block3.class, args);
  }

  @KafkaListener(topics = "com.gmiano.poc.correlation.second", groupId = "consumer2")
  public void consume(@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String messageKey, @Payload String message) {
    log.info("consuming message...");
    log.info(String.format("Message %s, key %s", message, messageKey));
  }
}
