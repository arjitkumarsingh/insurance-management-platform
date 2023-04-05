package com.insuremyteam.insurancemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Client already exists in database ")
public class ClientAlreadyExistsException extends Exception {
}
