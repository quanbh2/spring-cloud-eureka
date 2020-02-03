package net.group.service.authenticatorcenter.controller;

import net.group.service.authenticatorcenter.controller.util.CustomResponseBody;
import net.group.service.authenticatorcenter.controller.util.IController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController implements IController {

  @GetMapping
  public ResponseEntity<CustomResponseBody<Object>> testApi() {

    CustomResponseBody<Object> responseBody =
        CustomResponseBody.builder().data(null).message("OK").build();

    return new ResponseEntity<>(responseBody, HttpStatus.OK);
  }
}
