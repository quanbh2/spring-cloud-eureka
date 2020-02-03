package net.group.service.authenticatorcenter.service;

import net.group.supporter.mysql.local.model.DemoUser;

public interface ThirdPartyVerifyTokenService {

    /**
     * @param token
     * @return user if verified success, return null if verified failed
     */
    DemoUser verifyTokenAndFindUser(String token);
}
