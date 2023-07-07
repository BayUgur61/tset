package com.tset.test.counter.service

import com.tset.test.counter.api.ServiceStateDTO
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext

@SpringBootTest
@DisplayName("Service Register Service tests")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ServiceRegisterServiceTest {

    @Autowired
    lateinit var serviceRegisterService: ServiceRegisterService

    @Test
    @DisplayName("Deploy ein service mit name 1 und version 1")
    fun test1() {
        val expected: Int = 1
        val result = serviceRegisterService.handleDeployedService(ServiceStateDTO("name1", 1))
        Assertions.assertEquals(result, expected)
    }

    @Test
    @DisplayName("Deploy zwei gleiche  services mit name 1 und version 1")
    fun test2() {
        val expected: Int = 1
        serviceRegisterService.handleDeployedService(ServiceStateDTO("name1", 1))
        val result = serviceRegisterService.handleDeployedService(ServiceStateDTO("name1", 1))
        Assertions.assertEquals(result, expected)
    }

    @Test
    @DisplayName("Deploy zwei unterschiedliche services mit version 1 und 2")
    fun test3() {
        val expected: Int = 2
        serviceRegisterService.handleDeployedService(ServiceStateDTO("test1", 1))
        val result = serviceRegisterService.handleDeployedService(ServiceStateDTO("test2", 2))
        Assertions.assertEquals(expected, result)
    }

    @Test
    @DisplayName("Deploy zwei unterschiedliche services mit version 1")
    fun test4() {
        val expected: Int = 2
        serviceRegisterService.handleDeployedService(ServiceStateDTO("x1", 1))
        val result = serviceRegisterService.handleDeployedService(ServiceStateDTO("y1", 1))
        Assertions.assertEquals(result, expected)
    }

    @Test
    @DisplayName("Deploy zwei unterschiedliche services öfters und mit gleicher version")
    fun test5() {
        val expected: Int = 3
        serviceRegisterService.handleDeployedService(ServiceStateDTO("x", 1))
        serviceRegisterService.handleDeployedService(ServiceStateDTO("y", 1))
        serviceRegisterService.handleDeployedService(ServiceStateDTO("x", 2))
        val result = serviceRegisterService.handleDeployedService(ServiceStateDTO("x", 2))
        Assertions.assertEquals(result, expected)
    }

}