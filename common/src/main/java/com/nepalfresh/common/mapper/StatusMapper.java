package com.nepalfresh.common.mapper;

import com.nepalfresh.common.dto.StatusDto;
import com.nepalfresh.entity.Status;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class StatusMapper {

    public abstract StatusDto entityToStatusDto(Status status);

}
