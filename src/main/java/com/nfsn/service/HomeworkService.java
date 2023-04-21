package com.nfsn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nfsn.model.dto.HomeworkRequest;
import com.nfsn.model.dto.HomeworkSubmitRequest;
import com.nfsn.model.entity.Homework;
import com.nfsn.model.entity.HomeworkSubmit;
import com.nfsn.model.vo.HomeworkToEvenVO;
import com.nfsn.model.vo.HomeworkVO;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

/**
* @author 温格
* @description 针对表【homework(作业表)】的数据库操作Service
* @createDate 2023-04-10 09:18:59
*/
public interface HomeworkService extends IService<Homework> {


    /**
     * 新增作业草稿
     * @param homeworkRequest
     */
    void addHomeworkToDraft(HomeworkRequest homeworkRequest);


    /**
     * 发布作业
     * @param homeworkId
     */
    void putHomework(Integer homeworkId);


    /**
     * 修改截止时间
     * @param homeworkId
     * @param newTime
     */
    void updateHomeworkTime(Integer homeworkId,Date newTime);

    /**
     * 获取指定作业数据
     * @param homeworkId
     * @return
     */
    HomeworkVO getHomeworkToMy(@PathVariable Integer homeworkId);


    /**
     * 提交作业，并返回分数
     * @param homeworkSubmitRequest
     * @return
     */
    Double updateHomeworkSubmit(HomeworkSubmitRequest homeworkSubmitRequest);

    /**
     * 获取所有作业
     * @return
     */
    List<HomeworkToEvenVO> getAllHomework();

    /**
     * 修改分数
     * @param answerJsonString
     * @return
     */
    Double correct(String answerJsonString);
}
