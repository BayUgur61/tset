package com.tset.test.counter.persistence

import com.tset.test.counter.api.ServiceStateDTO
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@DisplayName("Service State Mapper tests")
class ServiceStateMapperTest {

    private val mapper: ServiceStateMapper = Mappers.getMapper(ServiceStateMapper::class.java)

    @Test
    @DisplayName("Mappe von einem dto auf ein entity")
    fun contextLoads() {

        val dto: ServiceStateDTO = ServiceStateDTO("name1", 3)
        val expected: ServiceState = ServiceState(null,"name1",3,null)

        val entityFromMapper = mapper.mapFromDto(dto)

        Assertions.assertNotNull(entityFromMapper.changeTime)
        Assertions.assertEquals(expected.serviceName,entityFromMapper.serviceName)
        Assertions.assertEquals(expected.version,entityFromMapper.version)
    }
}