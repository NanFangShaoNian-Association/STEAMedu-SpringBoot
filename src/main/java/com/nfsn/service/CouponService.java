package com.nfsn.service;

import com.nfsn.model.dto.CreateCouponRequest;
import com.nfsn.model.entity.Coupon;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nfsn.model.vo.UsedCouponAmountVO;

import java.util.List;

/**
 * @author Tuanzi
 * @description 针对表【coupon】的数据库操作Service
 * @createDate 2023-02-09 16:30:52
 */
public interface CouponService extends IService<Coupon> {

    /**
     * 创建优惠卷
     *
     * @param createCouponRequest
     * @return 优惠券实体
     */
    Coupon createCoupon(CreateCouponRequest createCouponRequest);

    /**
     * 根据条件查询优惠券
     *
     * @param courseName 课程名称
     * @return
     */
    List<Coupon> searchGetAllCoupon(String courseName);

    /**
     * 根据用户id查看课程优惠卷
     *
     * @return
     */
    List<Coupon> getCouponListInfoById();

    /**
     * 领取优惠券
     *
     * @param couponId 优惠券ID
     * @return
     */
    Boolean getCouponInfoById(Integer couponId);

    /**
     * 使用优惠券
     *
     * @param couponId 优惠券ID
     * @return
     */
    UsedCouponAmountVO getValueByCouponId(Integer couponId);

}
