package net.group.service.authenticatorcenter.controller;

import net.group.service.authenticatorcenter.service.ThirdPartyVerifyTokenService;
import net.group.supporter.mysql.local.model.DemoUser;
import net.group.supporter.securitycommon.model.UserToken;
import net.group.supporter.securitycommon.token.TokenProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

  private final ThirdPartyVerifyTokenService googleTokenService;

  private final TokenProvider tokenProvider;

  public LoginController(
      @Qualifier("google_token_service") ThirdPartyVerifyTokenService googleTokenService,
      TokenProvider tokenProvider) {
    this.googleTokenService = googleTokenService;
    this.tokenProvider = tokenProvider;
  }

  @PostMapping("/google")
  public ResponseEntity<UserToken> loginGooglePost(@RequestParam(value = "token") String token) {
    DemoUser demoUser = googleTokenService.verifyTokenAndFindUser(token);
    if (demoUser != null) {
      UserToken userToken = tokenProvider.generateUserToken(demoUser);

      return new ResponseEntity<>(userToken, HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
