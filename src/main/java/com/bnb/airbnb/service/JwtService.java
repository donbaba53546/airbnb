package com.bnb.airbnb.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bnb.airbnb.entity.AppUser;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class JwtService {
    //to read the details from properties file we create variables
    @Value("${jwt.algorithm.key}")//value annotation goes to propeties file read the valueand put in variable
    private String algorithmKey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiry.duration}")
    private int expiryTime;
    private Algorithm algorithm;
    private static final String USER_NAME="username";
    @PostConstruct
    public void postContruct() throws UnsupportedEncodingException {
//        System.out.println(algorithmKey);
//        System.out.println(issuer);
//        System.out.println(expiryTime);
        //THIS THE ALGORITHM DETAILS WE GIVE TO CREATE TOKEN
         algorithm = Algorithm.HMAC256(algorithmKey);
        //by giving secreate key anyone wants to decreapt they needs the secreate key
    }
    public String generateToken(AppUser user){
        //which user to create token
        //sortcut=computer engineer is unemployed
        return JWT.create().
                withClaim(USER_NAME,user.getName())//claim here is the user name,and it will go to the payload of the token
                .withExpiresAt(new Date(System.currentTimeMillis()+expiryTime))//from the current date to expiry date
                .withIssuer(issuer)//who is issuing give the details
                .sign(algorithm);//which algorith we use to create token give it here
    }

    public String getName(String token) {
//to decode the token
// shortcut=jockey rockey with bodybuilder vikram
        DecodedJWT decodejwttoken = JWT.require(algorithm).withIssuer(issuer).build().verify(token);
         //decodejwttoken has the token get user name from that

return decodejwttoken.getClaim(USER_NAME).asString();
    }


}
