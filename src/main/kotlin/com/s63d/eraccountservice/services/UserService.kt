package com.s63d.eraccountservice.services

import com.s63d.eraccountservice.domain.User
import com.s63d.eraccountservice.exceptions.*
import com.s63d.eraccountservice.repositories.RoleRepository
import com.s63d.eraccountservice.repositories.UserRepository
import org.springframework.dao.DataAccessException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val roleRepository: RoleRepository, private val bcrypt: BCryptPasswordEncoder) {
    fun createUser(firstname: String, lastname: String, email: String, password: String, address: String, postal: String, city: String): User {
        val basicRole = roleRepository.findById("basic").orElseThrow { RoleNotFoundException("basic") }
        return try{
            userRepository.save(User(email, firstname, lastname, bcrypt.encode(password), address, postal, city, basicRole))
        } catch (e: DataAccessException) { throw DuplicateEntryException(email) }
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
            throw PasswordDoesNotMatchException()

        user.password = bcrypt.encode(new)

        userRepository.save(user)
        return mapOf("message" to "Password updated")
    }

    fun updateUserRole(id: Long, role: String): User {
        val user = userRepository.findById(id).orElseThrow{ UserDoesNotExistException(id) }
        user.role = roleRepository.findById(role).orElseThrow{ RoleDoesNotExistException(role) }
        return userRepository.save(user)
    }
}