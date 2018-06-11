package com.s63d.eraccountservice.domain

import javax.persistence.*

@Entity
data class User(@Column(unique = true, length = 150) val email: String, var firstName: String, var lastName: String, var password: String, var address: String, var postal: String, var city: String, @OneToOne var role: Role, @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0)

@Entity
data class Role(@Id val name: String, val description: String)
