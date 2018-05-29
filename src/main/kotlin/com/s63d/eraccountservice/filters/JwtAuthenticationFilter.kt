package com.s63d.eraccountservice.filters

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.http.HttpServletRequest

class JwtAuthenticationFilter(authManager: AuthenticationManager, authSuccessHandler: AuthenticationSuccessHandler? = null) : UsernamePasswordAuthenticationFilter() {
    init {
        authenticationManager = authManager
        if (authSuccessHandler != null)
            setAuthenticationSuccessHandler(authSuccessHandler)
    }

    data class UsernamePassword(val username: String = "", val password: String = "")

    override fun obtainUsername(request: HttpServletRequest): String = try {
        val data = jacksonObjectMapper().readValue(request.inputStream, UsernamePassword::class.java)
        data.username
    } catch (_:Exception) {
        super.obtainUsername(request)
    }

    override fun obtainPassword(request: HttpServletRequest): String = try {
        val data = jacksonObjectMapper().readValue(request.inputStream, UsernamePassword::class.java)
        data.password
    } catch (_:Exception) {
        super.obtainPassword(request)
    }
}