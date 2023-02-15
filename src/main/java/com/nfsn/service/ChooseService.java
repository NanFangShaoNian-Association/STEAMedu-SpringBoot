package com.nfsn.service;

import com.nfsn.model.dto.CreateOrderRequest;
import com.nfsn.model.entity.Choose;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Tuanzi
* @description 针对表【choose】的数据库操作Service
* @createDate 2023-02-09 16:30:52
*/
public interface ChooseService extends IService<Choose> {
    /**
     * 创建订单并返回订单信息
     *
     * @param createOrderRequest 创建支付订单传输实体
     * @return 订单信息
     */
    Choose createOrder(CreateOrderRequest createOrderRequest);
}
