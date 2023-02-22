package com.nfsn.controller.common;

import com.alipay.api.AlipayApiException;
import com.nfsn.anno.RawResource;
import com.nfsn.constants.ResultCode;
import com.nfsn.model.dto.CreateOrderRequest;
import com.nfsn.model.entity.Choose;
import com.nfsn.service.ChooseService;
import com.nfsn.service.IPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName: OrderController
 * @Author: 团子tz
 * @CreateTime: 2023/02/15 17:09
 * @Description: 订单操作类
 */
@RestController
@RequestMapping("/orders")
@Api("订单操作类")
@ApiImplicitParams({
        @ApiImplicitParam(name = "token", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class)
})
public class OrderController {
    @Resource
    private ChooseService chooseService;

    @Resource(name = "aliPayService")
    private IPayService payService;

    //创建订单
    @ApiOperation("创建订单")
    @PostMapping("/addOrder")
    @RawResource //返回原始数据
    public String addOrder(@RequestBody CreateOrderRequest createOrderRequest) throws AlipayApiException {
        //创建订单并返回订单信息
        Choose choose = chooseService.createOrder(createOrderRequest);
        //发起支付
        return payService.pay(choose);
    }
//
//    //获取订单列表
//    @ApiOperation("获取订单列表")
//    @GetMapping("/list")
//    public List<UserOrderListVO> list() {
//        return orderInfoService.listOrder();
//    }
//
//    //获取商品订单详情
//    @ApiOperation("获取订单详情")
//    @GetMapping("/getOrder/{orderId}")
//    public UserOrderVO getOrder(@PathVariable("orderId") String orderId) {
//        Integer value = 0;
//        try {
//            value = Integer.valueOf(orderId);
//        } catch (NumberFormatException e) {
//            log.error("UserOrderController getOrder NumberFormatException : ", e);
//            throw new UserArticleException(ResultCode.PARAM_IS_INVALID);
//        }
//        return orderInfoService.getOrder(value);
//    }

}
