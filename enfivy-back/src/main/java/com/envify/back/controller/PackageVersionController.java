package com.envify.back.controller;

import com.envify.back.dto.PackageVersionDto;
import com.envify.back.entity.PackageVersionEntity;
import com.envify.back.service.PackageVersionService;
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

    @PutMapping("/{id}")
    public ResponseEntity<PackageVersionEntity> updatePackage(@PathVariable("packageId") int packageId, @PathVariable int id, @RequestBody PackageVersionDto packageVersionDto) {
        PackageVersionEntity packageVersionUpdated = new PackageVersionEntity();
        mapPackageVersionDtoToPackageVersionEntity(packageVersionDto, packageVersionUpdated, id, packageId);
        PackageVersionEntity packageVersionEntity = packageVersionService.updatePackageVersion(packageVersionUpdated);

        return ResponseEntity.ok().body(packageVersionEntity);
    }

    @DeleteMapping("/{id}")
    public void deletePackageById(@PathVariable int id) {
        packageVersionService.deletePackageVersionById(id);
    }

    private void mapPackageVersionDtoToPackageVersionEntity(PackageVersionDto packageVersionDto, PackageVersionEntity packageVersionEntity, int packageVersionId, int packageId) {
        packageVersionEntity.setId(packageVersionId);
        packageVersionEntity.setVersionNumber(packageVersionDto.getVersionNumber());
        packageVersionEntity.setUrl(packageVersionDto.getUrl());
        packageVersionEntity.setVersionStatusId(packageVersionDto.getVersionStatusId());
        packageVersionEntity.setPackageId(packageId);
    }
}
