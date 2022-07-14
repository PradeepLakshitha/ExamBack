package com.exam.examserver.config;

import com.exam.examserver.service.impl.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");
        System.out.println(requestTokenHeader);

        String userName=null;
        String jwtToken=null;

        if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")){
            //yes
            jwtToken = requestTokenHeader.substring(7);
            System.out.println("token : "+jwtToken);

            try {
                userName = this.jwtUtil.extractUsername(jwtToken);
                System.out.println("Username "+userName);

            }catch (ExpiredJwtException e){
                e.printStackTrace();
                System.out.println("JWT token has expired!!!");

            }catch (Exception e){

                e.printStackTrace();
                System.out.println("Error!!!!");

            }
        }else{
            System.out.println("Invalid Token, not starts with bearer string");

        }

        //validated

        if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            final UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);

            if(this.jwtUtil.validateToken(jwtToken,userDetails)){
                //token is valid
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        }else{
            System.out.println("Token is not valid");
        }


        filterChain.doFilter(request,response);

    }
}
