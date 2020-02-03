package net.group.supporter.mysql.local.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DemoUser {

  private Integer id;

  private String name;

  private String email;
}
