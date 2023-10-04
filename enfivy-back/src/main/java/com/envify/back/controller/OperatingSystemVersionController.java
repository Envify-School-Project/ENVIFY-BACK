package com.envify.back.controller;

import com.envify.back.dto.OperatingSystemVersionDto;
import com.envify.back.entity.OperatingSystemVersionEntity;
import com.envify.back.entity.PackageVersionEntity;
import com.envify.back.service.OperatingSystemVersionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/os/{osId}/versions")
public class OperatingSystemVersionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperatingSystemVersionController.class);

    @Autowired
    private OperatingSystemVersionService operatingSystemVersionService;

    @GetMapping()
    public ResponseEntity<List<OperatingSystemVersionEntity>> findAllOperatingSystemVersions(@PathVariable("osId") int osId) {
        List<OperatingSystemVersionEntity> operatingSystemVersions = operatingSystemVersionService.findAllOperatingSystemVersions(osId);

        return ResponseEntity.ok().body(operatingSystemVersions);
    }

    @PostMapping()
    public ResponseEntity<String> createOperatingSystemVersion(@PathVariable("osId") int osId, @RequestBody OperatingSystemVersionDto operatingSystemVersionDto) {
        final OperatingSystemVersionEntity operatingSystemVersionEntity = new OperatingSystemVersionEntity();
        operatingSystemVersionEntity.setVersionNumber(operatingSystemVersionDto.getVersionNumber());
        operatingSystemVersionEntity.setOperatingSystemId(osId);

        try {
            operatingSystemVersionService.saveOperatingSystemVersion(operatingSystemVersionEntity);
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return ResponseEntity.badRequest().body("Bad request exeption");
        }

        return ResponseEntity.ok().body("Operating system version succesfully created.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperatingSystemVersionEntity> findOperationSystemVersionById(@PathVariable int id) {
        OperatingSystemVersionEntity operatingSystemVersionEntity = operatingSystemVersionService.findOperatingSystemVersionById(id);

        return ResponseEntity.ok().body(operatingSystemVersionEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteOperatingSystemVersionById(@PathVariable int id) { operatingSystemVersionService.deleteOperatingSystemVersionById(id); }
}
