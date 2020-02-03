package net.group.supporter.securitycommon.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Initialize annotation @EnableSecurityCommon
 *
 * @author quanbh2
 */
@Import({ModuleConfiguration.class})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EnableSecurityCommon {
}
