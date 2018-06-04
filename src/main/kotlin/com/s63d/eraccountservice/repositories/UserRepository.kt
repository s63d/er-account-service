package com.s63d.eraccountservice.repositories

import com.s63d.eraccountservice.domain.User
import org.springframework.data.repository.PagingAndSortingRepository

interface UserRepository : PagingAndSortingRepository<User, Long> {
    fun findByEmail(email: String): User?
}