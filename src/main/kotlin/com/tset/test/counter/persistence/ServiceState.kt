package com.tset.test.counter.persistence

import jakarta.persistence.*
import java.time.LocalDate

@Entity
data class ServiceState (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,

    @Column
    var serviceName: String,

    @Column
    var version: Int,

    @Column
    var changeTime: LocalDate?
)