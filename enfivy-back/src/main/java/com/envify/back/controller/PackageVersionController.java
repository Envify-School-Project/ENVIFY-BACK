package com.envify.back.controller;

import com.envify.back.dto.PackageVersionDto;
import com.envify.back.entity.ConfigPackageFileEntity;
import com.envify.back.entity.PackageVersionEntity;
import com.envify.back.service.ConfigPackageFileService;
import com.envify.back.service.PackageVersionService;
import com.envify.back.service.mapper.PackageVersionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/packages/{packageId}/versions")
public class PackageVersionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PackageVersionController.class);

    @Autowired
    private PackageVersionService packageVersionService;
    @Autowired
    private ConfigPackageFileService configPackageFileService;

    @GetMapping()
    public ResponseEntity<List<PackageVersionEntity>> findAllPackageVersions(@PathVariable("packageId") int packageId) {
        List<PackageVersionEntity> packageVersions = packageVersionService.findAllPackageVersions(packageId);

        return ResponseEntity.ok().body(packageVersions);
    }

    @PostMapping("")
    public ResponseEntity<String> createPackageVersion(@PathVariable("packageId") int packageId, @RequestBody PackageVersionDto packageVersionDto) {
       final PackageVersionEntity packageVersionEntity = new PackageVersionEntity();
       packageVersionEntity.setVersionNumber(packageVersionDto.getVersionNumber());
       packageVersionEntity.setUrl(packageVersionDto.getUrl());
       packageVersionEntity.setPackageId(packageId);
       packageVersionEntity.setVersionStatusId(packageVersionDto.getVersionStatusId());

        try {
            packageVersionService.savePackageVersion(packageVersionEntity);
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return ResponseEntity.badRequest().body("Bad request exeption");
        }

        return ResponseEntity.ok().body("Package version succesfully created.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<PackageVersionEntity> findPackageVersionById(@PathVariable int id) {
        PackageVersionEntity packageVersionEntity = packageVersionService.findPackageVersionById(id);

        return ResponseEntity.ok().body(packageVersionEntity);
    }

    @GetMapping("/{id}/config_files")
    public ResponseEntity<ConfigPackageFileEntity> findPackageConfigFiles(@PathVariable int id) {
        ConfigPackageFileEntity configPackageFileEntity = configPackageFileService.findConfigPakageFileByPakageVersionId(id);

        return ResponseEntity.ok().body(configPackageFileEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PackageVersionEntity> updatePackage(@PathVariable("packageId") int packageId, @PathVariable int id, @RequestBody PackageVersionDto packageVersionDto) {
        PackageVersionEntity packageVersionUpdated = PackageVersionMapper.MAPPER.packageVersionDtoToPackageVersion(packageVersionDto);
        packageVersionUpdated.setId(id);
        packageVersionUpdated.setPackageId(packageId);
        PackageVersionEntity packageVersionEntity = packageVersionService.updatePackageVersion(packageVersionUpdated);

        return ResponseEntity.ok().body(packageVersionEntity);
    }

    @DeleteMapping("/{id}")
    public void deletePackageVersionById(@PathVariable int id) {
        packageVersionService.deletePackageVersionById(id);
    }
}
