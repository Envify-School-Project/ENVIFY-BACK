package com.envify.back.service.impl;

import com.envify.back.dao.VersionStatusDao;
import com.envify.back.entity.VersionStatusEntity;
import com.envify.back.service.VersionStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class VersionStatusServiceImpl implements VersionStatusService {

    @Autowired
    private VersionStatusDao versionStatusDao;

    @Override
    public List<VersionStatusEntity> findAllVersionsStatuses() { return versionStatusDao.findAll(); }

    @Override
    public void saveVersionStatus(VersionStatusEntity versionStatus) { versionStatusDao.save(versionStatus); }
}
