package net.group.service.authenticatorcenter.controller.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IController {

  default Map<String, Object> getBindingErrors(BindingResult bindingResult) {

    List<FieldError> errors = bindingResult.getFieldErrors();
    Map<String, Object> map = new HashMap<>();
    for (FieldError error : errors) {
      map.put(error.getField(), error.getDefaultMessage());
    }
    return map;
  }
}
