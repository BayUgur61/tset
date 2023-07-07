package com.tset.test.serviceAuditer

import jakarta.persistence.*
import java.time.LocalDate

@Entity
data class ServiceCounter (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,

    @Column
    var runningSystemVersion: Int = 0,

    @Column
    var changeTime: LocalDate?
)