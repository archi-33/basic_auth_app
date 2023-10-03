/**
 * Custom JWT authentication entry point for handling unauthorized access in the application.
 */
package com.practice.basic_auth.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


/**
 * Custom authentication entry point that responds with an unauthorized status and message when authentication fails.
 */
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {

  /**
   * Handles unauthorized access by setting the response status and providing an error message.
   *
   * @param request       The HTTP request.
   * @param response      The HTTP response.
   * @param authException The authentication exception that triggered this entry point.
   * @throws IOException      If an I/O error occurs while writing the response.
   * @throws ServletException If a servlet-related error occurs.
   */
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {

    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    PrintWriter writer = response.getWriter();
    writer.println("Access Denied !! " + authException.getMessage());

  }
}
