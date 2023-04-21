package com.nfsn.service;

import com.nfsn.model.dto.ExamRequest;
import com.nfsn.model.dto.ExamSubmissionRequest;
import com.nfsn.model.entity.Exam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nfsn.model.vo.ExamToEvenVo;
import com.nfsn.model.vo.ExamVo;
import com.nfsn.model.vo.HomeworkToEvenVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
* @author 温格
* @description 针对表【exam】的数据库操作Service
* @createDate 2023-04-10 09:52:02
*/
public interface ExamService extends IService<Exam> {

    /**
     * 新增考试
     * @param examRequest
     */
    void addExam( ExamRequest examRequest);

    /**
     * 发布考试
     * @param examId
     */
    void putExam( Integer examId);

    /**
     * 获取指定考试信息
     * @param examId
     * @return
     */
    ExamVo getExamToMy(Integer examId);

    /**
     * 提交试卷
     * @param examSubmissionRequest
     */
    Double updateExamSubmit(ExamSubmissionRequest examSubmissionRequest);

    /**
     * 获取所有考试
     * @return
     */
    List<ExamToEvenVo> getAllExam();

}
