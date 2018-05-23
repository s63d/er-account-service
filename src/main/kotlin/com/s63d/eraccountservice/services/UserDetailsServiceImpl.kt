package com.s63d.eraccountservice.services

import com.s63d.eraccountservice.exceptions.UnknownUsernameException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(private val userService: UserService) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val user = userService.findByEmail(email) ?: throw UnknownUsernameException(email)
        return User(user.email, user.password, getAuthorities(user))
    }

    fun loadUserById(id: Long): UserDetails {
        val user = userService.findById(id)
        return User(user.email, user.password, getAuthorities(user))
    }

    private fun getAuthorities(user: com.s63d.eraccountservice.domain.User): List<GrantedAuthority> {
        return listOf(
            SimpleGrantedAuthority("ROLE_${user.role.name.toUpperCase()}")
        )
    }
}