package net.group.supporter.securitycommon.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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
@Configuration
public class ModuleConfiguration {}
