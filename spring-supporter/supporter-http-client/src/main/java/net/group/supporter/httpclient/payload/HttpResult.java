package net.group.supporter.httpclient.payload;

import com.google.common.collect.Multimap;
import lombok.Data;

@Data
public class HttpResult {

  private String body;
  private Integer statusCode;
  private Multimap<String, String> headers;

  public HttpResult(Integer statusCode) {
    this.statusCode = statusCode;
  }
}
