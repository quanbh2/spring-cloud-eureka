package net.group.service.authenticatorcenter;

import lombok.extern.slf4j.Slf4j;
import net.group.supporter.securitycommon.config.EnableSecurityCommon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
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
public class AuthenticatorCenterApplication implements CommandLineRunner {

    public static void main(String[] args) {
        log.info("AUTHENTICATOR CENTER STARTING ...");
        SpringApplication.run(AuthenticatorCenterApplication.class, args);
    }

    @Value("${google.credential.client.id}")
    private String clientId;

    @Override
    public void run(String... args) throws Exception {
        log.info("clientId: {}", clientId);
    }
}
