package com.nfsn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import com.alipay.api.domain.Goods;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.dto.CreateOrderRequest;
import com.nfsn.model.entity.Choose;
import com.nfsn.model.entity.Course;
import com.nfsn.service.ChooseService;
import com.nfsn.mapper.ChooseMapper;
import com.nfsn.service.CourseService;
import com.nfsn.utils.AccountHolder;
import com.nfsn.utils.RandomUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
* @author Tuanzi
* @description 针对表【choose】的数据库操作Service实现
* @createDate 2023-02-09 16:30:52
*/
@Service
public class ChooseServiceImpl extends ServiceImpl<ChooseMapper, Choose>
    implements ChooseService{

    @Resource
    private CourseService courseService;

    /**
     * 创建订单并返回订单信息
     *
     * @param createOrderRequest 创建支付订单传输实体
     * @return 订单信息
     */
    @Override
    public Choose createOrder(CreateOrderRequest createOrderRequest) {
        //查询商品
        Course course = courseService.getOne(new LambdaQueryWrapper<Course>()
                .eq(Course::getCourseId, createOrderRequest.getCourseId()));
        //商品不存在
        if (course == null){
            //todo：抛出商品编号不存在异常
        }
        //封装订单信息
        Choose choose = BeanUtil.copyProperties(createOrderRequest, Choose.class);
        choose.setChooseId(Convert.toInt(RandomUtils.getRandomOfNumber(5)));
        choose.setCourseId(Convert.toInt(createOrderRequest.getCourseId()));
        choose.setChooseNumber(Convert.toInt(RandomUtils.getRandomOfNumber(5)));
        choose.setUserId(AccountHolder.getUser().getUserId());
        choose.setCouponId(1);//todo：此处填入优惠券id
        choose.setPayTime(new Date());
        choose.setChooseCommitTime(new Date());
        choose.setPayMoney(createOrderRequest.getTotalFee());//待支付
        choose.setPayWay(1);//支付宝支付
        choose.setChooseHandleStatus(0);//机构未处理
        choose.setPayStatus(0);//未付款
        //创建订单信息
        this.save(choose);
        return choose;
    }
}




