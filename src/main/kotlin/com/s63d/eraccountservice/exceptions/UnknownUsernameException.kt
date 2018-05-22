package com.s63d.eraccountservice.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.FORBIDDEN)
class UnknownUsernameException(email: String) : Exception("No user account found with email $email")