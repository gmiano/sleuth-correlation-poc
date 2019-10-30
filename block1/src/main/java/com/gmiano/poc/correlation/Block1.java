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
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Slf4j
public class Block1 {
  @Autowired
  KafkaTemplate<String, String> kafkaTemplate;

  public static void main(String[] args) {
    SpringApplication.run(Block1.class, args);
  }

  @GetMapping("/produce")
  public void produce() {
    log.info("producing event...");
    kafkaTemplate.send("com.gmiano.poc.correlation.first", UUID.randomUUID().toString(), "hello world 1");
  }
}
