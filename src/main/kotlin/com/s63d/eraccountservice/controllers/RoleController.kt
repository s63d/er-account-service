package com.s63d.eraccountservice.controllers

import com.s63d.eraccountservice.domain.Role
import com.s63d.eraccountservice.services.RoleService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/roles")
class RoleController(private val roleService: RoleService) {

    @PostMapping
    fun createRole(@RequestParam name: String, @RequestParam description: String): Role = roleService.createNew(name, description)

    @DeleteMapping
    @RequestMapping("{id}")
    fun deleteRole(@PathVariable id: String) = roleService.remove(id)

}