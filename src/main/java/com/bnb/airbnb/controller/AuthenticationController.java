package com.bnb.airbnb.controller;

import com.bnb.airbnb.entity.AppUser;
import com.bnb.airbnb.exception.UserExists;
import com.bnb.airbnb.payload.JWTToken;
import com.bnb.airbnb.payload.LoginDto;
import com.bnb.airbnb.repository.AppUserRepository;
import com.bnb.airbnb.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService, AppUserRepository appUserRepository) {
        this.authenticationService = authenticationService;
        this.appUserRepository = appUserRepository;
    }
    @Autowired
    private AppUserRepository appUserRepository;
//http://localhost:8080/api/authentication/createuser
    @PostMapping("/createuser")
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser appUser){
        Optional<AppUser> byEmail = appUserRepository.findByEmail(appUser.getEmail());
        if(byEmail.isPresent()){
            throw new UserExists("user all ready exist in database");
        }
        appUser.setRole("ROLE_USER");
        AppUser appUser1 = authenticationService.addUser(appUser);
        return new ResponseEntity<>(appUser1, HttpStatus.CREATED);
    }
    @PostMapping("/createPROPERTYOWNER")
    public ResponseEntity<AppUser> createPropertyOwner(@RequestBody AppUser appUser){
        Optional<AppUser> byEmail = appUserRepository.findByEmail(appUser.getEmail());
        if(byEmail.isPresent()){
            throw new UserExists("user all ready exist in database");
        }
        appUser.setRole("ROLE_OWNER");
        AppUser appUser1 = authenticationService.addUser(appUser);
        return new ResponseEntity<>(appUser1, HttpStatus.CREATED);
    }
    @PostMapping("/createPROPERTYMANAGER")
    public ResponseEntity<AppUser> createPropertyMANAGER(@RequestBody AppUser appUser){
        Optional<AppUser> byEmail = appUserRepository.findByEmail(appUser.getEmail());
        if(byEmail.isPresent()){
            throw new UserExists("user all ready exist in database");
        }
        appUser.setRole("ROLE_MANAGER");
        AppUser appUser1 = authenticationService.addUser(appUser);
        return new ResponseEntity<>(appUser1, HttpStatus.CREATED);
    }

  @PostMapping("/login")
   public ResponseEntity<?> signIN(@RequestBody LoginDto loginDto){
    String token = authenticationService.verifyLogin(loginDto);
      JWTToken jwtToken=new JWTToken();
      //embeded token into jwttoken
      //we return ass json because frontend team allWAys get a json object
        if(token!=null){
            jwtToken.setTokenType("JWT");
            jwtToken.setToken(token);
            return new ResponseEntity<>(jwtToken,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("invalid useername or password",HttpStatus.UNAUTHORIZED);
        }
   }

}

