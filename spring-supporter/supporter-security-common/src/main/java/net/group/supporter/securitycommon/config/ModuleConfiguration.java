package net.group.supporter.securitycommon.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Initialize @Component class in sub-modules
 *
 * @author quanbh2
 */
@ComponentScan({
  "net.group.supporter.securitycommon.exception",
  "net.group.supporter.securitycommon.filter",
  "net.group.supporter.securitycommon.token"
})
@Slf4j
@Configuration
public class ModuleConfiguration {

  @PostConstruct
  public void postConstruct() {
    log.info("init supporter-security-common complete");
  }
}
