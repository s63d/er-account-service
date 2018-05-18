package com.s63d.eraccountservice.services

import com.s63d.eraccountservice.domain.User
import com.s63d.eraccountservice.repositories.RoleRepository
import com.s63d.eraccountservice.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val roleRepository: RoleRepository) {
    fun createNew(firstname: String, lastname: String, email: String, password: String, address: String, postal: String, city: String): User {
        val basicRole = roleRepository.findById("basic").get()
        return userRepository.save(User(email, firstname, lastname, password, address, postal, city, basicRole))
    }

    fun login(email: String, password: String): Boolean {
        val user = userRepository.findByEmail(email) ?: throw Exception("User not found")

        if (user.password != password) {
            throw Exception("Invalid password")
        }
        return true
    }
}