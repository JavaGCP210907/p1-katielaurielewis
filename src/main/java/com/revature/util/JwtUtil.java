package com.revature.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil {

    private static final String secret ="a player may concede the game at any time";
    private static final Algorithm algorithm = Algorithm.HMAC256(secret);

    public static String generate(String username, String password){

        String token = JWT.create()
                .withClaim("username", username)
                .withClaim("password", password)
                .sign(algorithm); 

        return  token;
    }

    public static DecodedJWT isValidJWT(String token){
            DecodedJWT jwt = JWT.require(algorithm).build().verify(token);
            return  jwt;
    }
	
}
