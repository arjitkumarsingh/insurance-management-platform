package com.insuremyteam.insurancemanagement.security;

import com.insuremyteam.insurancemanagement.domain.Client;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenGeneratorImpl implements TokenGenerator {
    @Override
    public Map<String, String> generateToken(Client client) {
        String secretKeyString = "DoNotShareSuperSecretKeyWithAnyone";
        byte[] secretKeyBytes = secretKeyString.getBytes();
        SecretKey key = Keys.hmacShaKeyFor(secretKeyBytes);

        String jwtToken = Jwts.builder().setSubject(client.getName()).setIssuedAt(new Date()).signWith(key).compact();
        Map<String, String> map = new HashMap<>();
        map.put("token", jwtToken);
        map.put("message", "Client successfully logged in");
        return map;
    }
}
