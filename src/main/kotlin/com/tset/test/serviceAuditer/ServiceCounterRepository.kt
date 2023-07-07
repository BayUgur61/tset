package com.tset.test.serviceAuditer

import com.tset.test.counter.persistence.ServiceState
import org.springframework.data.repository.CrudRepository

interface ServiceCounterRepository: CrudRepository<ServiceCounter, Int> {
}
