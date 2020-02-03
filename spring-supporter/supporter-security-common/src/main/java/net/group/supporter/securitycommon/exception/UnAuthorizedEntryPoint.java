package net.group.supporter.securitycommon.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  This class indicate to the browser its credentials are no longer authorized
 *  and causing it to prompt the user to login again by call the commence() method
 *
 * @author quanbh2
 */
@Component
@Slf4j
public class UnAuthorizedEntryPoint extends BasicAuthenticationEntryPoint {

    /**
     * Unauthorized browser's credentials causing user to login again
     *
     * @param httpServletRequest request from client
     * @param httpServletResponse response from server
     * @param e exception
     * @throws IOException
     */
    @Override
    public void commence(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            AuthenticationException e)
            throws IOException {
        log.error("Responding with unauthorized error. Message: {}", e.getMessage());
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
    }

    /**
     * TODO
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("net.group.quanbh2");
        super.afterPropertiesSet();
    }
}
