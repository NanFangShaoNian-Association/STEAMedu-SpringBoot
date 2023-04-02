package com.nfsn.controller.common;

import com.alipay.api.AlipayApiException;
import com.nfsn.anno.RawResource;
import com.nfsn.model.dto.CreateCouponRequest;
import com.nfsn.model.dto.CreateOrderRequest;
import com.nfsn.model.entity.Choose;
import com.nfsn.model.entity.Coupon;
import com.nfsn.service.CouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author snail
 * @create 2023-03-03  15:15
 */
@RestController
@RequestMapping("/coupon")
@Api(value = "聊天相关接口",tags = "聊天相关接口")
@ApiImplicitParams({
        @ApiImplicitParam(name = "token", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class)
})
public class CouponController {

    @Resource
    private CouponService  couponService;


    //创建优惠卷
    @ApiOperation("创建优惠卷")
    @PostMapping("/addCoupon")
    @RawResource //返回原始数据
    public Coupon addCoupon(@RequestBody CreateCouponRequest createCouponRequest) {
        //创建优惠卷并返回优惠卷信息
        Coupon coupon = couponService.createCoupon(createCouponRequest);
        return coupon;
    }

    @ApiOperation("所有或根据课程名称显示优惠卷")
    @PostMapping("/getAllCoupon")
    @RawResource //返回原始数据
    public List<Coupon> getAllCoupon( String courseName) {
        //根据查询条件返回优惠券消息
        List<Coupon> couponList = couponService.searchGetAllCoupon(courseName);
        return couponList;
    }

//    查看用户id优惠卷
    @ApiOperation("根据用户id查看课程优惠卷")
    @PostMapping("/getCouponInfoByUserId")
    @RawResource //返回原始数据
    public List<Coupon> getCouponInfoByUserId() {
        //获取用户的优惠券列表
        List<Coupon> couponList = couponService.getCouponListInfoById();
        return couponList;
    }


    //领取课程优惠卷
    @ApiOperation("领取课程优惠卷")
    @PostMapping("/getCouponInfoById")
    @RawResource //返回原始数据
    public Boolean getCouponInfoById(Integer couponId) {
        //根据优惠券id进行获取
        return couponService.getCouponInfoById(couponId);



    }

}
