package com.epsilon.lx.service.impl;

import com.epsilon.lx.entities.UserOperation;
import com.epsilon.lx.entities.UserOperationExample;
import com.epsilon.lx.entities.UserRecharge;
import com.epsilon.lx.mapper.UserOperationMapper;
import com.epsilon.lx.mapper.UserRechargeMapper;
import com.epsilon.lx.service.IUserOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IUserOperationServiceImpl implements IUserOperationService {

    @Autowired
    private UserOperationMapper operationMapper;

    @Autowired
    private UserRechargeMapper rechargeMapper;
    /**
     * 用户点赞\\关注\观看记录\购买记录
     *
     * @param operation
     * @return
     */
    @Override
    public boolean userOperation(UserOperation operation) {
        return operationMapper.insertSelective(operation) > 0;
    }

    /**
     * 用户充值
     *
     * @param recharge
     * @return
     */
    @Override
    public boolean userRecharge(UserRecharge recharge) {
        return rechargeMapper.insertSelective(recharge) > 0;
    }

    /**
     * 获取用户点赞\\关注\观看记录\购买记录
     *
     * @param operation
     * @return
     */
    @Override
    public List<UserOperation> getOperationRecordList(UserOperation operation) {
        return operationMapper.selectByExample(getUserOperationExample(operation));
    }

    private UserOperationExample getUserOperationExample(UserOperation operation) {
        UserOperationExample operationExample = new UserOperationExample();
        if (operation.getId() != null){
            operationExample.createCriteria().andIdEqualTo(operation.getId());
        }
        if (operation.getOperation() != null){
            operationExample.createCriteria().andOperationEqualTo(operation.getOperation());
        }
        if (operation.getUserId() != null){
            operationExample.createCriteria().andUserIdEqualTo(operation.getUserId());
        }
        if (operation.getVideoId() != null){
            operationExample.createCriteria().andVideoIdEqualTo(operation.getVideoId());
        }
        return operationExample;
    }
}
