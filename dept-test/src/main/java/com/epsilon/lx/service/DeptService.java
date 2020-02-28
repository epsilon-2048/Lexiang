package com.epsilon.lx.service;

import com.epsilon.lx.entities.Dept;

import java.util.List;

public interface DeptService {

    boolean add(Dept dept);
    Dept getDept(Long id);
    List<Dept> DeptList();
}
