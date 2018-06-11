package com.s63d.eraccountservice.domain

import javax.persistence.*

@Entity
data class User(@Column(length = 100) val email: String, var firstName: String, var lastName: String, var password: String, var address: String, var postal: String, var city: String, @OneToOne var role: Role, @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0)

@Entity
data class Role(@Id @Column(length = 20) val name: String, val description: String)
