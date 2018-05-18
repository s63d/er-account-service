package com.s63d.eraccountservice.repositories

import com.s63d.eraccountservice.domain.Role
import org.springframework.data.repository.CrudRepository

interface RoleRepository : CrudRepository<Role, String>