package com.bnb.airbnb.configuration;

import com.bnb.airbnb.entity.AppUser;
import com.bnb.airbnb.repository.AppUserRepository;
import com.bnb.airbnb.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private AppUserRepository appUserRepository;

    public JWTFilter(JwtService jwtService, AppUserRepository appUserRepository) {
        this.jwtService = jwtService;
        this.appUserRepository = appUserRepository;
    }

    //this class extract the token from any incoming request
    //OnceperRequestFilteer class is an abstract class has an incomplite method
   // url we submitting in front end automatically comes to dofilterinternal method request object
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");//this won't run automatically to run this annotate class @Component
        //this token recived bearer added to it
        //System.out.println(token);
        if (token!=null && token.startsWith("Bearer ")){
            //if token is not null and starts with bearer enter and remove bearer from it
            String tokenval = token.substring(8, token.length()-1);
         //   System.out.println(tokenval);
             String username=jwtService.getName(tokenval);//verify token
            System.out.println(username);

            Optional<AppUser> byName = appUserRepository.findByName(username);
            if(byName.isPresent()){
                AppUser appUser = byName.get();


                UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(appUser,null, Collections.singleton(new SimpleGrantedAuthority(appUser.getRole())));//last value should be  a collection
                //this is the authentication token which is used to authenticate the user                                                                      // we give the rolehere form of collection
                //we pass the user object and null because we don't have password
                //we pass the authorities which is the role of the user
                //this is the authentication token which is used to authenticate the user
                //we pass the user object and null because we don't have password
                //we pass the authorities which is the role of the user
               auth.setDetails(new WebAuthenticationDetails(request));//this is the authentication token which is used to authenticate the user
               //we pass the user object and null because we don't have password
                SecurityContextHolder.getContext().setAuthentication(auth);//this is the authentication token which is used to authenticate the user
                //we pass the user object and null because we don't have password
                //spring security understandthe token is valid and have the user and the user requestingthis url
            }
        }
        filterChain.doFilter(request,response);//when the jwt filter runs along with it filter chain runs once jwtfilter runs further it will go to the next filter
        //it runs in spring security filter chain and responsible for permiting and denying access for a request

    }
}
