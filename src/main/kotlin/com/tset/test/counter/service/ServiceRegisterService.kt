package com.tset.test.counter.service

import com.tset.test.counter.api.ServiceStateDTO
import com.tset.test.counter.persistence.ServiceState
import com.tset.test.counter.persistence.ServiceStateMapper
import com.tset.test.counter.persistence.ServiceStateRepository
import com.tset.test.serviceAuditer.ServiceCounter
import com.tset.test.serviceAuditer.ServiceCounterRepository
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ServiceRegisterService(
    private val serviceStateRepository: ServiceStateRepository,
    private val serviceCounterRepository: ServiceCounterRepository
) {

    private val mapper: ServiceStateMapper = Mappers.getMapper(ServiceStateMapper::class.java)

    fun handleDeployedService(deployedService: ServiceStateDTO): Int {
        val findAll = serviceCounterRepository.findAll()

        val latestSystem = findAll.firstOrNull() ?: ServiceCounter(null,0,null)

        val existingSystem =
            serviceStateRepository.findByServiceNameAndVersion(deployedService.serviceName,
                deployedService.version)
        if (existingSystem != null) {
            println("gleich")
            return latestSystem.runningSystemVersion
        }

        val existingService = serviceStateRepository.findByServiceName(deployedService.serviceName)
        if (existingService != null) {
            println("name wurde gefunden")
            val now = LocalDate.now()
            existingService.version = deployedService.version
            existingService.changeTime = now
            return saveToDB(existingService,latestSystem)
        }

        println("nix wurde gefunden $deployedService")
        return saveToDB(mapper.mapFromDto(deployedService), latestSystem)
    }

    private fun saveToDB(service: ServiceState, latestSystem: ServiceCounter): Int {
        val newVersionNumber = latestSystem.runningSystemVersion + 1
        latestSystem.runningSystemVersion = newVersionNumber
        latestSystem.changeTime = LocalDate.now()


        println("speichern von counter $latestSystem")
        println("speichern von service $service")
        serviceStateRepository.save(service)
        serviceCounterRepository.save(latestSystem)

        return newVersionNumber
    }

    fun getServicesFor(systemVersion: Int): MutableIterable<ServiceStateDTO>? {
        return mapper.mapToDto(serviceStateRepository.findAll())
    }
}