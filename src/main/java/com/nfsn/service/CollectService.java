package com.nfsn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nfsn.model.entity.Collect;
import com.nfsn.model.vo.CollectToCourseInfoVo;

import java.util.List;

/**
 * @author snail
 * * @description 针对表【Collect】的数据库操作Service
 * @create 2023-03-22  9:12
 */

public interface CollectService extends IService<Collect> {

    /**
     * 查看课程收藏列表
     * @return
     */
    List<CollectToCourseInfoVo> getCollectList();


    /**
     * 添加课程到收藏
     * @param courseId
     */
    void addCourseToCollect(Integer courseId);

    /**
     * 删除课程收藏
     * @param courseId
     */
    void delCourseToCollect(Integer courseId);
}
