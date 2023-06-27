package com.auth.service;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth.exception.ExpiredException;

import io.jsonwebtoken.ExpiredJwtException;

@Component
@CrossOrigin
public class JWTFilter extends OncePerRequestFilter {

	@Autowired
	JWTUtillity utillity;

	@Autowired
	UserDetailService service;

//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		UserDetails userDetails = service.loadUserByUsername("Manojkumar");
//		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//				userDetails, null, userDetails.getAuthorities());
//
//		usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//		filterChain.doFilter(request, response);
//	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		System.out.println("Filterrrrrrrrrr");
		String authorization = request.getParameter("Authorization");
		System.out.println(authorization);
		String sessionToken = null;
		String sessionUsername = null;
		String token = null;
		String userName = null;

		if ((authorization != null && authorization.startsWith("Manojkumar"))) {
			System.out.println("Filterrrrrrrrrr------1");
			HttpSession session = request.getSession();
			sessionUsername = (String) session.getAttribute("UserToken");
			System.out.println(sessionUsername);
			token = request.getParameter("Authorization").substring(10);

			userName = utillity.getUsernameFromToken(token);
			System.out.println(userName);

		}
		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			System.out.println("Permission Granted");
			UserDetails userDetails = service.loadUserByUsername(userName);
			System.out.println("Filterrrrrrrrrr------2");

			if (utillity.validateToken(token, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}

		System.out.println("Filterrrrrrrrrr------3");

		filterChain.doFilter(request, response);

	}

}

//USING SESSION
//if (request.getRequestURI().contains("authenticate") || session.getAttribute("USER") != null) {
////UserDetails userDetails = service.loadUserByUsername("Manojkumar");
////UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
////		userDetails, null, userDetails.getAuthorities());
////
////usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
////
////SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//
//filterChain.doFilter(request, response);
//}
