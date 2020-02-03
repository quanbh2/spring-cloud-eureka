package net.group.service.middleware.controller;

import lombok.extern.slf4j.Slf4j;
import net.group.service.middleware.controller.util.CustomResponseBody;
import net.group.supporter.httpclient.payload.HttpMethod;
import net.group.supporter.httpclient.payload.HttpResult;
import net.group.supporter.httpclient.service.HttpClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class TestController {

  final HttpClient httpClient;

  public TestController(HttpClient httpClient) {
    this.httpClient = httpClient;
  }

  @GetMapping("/test")
  public ResponseEntity<CustomResponseBody<Object>> testApi() {

    CustomResponseBody<Object> responseBody =
        CustomResponseBody.builder().data(null).message("OK").build();

    return new ResponseEntity<>(responseBody, HttpStatus.OK);
  }

  @GetMapping("/test-http-client")
  public ResponseEntity<CustomResponseBody<Object>> testHttpClient() {

    HttpResult httpResult = httpClient.query(HttpMethod.GET, "https://edumall.co.th", false);

    log.info(" httpResult: {}", httpResult);

    CustomResponseBody<Object> responseBody =
        CustomResponseBody.builder()
                .data(httpResult.getStatusCode())
                .message("OK")
                .build();

    return new ResponseEntity<>(responseBody, HttpStatus.OK);
  }
}
