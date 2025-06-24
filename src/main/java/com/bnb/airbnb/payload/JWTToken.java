package com.bnb.airbnb.payload;

public class JWTToken {
    //WE MADE THIS CLASSS TO RETURN THE TOKEN AS AJSON OBJECT
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String tokenType;

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
