package com.epsilon.lx.controller;

import com.epsilon.lx.entities.UserOperation;
import com.epsilon.lx.entities.UserRecharge;
import com.epsilon.lx.service.IUserOperationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("operation")
@Api
public class UserOperationController {

    @Autowired
    private IUserOperationService userOperationService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "记录用户点赞、观看、收藏")
    public void userOperation(@RequestBody UserOperation operation){
        userOperationService.userOperation(operation);
    }

    @GetMapping("user/{id}/type/{type}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取用户点赞、观看、收藏")
    public List<UserOperation> userOperation(@PathVariable("id") Long id, @PathVariable("type")String type){
        UserOperation operation = new UserOperation();
        operation.setUserId(id);
        operation.setOperation(type);
        return userOperationService.getOperationRecordList(operation);
    }

    @PostMapping("recharge")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "用户充值")
    public void userRecharge(@RequestBody UserRecharge recharge) {
        userOperationService.userRecharge(recharge);
    }
}
