package com.nfsn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.dto.CreateCouponRequest;
import com.nfsn.model.entity.*;
import com.nfsn.model.vo.UsedCouponAmountVO;
import com.nfsn.service.CouponService;
import com.nfsn.mapper.CouponMapper;
import com.nfsn.service.CourseService;
import com.nfsn.service.UserCouponService;
import com.nfsn.utils.AccountHolder;
import com.nfsn.utils.RandomUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author Tuanzi
* @description 针对表【coupon】的数据库操作Service实现
* @createDate 2023-02-09 16:30:52
*/
@Service
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon>
    implements CouponService{


    @Resource
    private CourseService courseService;

    @Resource
    private UserCouponService userCouponService;

    @Resource
    private CouponMapper couponMapper;

    @Override
    public Coupon createCoupon(CreateCouponRequest createCouponRequest) {
        //查询是否存在课程

        Course course = courseService.getOne(new LambdaQueryWrapper<Course>()
                .eq(Course::getCourseId, createCouponRequest.getDesignatedCourseId())
        );

        if (course ==null){
            // TODO: 2023/3/3 判断不存在时的异常（待做）

        }

        //封装优惠券信息
        Coupon coupon = BeanUtil.copyProperties(createCouponRequest,Coupon.class);
        coupon.setCouponId(Convert.toInt(RandomUtils.getRandomOfNumber(5)));
        coupon.setIssuerUserId(AccountHolder.getUser().getUserId());
        coupon.setCouponCode(Convert.toStr(RandomUtils.getRandomOfLetterAndNumber(8)));
        coupon.setStatus(1);
        this.save(coupon);
        return coupon;
    }

    @Override
    public List<Coupon> searchGetAllCoupon(String courseName) {

        if (courseName != null){
            List<Course> courses = courseService.list(new LambdaQueryWrapper<Course>()
                    .like(Course::getCourseName, courseName));
            //提取ids
            List<Integer> ids = courses.stream().map(Course::getCourseId).collect(Collectors.toList());
            List<Coupon> couponList = this.list(new LambdaQueryWrapper<Coupon>().in(Coupon::getDesignatedCourseId, ids));
            return couponList;
        }
        List<Coupon> couponList = this.list();
        return couponList;
    }

    @Override
    public List<Coupon> getCouponListInfoById() {
        User user = AccountHolder.getUser();

        List<Coupon> couponList = new ArrayList<>();

        //获取用户的优惠券列表
        List<UserCoupon> userCoupons = userCouponService.list(new LambdaQueryWrapper<UserCoupon>()
                .eq(UserCoupon::getUserId, user.getUserId()));
        List<Integer> couponIds = userCoupons.stream().map(UserCoupon::getCouponId).collect(Collectors.toList());
        if (couponIds.size() == 0){

            return couponList;
        }
        //获取对应优惠卷的详细信息
        couponList = this.listByIds(couponIds);

        return couponList;
    }


    @Override
    public Boolean getCouponInfoById(Integer couponId) {
        //判断是否存在优惠券
        Coupon coupon = this.getById(couponId);
        if (coupon == null){
            return false;
        }

        //判断是否已经领取
        UserCoupon userCouponOne = userCouponService.getOne(new LambdaQueryWrapper<UserCoupon>().eq(UserCoupon::getCouponId, couponId).
                eq(UserCoupon::getUserId, AccountHolder.getUser().getUserId()));
        if (userCouponOne ==null){
            UserCoupon userCoupon = new UserCoupon();
            userCoupon.setCouponId(couponId);
            userCoupon.setUserId(AccountHolder.getUser().getUserId());

            return userCouponService.save(userCoupon);
        }
        return false;

    }

    /**
     * 通过优惠券ID查询优惠金额
     * @param couponId 优惠券ID
     * @return UsedCouponAmountVO 包含优惠金额的VO对象，如果没有找到对应的优惠券，则返回null
     */
    @Override
    public UsedCouponAmountVO getValueByCouponId(Integer couponId) {
        Coupon coupon = couponMapper.selectById(couponId);
        if (coupon != null) {
            UsedCouponAmountVO usedCouponAmountVO = new UsedCouponAmountVO();
            usedCouponAmountVO.setValue(coupon.getValue());
            return usedCouponAmountVO;
        }
        return null;
    }
}




