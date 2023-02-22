package com.nfsn.service;

import com.alipay.api.AlipayApiException;
import com.nfsn.model.entity.Choose;

public interface IPayService {
    /**
     * 支付接口
     *
     * @param choose 订单信息
     * @return
     * @throws AlipayApiException
     */
    String pay(Choose choose) throws AlipayApiException;

//    /**
//     * 订单查询接口
//     *
//     * @param queryOrderRequest 订单查询传输实体
//     * @return
//     * @throws AlipayApiException
//     */
//    public String queryOrder(QueryOrderRequest queryOrderRequest) throws AlipayApiException;
//
}
