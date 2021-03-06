package com.s63d.eraccountservice.services

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.Claim
import com.s63d.eraccountservice.domain.User
import org.springframework.http.HttpHeaders
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Service
class JwtService(private val userService: UserService) : AuthenticationSuccessHandler {

    companion object {
        val algo = Algorithm.HMAC256("S63D-SECRET")!!
        private const val PREFIX = "Bearer "
    }

    override fun onAuthenticationSuccess(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication) {
        val user = userService.findByEmail(authentication.name)!!
        val token = JWT.create()
                .withSubject(user.email)
                .withClaim("userId", user.id)
                .withClaim("userRole", user.role.name)
                .sign(algo)
        response.addHeader(HttpHeaders.AUTHORIZATION, PREFIX + token)
    }
}