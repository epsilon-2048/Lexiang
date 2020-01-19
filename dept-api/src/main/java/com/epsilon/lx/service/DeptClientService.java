package com.epsilon.lx.service;

import com.epsilon.lx.entities.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "DEPT-TEST")
@RequestMapping("/dept")
public interface DeptClientService {
/*

    @RequestMapping(value = "/dept/add",method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept);

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    public Dept get(@PathVariable("id") Long id);

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> deptList();

    @RequestMapping(value = "/dev/get", method = RequestMethod.GET)
    public String getDev();*/
}
