package com.insuremyteam.insurancemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Policy does not exist in database")
public class InsurancePolicyNotFoundException extends Exception {
}
