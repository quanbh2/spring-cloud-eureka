package net.group.supporter.securitycommon.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Store user's token
 *
 * @author quanbh2
 */
@Validated
@Builder
@Data
public class UserToken {
  @JsonProperty("access_token")
  private String accessToken;

  @JsonProperty("refresh_token")
  private String refreshToken;

  @JsonProperty("roles")
  private List<String> listRole;

  @JsonProperty("name")
  private String name;

  @JsonProperty("username")
  private String username;

  @JsonProperty("expires_in")
  private Integer expiresIn;

  @JsonProperty("avatar")
  private String avatar;
}
