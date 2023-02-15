package com.nfsn.utils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.*;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.nfsn.config.AlipayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class AlipayUtil implements BeanPostProcessor {
    @Autowired
    private AlipayConfig alipayConfig;

    @Autowired
    private AlipayClient alipayClient;


    /**
     * app支付接口2.0
     *
     * @param alipayRequest 支付订单交易实体
     * @return 返回订单支付页面
     * @throws AlipayApiException
     */
    public String tradeAppPay(AlipayTradePagePayRequest alipayRequest) throws AlipayApiException {
        // 页面跳转同步通知页面路径
        alipayRequest.setReturnUrl(alipayConfig.getReturnUrl());
        // 服务器异步通知页面路径
        alipayRequest.setNotifyUrl(alipayConfig.getNotifyUrl());

        // 3、请求支付宝进行付款，并获取支付结果
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        // 返回付款信息
        return result;
    }

    /**
     * 统一收单交易查询
     *
     * @param alipayRequest 查询订单请求实体
     * @return
     * @throws AlipayApiException
     */
    public String tradeQuery(AlipayTradeQueryRequest alipayRequest) throws AlipayApiException {
//        bizContent.put("out_trade_no", "2021081722001419121412730660");
        // 请求支付宝进行订单查询，并获取结果
        AlipayTradeQueryResponse response = alipayClient.execute(alipayRequest);
        return response.getBody();
    }

    /**
     * 统一收单交易退款接口
     *
     * @param alipayRequest 订单退款请求实体
     * @return
     * @throws AlipayApiException
     */
    public String tradeRefund(AlipayTradeRefundRequest alipayRequest) throws AlipayApiException {
//        bizContent.put("trade_no", "2021081722001419121412730660");
//        bizContent.put("refund_amount", 0.01);
//        bizContent.put("out_request_no", "HZ01RF001");

        return alipayClient.execute(alipayRequest).getBody();
    }

    /**
     * 统一收单交易退款接口
     *
     * @param alipayRequest 查询退款订单请求实体
     * @return
     * @throws AlipayApiException
     */
    public String tradeFastPayRefundQuery(AlipayTradeFastpayRefundQueryRequest alipayRequest) throws AlipayApiException {

//        bizContent.put("trade_no", "2021081722001419121412730660");
//        bizContent.put("out_request_no", "HZ01RF001");

        return alipayClient.execute(alipayRequest).getBody();
    }

    /**
     * 统一收单交易关闭接口
     *
     * @param alipayRequest 订单关闭请求实体
     * @return
     * @throws AlipayApiException
     */
    public String tradeClose(AlipayTradeCloseRequest alipayRequest) throws AlipayApiException {

//        bizContent.put("trade_no", "2013112611001004680073956707");

        return alipayClient.execute(alipayRequest).getBody();
    }


    /**
     * 查询对账单下载地址
     *
     * @param alipayRequest 查询对账单请求实体
     * @return
     * @throws AlipayApiException
     */
    public String billDownloadUrlQuery(AlipayDataDataserviceBillDownloadurlQueryRequest alipayRequest) throws AlipayApiException {
//        request.setBizContent("{" +
//                "  \"bill_type\":\"trade\"," +
//                "  \"bill_date\":\"2016-04-05\"," +
//                "  \"smid\":\"2088123412341234\"" +
//                "}");

        return alipayClient.execute(alipayRequest).getBody();
    }


}
