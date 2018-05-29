package com.s63d.eraccountservice.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
class PasswordDoesNotMatchException : Exception("The given password do not match")