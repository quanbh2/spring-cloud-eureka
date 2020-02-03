package net.group.supporter.securitycommon.token;

import net.group.supporter.mysql.local.model.DemoUser;
import net.group.supporter.securitycommon.model.UserToken;
import org.springframework.security.core.Authentication;

public interface TokenProvider {

    String generateToken(DemoUser demoUser);

    boolean validateToken(String authToken);

    String getUserEmailFromToken(String token);

    Authentication getAuthentication(String token);

    UserToken generateUserToken(DemoUser demoUser);
}
