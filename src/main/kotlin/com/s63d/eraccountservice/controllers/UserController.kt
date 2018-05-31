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
            @RequestParam city: String
    ) = userService.createUser(firstname, lastname, email, password, address, postal, city)

    @GetMapping("{id}")
    fun getUser(@PathVariable id: Long) = userService.findById(id)

    @GetMapping("/all")
    fun getAllUsers() = userService.findAllUsers()

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