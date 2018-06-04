package com.s63d.eraccountservice.services

import com.s63d.eraccountservice.domain.Role
import com.s63d.eraccountservice.repositories.RoleRepository
import org.springframework.stereotype.Service

@Service
class RoleService(private val roleRepository: RoleRepository) {
    fun getAll(): Iterable<Role> = roleRepository.findAll()
    fun createNew(name: String, description: String): Role = roleRepository.save(Role(name, description))
    fun remove(id: String) = roleRepository.deleteById(id)
}