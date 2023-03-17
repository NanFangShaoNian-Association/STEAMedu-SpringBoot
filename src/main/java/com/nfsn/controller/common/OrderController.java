package com.nfsn.controller.common;

import com.alipay.api.AlipayApiException;
import com.nfsn.anno.NoNeedLogin;
import com.nfsn.anno.RawResource;
import com.nfsn.constants.ResultCode;
import com.nfsn.model.dto.CreateOrderRequest;
import com.nfsn.model.entity.Choose;
import com.nfsn.model.vo.PendingPaymentCourseVO;
import com.nfsn.model.vo.PendingPaymentOrderInfoVO;
import com.nfsn.model.vo.PendingPaymentStudentInfoVO;
import com.nfsn.model.vo.UsedCouponAmountVO;
import com.nfsn.service.*;
import com.nfsn.utils.AccountHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
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

    @Resource
    private CourseService courseService;

    @Resource
    private CouponService couponService;

    @Resource
    private StudentMessageService studentMessageService;

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

    /**
     * 课程订单详细
     *
     * @param courseId 课程ID
     * @return
     */
    @ApiOperation("课程订单详细")
    @PostMapping("/courseOrderInfo")
    @RawResource // 返回原始数据
    public PendingPaymentOrderInfoVO courseOrderInfo(Integer courseId) {
        // 获取当前登录用户的用户ID
        Integer userId = AccountHolder.getUser().getUserId();

        // 创建一个新的PendingPaymentOrderInfoVO对象
        PendingPaymentOrderInfoVO pendingPaymentOrderInfoVO = new PendingPaymentOrderInfoVO();
        // 通过courseId获取课程详情
        PendingPaymentCourseVO courseDetailById = courseService.getCourseDetailById(courseId);
        // 通过userId获取学生信息
        PendingPaymentStudentInfoVO studentInfoById = studentMessageService.getStudentInfoById(userId);

        // 将课程详情复制到PendingPaymentOrderInfoVO对象中
        BeanUtils.copyProperties(courseDetailById, pendingPaymentOrderInfoVO);
        // 将学生信息复制到PendingPaymentOrderInfoVO对象中
        BeanUtils.copyProperties(studentInfoById, pendingPaymentOrderInfoVO);

        // 返回完整的PendingPaymentOrderInfoVO对象
        return pendingPaymentOrderInfoVO;
    }

    /**
     * 通过优惠券ID查询优惠金额
     * @param couponId 优惠券ID
     * @return UsedCouponAmountVO 包含优惠金额的VO对象，如果没有找到对应的优惠券，则返回null
     */
    @ApiOperation("通过优惠券ID查询优惠金额")
    @GetMapping("/value/{couponId}")
    public UsedCouponAmountVO getValueByCouponId(@PathVariable Integer couponId) {
        return couponService.getValueByCouponId(couponId);
    }

}