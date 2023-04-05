package com.insuremyteam.insurancemanagement.security;

import com.insuremyteam.insurancemanagement.domain.Client;

import java.util.Map;

public interface TokenGenerator {
    Map<String, String> generateToken(Client client);
}
