package com.insuremyteam.insurancemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Claim already exists in database")
public class ClaimAlreadyExistsException extends Exception {
}
