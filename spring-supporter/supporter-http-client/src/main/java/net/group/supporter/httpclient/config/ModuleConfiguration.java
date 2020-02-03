package net.group.supporter.httpclient.config;

import lombok.extern.slf4j.Slf4j;
import net.group.supporter.httpclient.payload.HttpProperties;
import net.group.supporter.httpclient.service.DefaultHttpClient;
import net.group.supporter.httpclient.service.HttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@Slf4j
public class ModuleConfiguration {

  @PostConstruct
  public void postConstruct() {
    log.info("init supporter-http-client complete");
  }

  @Bean
  public HttpClient httpClient() {
    HttpProperties httpProperties = new HttpProperties();
    return new DefaultHttpClient(httpProperties);
  }
}
