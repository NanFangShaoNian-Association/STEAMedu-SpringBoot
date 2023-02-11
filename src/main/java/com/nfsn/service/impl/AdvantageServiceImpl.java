package com.nfsn.service.impl;

import com.nfsn.mapper.AdvantageMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.Advantage;
import com.nfsn.service.AdvantageService;
import com.nfsn.mapper.AdvantageMapper;
import org.springframework.stereotype.Service;
import com.nfsn.model.vo.CourseAdvantageVO;

import javax.annotation.Resource;
import java.util.List;

/**
* @author Tuanzi
* @description 针对表【advantage】的数据库操作Service实现
* @createDate 2023-02-09 16:30:52
*/
@Service
public class AdvantageServiceImpl extends ServiceImpl<AdvantageMapper, Advantage>
    implements AdvantageService{

    @Resource
    private AdvantageMapper advantageMapper;

    @Override
    public List<CourseAdvantageVO> selectByCourseId(Integer courseId) {
        //根据课程ID查出所有课程优势
        return advantageMapper.selectByCourseId(courseId);
    }

}




