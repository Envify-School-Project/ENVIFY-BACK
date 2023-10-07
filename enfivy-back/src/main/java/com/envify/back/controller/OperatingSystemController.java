package com.envify.back.controller;

import com.envify.back.dto.OperatingSystemDto;
import com.envify.back.dto.PackageDto;
import com.envify.back.entity.OperatingSystemEntity;
import com.envify.back.entity.PackageEntity;
import com.envify.back.service.OperatingSystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/os")
public class OperatingSystemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperatingSystemController.class);

    @Autowired
    private OperatingSystemService operatingSystemService;

    @GetMapping()
    public ResponseEntity<List<OperatingSystemEntity>> findAllOperatingSystems() {
        List<OperatingSystemEntity> operatingSystems = operatingSystemService.findAllOperatingSystems();

        return ResponseEntity.ok().body(operatingSystems);
    }

    @PostMapping("/")
    public ResponseEntity<String> createOperatingSystem(@RequestBody OperatingSystemDto operatingSystemDto) {
        final OperatingSystemEntity operatingSystemCreated = new OperatingSystemEntity();
        operatingSystemCreated.setName(operatingSystemDto.getName());

        try {
            operatingSystemService.saveOperatingSystem(operatingSystemCreated);
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return ResponseEntity.badRequest().body("Bad request exeption");
        }

        return ResponseEntity.ok().body("Operating system succesfully created.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperatingSystemEntity> findOperatingSystemById(@PathVariable int id) {
        OperatingSystemEntity operatingSystemEntity = operatingSystemService.findOperatingSystemById(id);

        return ResponseEntity.ok().body(operatingSystemEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteOperatingSystemById(@PathVariable int id) {
        operatingSystemService.deleteOperatingSystemById(id);
    }
}
