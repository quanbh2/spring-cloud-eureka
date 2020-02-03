package net.group.service.authenticatorcenter.service.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import lombok.extern.slf4j.Slf4j;
import net.group.service.authenticatorcenter.service.ThirdPartyVerifyTokenService;
import net.group.supporter.mysql.local.model.DemoUser;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

@Slf4j
@Qualifier("google_token_service")
@Service
public class GoogleTokenServiceImpl implements ThirdPartyVerifyTokenService {

  @Value("${google.credential.client.id}")
  private String clientId;

  @Override
  public DemoUser verifyTokenAndFindUser(String token) {

    try {

      // 1. verify token and get email from payload
      GoogleIdToken.Payload payload = getPayloadByTokenFromGoogle(token);
      String email = Objects.requireNonNull(payload).getEmail();

      // 2. find user by email, create new if not existed
      DemoUser demoUser = null;
      // SumoUser user = sumoUserService.findByEmail(email);
      if (demoUser != null) {
        return demoUser;
      } else {
        // TODO : create new user
        return DemoUser.builder().email("quanbh2@toprate.io").build();
      }

    } catch (Exception ex) {
      log.error(ExceptionUtils.getFullStackTrace(ex));
      return null;
    }
  }

  private GoogleIdToken.Payload getPayloadByTokenFromGoogle(String token) {

    // 1. create GoogleIdTokenVerifier
    GoogleIdTokenVerifier verifier =
        new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(), JacksonFactory.getDefaultInstance())
            .setAudience(Collections.singletonList(clientId))
            .build();

    // 2. verify token and get GoogleIdToken
    GoogleIdToken idToken;
    try {
      idToken = verifier.verify(token);
    } catch (Exception ex) {
      idToken = null;
      log.error(ExceptionUtils.getStackTrace(ex));
    }

    log.info("GoogleIdToken: {}", idToken);
    if (idToken == null) {
      return null;
    }

    // 3. parse data from GoogleIdToken
    GoogleIdToken.Payload payload = idToken.getPayload();
    log.info("GoogleIdToken.Payload: {}", payload);

    return payload;
  }
}
