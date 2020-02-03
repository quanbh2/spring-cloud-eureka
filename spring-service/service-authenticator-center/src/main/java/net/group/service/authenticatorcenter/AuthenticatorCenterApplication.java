package net.group.service.authenticatorcenter;

import lombok.extern.slf4j.Slf4j;
import net.group.supporter.securitycommon.config.EnableSecurityCommon;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class to initiate authenticator service
 *
 * @author quanbh2
 */
@EnableSecurityCommon
@SpringBootApplication
@Slf4j
public class AuthenticatorCenterApplication {

    public static void main(String[] args) {
        log.info("AUTHENTICATOR CENTER STARTING ...");
        SpringApplication.run(AuthenticatorCenterApplication.class, args);
    }
}
