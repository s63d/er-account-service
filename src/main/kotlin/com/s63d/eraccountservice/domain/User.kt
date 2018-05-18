package com.s63d.eraccountservice.domain

import javax.persistence.*

@Entity
data class User(val email: String, val firstName: String, val lastName: String, val password: String, val address: String, val postal: String, val city: String, @OneToOne val role: Role, @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0)

@Entity
data class Role(@Id val name: String, val description: String, @OneToMany val permissions: List<Permission> = emptyList())


@Entity
data class Permission(@Id val name: String, val description: String)
