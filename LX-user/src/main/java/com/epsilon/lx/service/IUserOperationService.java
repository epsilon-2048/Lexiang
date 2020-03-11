package com.epsilon.lx.service;

import com.epsilon.lx.entities.UserOperation;
import com.epsilon.lx.entities.UserRecharge;

import java.util.List;

public interface IUserOperationService {

    /**
     * 用户点赞\\关注\观看记录\购买记录
     * @param operation
     * @return
     */
    boolean userOperation(UserOperation operation);

    /**
     * 用户充值
     * @param recharge
     * @return
     */
    boolean userRecharge(UserRecharge recharge);

    /**
     * 获取用户点赞\\关注\观看记录\购买记录
     * @param operation
     * @return
     */
    List<UserOperation> getOperationRecordList(UserOperation operation);
}
