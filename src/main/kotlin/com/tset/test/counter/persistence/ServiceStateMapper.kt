package com.tset.test.counter.persistence

import com.tset.test.counter.api.ServiceStateDTO
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface ServiceStateMapper {

    fun mapToDto(source: MutableIterable<ServiceState>): MutableIterable<ServiceStateDTO>

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "changeTime", expression = "java(LocalDate.now())")
    fun mapFromDto(source: ServiceStateDTO): ServiceState
}