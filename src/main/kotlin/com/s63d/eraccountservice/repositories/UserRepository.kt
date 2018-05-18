package com.s63d.eraccountservice.repositories

import com.s63d.eraccountservice.domain.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findByEmail(email: String): User?
}