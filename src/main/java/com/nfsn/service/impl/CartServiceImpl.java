package com.nfsn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.Cart;
import com.nfsn.model.entity.Course;
import com.nfsn.model.vo.CartVO;
import com.nfsn.model.vo.TeacherInfoVO;
import com.nfsn.service.CartService;
import com.nfsn.mapper.CartMapper;
import com.nfsn.service.CourseService;
import com.nfsn.service.CourseTeacherRelService;
import com.nfsn.utils.AccountHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author atnibamaitay
* @description 针对表【cart】的数据库操作Service实现
* @createDate 2023-03-19 21:30:52
*/
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    @Resource
    private CourseService courseService;

    @Resource
    private CourseTeacherRelService courseTeacherRelService;

    /**
     * 获取购物车列表
     *
     * @return
     */
    @Override
    public List<CartVO> getCartList() {
        // 获取当前用户ID
        Integer userId = AccountHolder.getUser().getUserId();

        // 获取该用户的购物车中的课程
        List<Cart> carts = this.list(new LambdaQueryWrapper<Cart>().eq(Cart::getUserId, userId));

        // 如果购物车为空，则返回null
        if (carts.isEmpty()) {
            return null;
        }

        // 获取课程及其对应的教师列表并转换为CartVO对象列表
        return carts.stream()
                .map(cart -> {
                    // 根据购物车中的课程ID获取课程信息
                    Course course = courseService.getById(cart.getCourseId());
                    // 根据课程ID获取教师信息列表
                    List<TeacherInfoVO> teacherInfoVOList = courseTeacherRelService.selectTeacherInfoByCourseId(course.getCourseId());
                    // 将课程信息转换为CartVO对象
                    CartVO cartVO = BeanUtil.copyProperties(course, CartVO.class);
                    // 设置CartVO对象的教师列表
                    cartVO.setTeachers(teacherInfoVOList);
                    // 返回CartVO对象
                    return cartVO;
                })
                .collect(Collectors.toList());
    }

    /**
     * 删除购物车中的课程
     *
     * @param ids 课程id【列表】
     */
    @Override
    public void deleteCartList(List<String> ids) {
        // FIXME:存在报错
        if (ids.size() == 0) {
            return;
        }
        List<Integer> idList = ids.stream().distinct().map(s -> Convert.toInt(s, -1)).collect(Collectors.toList());
        this.removeByIds(idList);
    }

}