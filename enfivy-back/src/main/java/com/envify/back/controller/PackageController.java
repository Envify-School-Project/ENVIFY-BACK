package com.envify.back.controller;

import com.envify.back.entity.PackageEntity;
import com.envify.back.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/packages")
public class PackageController {

    @Autowired
    private PackageService packageService;

    @GetMapping()
    public ResponseEntity<List<PackageEntity>> findAllPackages() {
        List<PackageEntity> packages = packageService.findAllPackages();

        return ResponseEntity.ok().body(packages);
    }

    @PostMapping("/")
    public ResponseEntity<PackageEntity> createPackage(@RequestBody PackageEntity packageEntity) {
        PackageEntity createdPackage = packageService.createPackage(packageEntity);

        return ResponseEntity.ok().body(createdPackage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PackageEntity> findPackageById(@PathVariable int id) {
        PackageEntity packageEntity = packageService.findPackageById(id);

        return ResponseEntity.ok().body(packageEntity);
    }

    @DeleteMapping("/{id}")
    public void deletePackageById(@PathVariable int id) {
        packageService.deletePackageById(id);
    }
}
