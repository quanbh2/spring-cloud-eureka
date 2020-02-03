package net.group.supporter.securitycommon.token;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import net.group.supporter.mysql.local.model.DemoUser;
import net.group.supporter.securitycommon.model.UserToken;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Generate and validate token from user's info
 *
 * @author quanbh2
 */
@Component
@Slf4j
@Profile({"jwt_token"})
public class JwtTokenProvider implements TokenProvider {

  // Secret JWT that only is known by server side
  @Value("${authen.jwt.secret}")
  private String jwtSecret;

  // expired time of token in Milliseconds
  @Value("${authen.jwt.expiredTime}")
  private long jwtExpiration;

  @Override
  public String generateToken(DemoUser demoUser) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + jwtExpiration);

    return Jwts.builder()
        .setSubject(demoUser.getEmail())
        .setIssuedAt(new Date())
        .setExpiration(expiryDate)
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  @Override
  public boolean validateToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
      return true;
    } catch (MalformedJwtException ex) {
      log.error("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      log.error("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      log.error("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      log.error("JWT claims string is empty.");
    }
    return false;
  }

  @Override
  public String getUserEmailFromToken(String token) {
    Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    return claims.getSubject();
  }

  @Override
  public Authentication getAuthentication(String token) {
    try {

      if (!StringUtils.isBlank(token) && validateToken(token)) {
        String email = getUserEmailFromToken(token);

        // TODO: find user by email from DB
        // DemoUser demoUser = demoUserService.findByEmail(email);

        // get list String privilege
        Set<String> listPrivilege = new HashSet<>();
        // TODO: set listPrivilege

        List<GrantedAuthority> authorities =
            listPrivilege.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(email, null, authorities);
      } else {
        log.error("Cant not authentication from token: [{}] because null or empty", token);
      }

    } catch (Exception ex) {
      log.error(ExceptionUtils.getStackTrace(ex));
    }

    return null;
  }

  @Override
  public UserToken generateUserToken(DemoUser demoUser) {
    String jwtToken = generateToken(demoUser);

    return UserToken.builder().accessToken(jwtToken).name(demoUser.getName()).build();
  }
}
