package com.envify.back.service.impl;

import com.envify.back.dao.OperatingSystemVersionDao;
import com.envify.back.entity.OperatingSystemVersionEntity;
import com.envify.back.service.OperatingSystemVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

@Service
@Transactional
public class OperatingSystemVersionSystemImpl implements OperatingSystemVersionService {

    @Autowired
    private OperatingSystemVersionDao operatingSystemVersionDao;

    @Override
    public List<OperatingSystemVersionEntity> findAllOperatingSystemVersions(int operatingSystemId) {
        return operatingSystemVersionDao.findOperatingSystemVersionEntitiesByOperatingSystemId(operatingSystemId);
    }

    @Override
    public void saveOperatingSystemVersion(OperatingSystemVersionEntity operatingSystemVersionEntity) {
        operatingSystemVersionDao.save(operatingSystemVersionEntity);
    }

    @Override
    public OperatingSystemVersionEntity findOperatingSystemVersionById(int id) {
        return operatingSystemVersionDao.getById(id);
    }

    @Override
    public void deleteOperatingSystemVersionById(int id) {
        operatingSystemVersionDao.deleteById(id);
    }
}
