package com.s63d.eraccountservice.services

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.Claim
import com.s63d.eraccountservice.domain.User
import org.springframework.stereotype.Service

@Service
class JwtService {

    companion object {
        val algo = Algorithm.HMAC256("S63D-SECRET")
    }

    fun sign(user: User) = JWT.create().withSubject(user.id.toString()).withClaim("userRole", user.role.name).withClaim("userId", user.id).sign(algo)

    fun decode(rawToken: String): MutableMap<String, Claim> {
        val token = rawToken.replace("Bearer ", "")
        return JWT.decode(token).claims
    }

}