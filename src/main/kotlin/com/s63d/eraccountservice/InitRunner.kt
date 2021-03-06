package com.s63d.eraccountservice

import com.s63d.eraccountservice.services.RoleService
import com.s63d.eraccountservice.services.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class InitRunner(private val roleService: RoleService, private val userService: UserService) : CommandLineRunner {

    override fun run(vararg args: String?) {
        roleService.createNew("basic", "A basic user")
        roleService.createNew("police", "A police officer user")
        roleService.createNew("government", "A government user")
        val adminRole = roleService.createNew("admin", "An administrator")
        val admin = userService.createUserIfNotExists("admin", "admin", "admin@ersols.online", "admin", "admin", "admin", "admin")
        userService.updateUserRole(admin.id, adminRole.name)
    }
}