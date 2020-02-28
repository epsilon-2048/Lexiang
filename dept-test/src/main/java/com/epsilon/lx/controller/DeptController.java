package com.epsilon.lx.controller;

import com.epsilon.lx.entities.BaseUser;
import com.epsilon.lx.entities.Dept;
import com.epsilon.lx.enums.ErrorCode;
import com.epsilon.lx.exception.InternalServerErrorException;
import com.epsilon.lx.exception.NotFoundException;
import com.epsilon.lx.service.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Controller与ResponseBody的结合体
@Api
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Value("${spring.profiles.active}")
    private String dev;


    @ApiOperation(value="新增部门", notes="已测试")
    @ApiImplicitParam(name = "dept", value = "部门实体", required = true, dataType = "Dept")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public boolean add(@RequestBody Dept dept){
        return deptService.add(dept);
    }

    @ApiOperation(value="新增部门", notes="已测试")
    @ApiImplicitParam(name = "id", value = "部门实体", required = true, dataType = "Long")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Dept get(@PathVariable("id") Long id){
       // System.out.println(8003);
        return deptService.getDept(id);
    }

    @ApiOperation(value="获取部门列表", notes="已测试")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Dept> deptList(){
        return deptService.DeptList();
    }

    @ApiOperation(value="获取来自哪个实例", notes="已测试")
    @RequestMapping(value = "/dev/get", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getDev(@AuthenticationPrincipal UserDetails user){
        if (user != null)
            return dev +" id: "+((BaseUser)user).getId();
        else return dev;
    }

    @ApiOperation(value="测试抛出异常", notes="已测试")
    @RequestMapping(value = "/dev/ex404/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getEx404(@PathVariable("id") int id) throws Exception {
        throw new NotFoundException(ErrorCode.USER_NOT_FOUND, String.valueOf(id));
    }

    @ApiOperation(value="测试抛出异常", notes="已测试")
    @RequestMapping(value = "/dev/ex505/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getEx500(@PathVariable("id") int id) throws Exception {
        throw new InternalServerErrorException(ErrorCode.FILE_UPLOAD_FAILED, String.valueOf(id));
    }
}
