package com.s63d.eraccountservice.controllers

import com.s63d.eraccountservice.services.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {
    @PostMapping
    fun createUser(
            @RequestParam firstname: String,
            @RequestParam lastname: String,
            @RequestParam email: String,
            @RequestParam password: String,
            @RequestParam address: String,
            @RequestParam postal: String,
            @RequestParam city: String,
            @RequestParam role: String?
    ) = userService.createNew(firstname, lastname, email, password, address, postal, city, role ?: "basic")

    @PostMapping("login")
    fun loginUser(@RequestParam email: String, @RequestParam password: String) = userService.login(email, password)

    @GetMapping("{id}")
    fun getUser(@PathVariable id: Long) = userService.findById(id)

    @PutMapping("{id}")
    fun updateUser(@PathVariable id: Long,
                   @RequestParam firstname: String?,
                   @RequestParam lastname: String?,
                   @RequestParam address: String?,
                   @RequestParam postal: String?,
                   @RequestParam city: String?
    ) = userService.updateUser(id, firstname, lastname, address, postal, city)

    @PutMapping("{id}/password")
    fun updatePassword(@PathVariable id: Long, @RequestParam old: String, @RequestParam new: String) = userService.updatePassword(id, old, new)
}