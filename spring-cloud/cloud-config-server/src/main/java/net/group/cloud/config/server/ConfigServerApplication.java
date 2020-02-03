package net.group.cloud.config.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * This class to initiate configuration server
 *
 * @author quanbh2
 */
@EnableConfigServer
@SpringBootApplication
@Slf4j
public class ConfigServerApplication {
  public static void main(String[] args) {
    log.info("CONFIG SERVER STARTING ...");
    SpringApplication.run(ConfigServerApplication.class, args);
  }
}
