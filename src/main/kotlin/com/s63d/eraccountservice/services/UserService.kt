package com.s63d.eraccountservice.services

import com.s63d.eraccountservice.domain.User
import com.s63d.eraccountservice.repositories.RoleRepository
import com.s63d.eraccountservice.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val roleRepository: RoleRepository, private val jwtService: JwtService) {
    fun createNew(firstname: String, lastname: String, email: String, password: String, address: String, postal: String, city: String): User {
        val basicRole = roleRepository.findById("basic").get()
        // TODO use hashing
        return userRepository.save(User(email, firstname, lastname, password, address, postal, city, basicRole))
    }

    fun login(email: String, password: String): Map<String, String> {
        val user = userRepository.findByEmail(email) ?: throw Exception("User not found")

        if (user.password != password) {
            throw Exception("Invalid password")
        }
        return mapOf("token" to jwtService.sign(user))
    }

    fun findById(id: Long) = userRepository.findById(id).get()

    fun updateUser(id: Long, firstname: String?, lastname: String?, address: String?, postal: String?, city: String?): User {
        val user = userRepository.findById(id).get()
        user.firstName = firstname ?: user.firstName
        user.lastName = lastname ?: user.lastName
        user.address = address ?: user.address
        user.postal = postal ?: user.postal
        user.city = city ?: user.city
        return userRepository.save(user)
    }

    fun updatePassword(id: Long, old: String, new: String): Pair<String, String> {
        val user = userRepository.findById(id).get()

        if (user.password != old) // TODO use hashing
            throw Exception("Old password does not match")
        user.password = new

        userRepository.save(user)
        return "message" to "Password updated"
    }
}