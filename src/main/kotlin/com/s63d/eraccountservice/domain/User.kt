package com.s63d.eraccountservice.domain

import javax.persistence.*

@Entity
data class User(val email: String, var firstName: String, var lastName: String, var password: String, var address: String, var postal: String, var city: String, @OneToOne val role: Role, @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0)

@Entity
data class Role(@Id val name: String, val description: String, @OneToMany val permissions: List<Permission> = emptyList())


@Entity
data class Permission(@Id val name: String, val description: String)
