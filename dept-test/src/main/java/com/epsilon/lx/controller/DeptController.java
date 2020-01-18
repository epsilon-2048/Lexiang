package com.epsilon.lx.controller;

import com.epsilon.lx.entities.Dept;
import com.epsilon.lx.service.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Controller与ResponseBody的结合体
@Api
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Value("${spring.profiles.active}")
    private String dev;


    @ApiOperation(value="新增部门", notes="已测试")
    @ApiImplicitParam(name = "dept", value = "部门实体", required = true, dataType = "Dept")
    @RequestMapping(value = "/dept/add",method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept){
        return deptService.add(dept);
    }

    @ApiOperation(value="新增部门", notes="已测试")
    @ApiImplicitParam(name = "id", value = "部门实体", required = true, dataType = "Long")
    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    public Dept get(@PathVariable("id") Long id){
        System.out.println(8003);
        return deptService.getDept(id);
    }

    @ApiOperation(value="获取部门列表", notes="已测试")
    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> deptList(){
        return deptService.DeptList();
    }

    @ApiOperation(value="获取来自哪个实例", notes="已测试")
    @RequestMapping(value = "/dev/get", method = RequestMethod.GET)
    public String getDev(){
        return dev;
    }
}
