package net.group.supporter.securitycommon.filter;

import lombok.extern.slf4j.Slf4j;
import net.group.supporter.securitycommon.token.TokenProvider;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Each request from client to server need to be filtered by this class
 *
 * @author quanbh2
 */
@Slf4j
@Component
public class TokenRequestFilter extends OncePerRequestFilter {

  @Autowired private TokenProvider tokenProvider;

  /**
   * Authentication filter layer If it is authenticated, forward the request
   *
   * @param httpServletRequest http request from client
   * @param httpServletResponse http response from server
   * @param filterChain
   * @throws ServletException
   * @throws IOException
   */
  @Override
  protected void doFilterInternal(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      FilterChain filterChain)
      throws ServletException, IOException {

    log.info("(doFilter)START");
    try {

      // find token in header
      String requestTokenHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
      if (StringUtils.isBlank(requestTokenHeader)) {
        // find token in cookie
        requestTokenHeader = getCookieToken(httpServletRequest, HttpHeaders.AUTHORIZATION);
      }
      Authentication authentication = tokenProvider.getAuthentication(requestTokenHeader);
      if (authentication != null) {
        log.info("authentication: {}", authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }

    } catch (Exception ex) {
      log.error(ExceptionUtils.getStackTrace(ex));
      throw ex;
    }

    // if there's no problem, forward the request to the destination
    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }

  /**
   * @param request http request from client
   * @param header request header
   * @return cookie value from header request
   */
  private String getCookieToken(HttpServletRequest request, String header) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals(header)) {
          return cookie.getValue();
        }
      }
    }
    return null;
  }
}
