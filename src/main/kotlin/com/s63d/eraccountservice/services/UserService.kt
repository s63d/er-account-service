package com.s63d.eraccountservice.services

import com.s63d.eraccountservice.domain.User
import com.s63d.eraccountservice.exceptions.DuplicateEntryException
import com.s63d.eraccountservice.repositories.RoleRepository
import com.s63d.eraccountservice.repositories.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val roleRepository: RoleRepository, private val bcrypt: BCryptPasswordEncoder) {
    fun createNew(firstname: String, lastname: String, email: String, password: String, address: String, postal: String, city: String): User {
        try {
            val basicRole = roleRepository.findById("basic").get()
            return userRepository.save(User(email, firstname, lastname, bcrypt.encode(password), address, postal, city, basicRole))
        } catch (e: Exception) {
            throw DuplicateEntryException(email) // TODO: do something else with this
        }
    }

    fun findById(id: Long) = userRepository.findById(id).get()
    fun findByEmail(email: String) = userRepository.findByEmail(email)

    fun updateUser(id: Long, firstname: String?, lastname: String?, address: String?, postal: String?, city: String?): User {
        val user = userRepository.findById(id).get()
        user.firstName = firstname ?: user.firstName
        user.lastName = lastname ?: user.lastName
        user.address = address ?: user.address
        user.postal = postal ?: user.postal
        user.city = city ?: user.city
        return userRepository.save(user)
    }

    fun updatePassword(id: Long, old: String, new: String): Map<String, String> {
        val user = userRepository.findById(id).get()

        if (bcrypt.matches(old, user.password))
            throw Exception("Old password does not match")

        user.password = bcrypt.encode(new)

        userRepository.save(user)
        return mapOf("message" to "Password updated")
    }
}