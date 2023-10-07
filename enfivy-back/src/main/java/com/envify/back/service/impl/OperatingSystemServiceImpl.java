package com.envify.back.service.impl;

import com.envify.back.dao.OperatingSystemDao;
import com.envify.back.entity.OperatingSystemEntity;
import com.envify.back.service.OperatingSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

@Service
@Transactional
public class OperatingSystemServiceImpl implements OperatingSystemService {
    @Autowired
    private OperatingSystemDao operatingSystemDao;

    @Override
    public List<OperatingSystemEntity> findAllOperatingSystems() {
        return operatingSystemDao.findAll();
    }

    @Override
    public void saveOperatingSystem(OperatingSystemEntity operatingSystemEntity) {
        operatingSystemDao.save(operatingSystemEntity);
    }

    @Override
    public OperatingSystemEntity findOperatingSystemById(int id) {
        return operatingSystemDao.getById(id);
    }

    @Override
    public void deleteOperatingSystemById(int id) {
        operatingSystemDao.deleteById(id);
    }
}
