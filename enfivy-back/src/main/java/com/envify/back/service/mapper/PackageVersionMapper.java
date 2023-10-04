package com.envify.back.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.envify.back.dto.PackageVersionDto;
import com.envify.back.entity.PackageVersionEntity;

@Service
@Mapper
public interface PackageVersionMapper {

    PackageVersionMapper MAPPER = Mappers.getMapper(PackageVersionMapper.class);

    PackageVersionEntity packageVersionDtoToPackageVersion(PackageVersionDto packageVersionDto);
}
