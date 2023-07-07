package com.tset.test.counter.persistence

import org.springframework.data.repository.CrudRepository

interface ServiceStateRepository: CrudRepository<ServiceState, Int> {
    fun findByServiceNameAndVersion(serviceName: String, version: Int): ServiceState?
    fun findByServiceName(title: String): ServiceState?
}
