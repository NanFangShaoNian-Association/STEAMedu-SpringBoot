package com.nfsn.service.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.nfsn.model.dto.AlipayBean;
import com.nfsn.model.entity.Choose;
import com.nfsn.service.IPayService;
import com.nfsn.utils.AlipayUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service("aliPayService")
public class AliPayServiceImpl implements IPayService {

    @Resource
    private AlipayUtil alipayUtil;

    /**
     * 支付宝支付接口
     *
     * @param choose 订单信息
     * @return
     * @throws AlipayApiException
     */
    @Override
    public String pay(Choose choose) throws AlipayApiException {
        //封装支付实体
        AlipayBean alipayBean = new AlipayBean(String.valueOf(choose.getChooseId()),//订单id
                "课程-"+choose.getChooseId(),//订单标题
                String.valueOf(choose.getPayMoney()),//订单金额
                null, null, null);
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();

        // 封装业务参数
        alipayRequest.setBizContent(JSON.toJSONString(alipayBean));

        //发起支付
        String body = alipayUtil.tradeAppPay(alipayRequest);

        return body;
    }
}