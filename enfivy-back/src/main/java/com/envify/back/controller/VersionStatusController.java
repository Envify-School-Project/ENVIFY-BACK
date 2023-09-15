package com.envify.back.controller;

import com.envify.back.dao.VersionStatusDao;
import com.envify.back.dto.VersionStatusDto;
import com.envify.back.entity.PackageVersionEntity;
import com.envify.back.entity.VersionStatusEntity;
import com.envify.back.service.VersionStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/versions_status")
public class VersionStatusController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VersionStatusController.class);

    @Autowired
    private VersionStatusService versionStatusService;

    @GetMapping("")
    public ResponseEntity<List<VersionStatusEntity>> findAllVersionStatus() {
        List<VersionStatusEntity> versionStatuses = versionStatusService.findAllVersionsStatuses();

        return ResponseEntity.ok().body(versionStatuses);
    }

    @PostMapping("")
    public ResponseEntity<String> createVersionStatus(@RequestBody VersionStatusDto versionStatusDto) {
        final VersionStatusEntity versionStatus = new VersionStatusEntity();
        versionStatus.setLabel(versionStatusDto.getLabel());

        try {
            versionStatusService.saveVersionStatus(versionStatus);
        } catch (Exception e) {
            LOGGER.error(e.toString());
            ResponseEntity.badRequest().body("Bad request exception");
        }

        return ResponseEntity.ok().body("New version status successfully created");
    }
}
