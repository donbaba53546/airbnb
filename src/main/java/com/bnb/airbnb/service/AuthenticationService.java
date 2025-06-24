package com.bnb.airbnb.service;

import com.bnb.airbnb.entity.AppUser;
import com.bnb.airbnb.payload.LoginDto;
import com.bnb.airbnb.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    @Autowired
    private AppUserRepository appUserRepository;
    private JwtService jwtService;

    public AuthenticationService(AppUserRepository appUserRepository, JwtService jwtService) {
        this.appUserRepository = appUserRepository;
        this.jwtService = jwtService;
    }
    public AppUser addUser(AppUser appUser){
        String enpw = BCrypt.hashpw(appUser.getPassword(), BCrypt.gensalt(5));
        appUser.setPassword(enpw);

        return appUserRepository.save(appUser);
    }
  public String verifyLogin(LoginDto loginDto){
       Optional<AppUser> byEmail = appUserRepository.findByEmail(loginDto.getEmail());
      if(byEmail.isPresent()){
         AppUser appUser = byEmail.get();
            if(BCrypt.checkpw(loginDto.getPassword(),appUser.getPassword())){
                //password generates return true call method which creates token
               return jwtService.generateToken(appUser);
            }


        }
      return null;

    }
}
