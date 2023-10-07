package com.envify.back.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.envify.back.dto.PackageDto;
import com.envify.back.entity.PackageEntity;
import com.envify.back.service.PackageService;

@RestController
@RequestMapping("/api/v1/packages")
public class PackageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PackageController.class);

    @Autowired
    private PackageService packageService;

    @GetMapping()
    public ResponseEntity<List<PackageEntity>> findAllPackages() {
        List<PackageEntity> packages = packageService.findAllPackages();

        return ResponseEntity.ok().body(packages);
    }

    @PostMapping("/")
    public ResponseEntity<String> createPackage(@RequestBody PackageDto packageEntityDto) {
        final PackageEntity packageCreated = new PackageEntity();
        packageCreated.setName(packageEntityDto.getName());

        try {
            packageService.savePackage(packageCreated);
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return ResponseEntity.badRequest().body("Bad request exception");
        }

        return ResponseEntity.ok().body("Package succesfully created.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<PackageEntity> findPackageById(@PathVariable int id) {
        PackageEntity packageEntity = packageService.findPackageById(id);

        return ResponseEntity.ok().body(packageEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PackageEntity> updatePackage(@PathVariable int id, @RequestBody PackageDto packageDto) {
        PackageEntity packageUpdated = new PackageEntity();
        mapPackageDtoToPackageEntity(packageDto, packageUpdated, id);
        PackageEntity packageEntity = packageService.updatePackage(packageUpdated);

        return ResponseEntity.ok().body(packageEntity);
    }

    @DeleteMapping("/{id}")
    public void deletePackageById(@PathVariable int id) {
        packageService.deletePackageById(id);
    }

    private void mapPackageDtoToPackageEntity(PackageDto packageDto, PackageEntity packageEntity, int packageId) {
        packageEntity.setId(packageId);
        packageEntity.setName(packageDto.getName());
    }
}
