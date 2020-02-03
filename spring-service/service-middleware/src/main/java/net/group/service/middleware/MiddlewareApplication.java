package net.group.service.middleware;

import lombok.extern.slf4j.Slf4j;
import net.group.supporter.httpclient.config.EnableHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableHttpClient
@Slf4j
public class MiddlewareApplication {

  public static void main(String[] args) {
    log.info("MIDDLEWARE-APPLICATION STARTING ...");
    SpringApplication.run(MiddlewareApplication.class, args);
  }
}
