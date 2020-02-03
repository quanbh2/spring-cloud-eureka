package net.group.service.authenticatorcenter.controller.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomResponseBody<T> {

    @JsonProperty("data")
    T data;

    String message;
}
