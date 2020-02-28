package com.epsilon.lx.service.impl;

import com.epsilon.lx.entities.Dept;
import com.epsilon.lx.mapper.DeptMapper;
import com.epsilon.lx.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;


    @Override
    public boolean add(Dept dept) {
        return deptMapper.addDept(dept);
    }

    @Override
    public Dept getDept(Long id) {
        return deptMapper.findById(id);
    }

    @Override
    public List<Dept> DeptList() {
        return deptMapper.findAll();
    }
}
