package com.envify.back.service.mapper;

import com.envify.back.dto.PackageVersionDto;
import com.envify.back.entity.PackageVersionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Mapper
public interface PackageVersionMapper {

    PackageVersionMapper MAPPER = Mappers.getMapper(PackageVersionMapper.class);

    PackageVersionEntity packageVersionDtoToPackageVersion(PackageVersionDto packageVersionDto);

//    @Override
//    public PackageVersionEntity packageVersionDtoToPackageVersion(PackageVersionDto packageVersionDto) {
//        PackageVersionEntity packageVersionEntity = new PackageVersionEntity();
//        packageVersionEntity.setVersionNumber(packageVersionDto.getVersionNumber());
//        packageVersionEntity.setUrl(packageVersionDto.getUrl());
//        packageVersionEntity.setVersionStatusId(packageVersionDto.getVersionStatusId());
//
//        return packageVersionEntity;
//    }
}
