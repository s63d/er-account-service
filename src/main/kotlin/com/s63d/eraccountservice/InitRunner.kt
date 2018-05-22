package com.s63d.eraccountservice

import com.s63d.eraccountservice.services.RoleService
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class InitRunner(private val roleService: RoleService) : CommandLineRunner {

    override fun run(vararg args: String?) {
        roleService.createNew("basic", "A basic user")
        roleService.createNew("police", "A police officer user")
        roleService.createNew("government", "A government user")
        roleService.createNew("admin", "An administrator")
    }
}