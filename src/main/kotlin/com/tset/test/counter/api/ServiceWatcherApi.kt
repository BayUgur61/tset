package com.tset.test.counter.api

import com.tset.test.counter.persistence.ServiceState
import com.tset.test.counter.service.ServiceRegisterService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class ServiceWatcherApi ( private val serviceRegisterService: ServiceRegisterService) {

    @PostMapping("/deployment")
    @ResponseStatus(HttpStatus.CREATED)
    fun registerDeployment(@RequestBody deploymentState: ServiceStateDTO): SystemVersionResponse {
        val version = serviceRegisterService.handleDeployedService(deploymentState)
        return SystemVersionResponse(version);
    }

    @GetMapping("/services")
    fun readServices(@RequestParam systemVersion: Int): MutableIterable<ServiceStateDTO>? {
        return serviceRegisterService.getServicesFor(systemVersion)
    }
}