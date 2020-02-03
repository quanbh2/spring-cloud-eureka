package net.group.supporter.httpclient.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.annotation.PostConstruct;

@AllArgsConstructor
@Data
public class HttpProperties {

  private Integer socketTimeout;
  private Integer connectTimeout;
  private Integer connectRequestTimeout;
  private Integer defaultHttpCode;

  public HttpProperties() {
    init();
  }

  @PostConstruct
  public void init() {
    if (socketTimeout == null) {
      socketTimeout = 30000;
    }

    if (connectTimeout == null) {
      connectTimeout = 30000;
    }

    if (connectRequestTimeout == null) {
      connectRequestTimeout = 30000;
    }

    if (defaultHttpCode == null) {
      defaultHttpCode = 500;
    }
  }
}
