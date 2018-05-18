package com.s63d.eraccountservice.controllers

import com.s63d.eraccountservice.services.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

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
    ) = userService.createNew(firstname, lastname, email, password, address, postal, city)

    @PostMapping("login")
    fun loginUser(@RequestParam email: String, @RequestParam password: String) = userService.login(email, password)
}