package com.nfsn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.Option;
import com.nfsn.service.OptionService;
import com.nfsn.mapper.OptionMapper;
import org.springframework.stereotype.Service;

/**
* @author Tuanzi
* @description 针对表【option】的数据库操作Service实现
* @createDate 2023-02-09 16:30:53
*/
@Service
public class OptionServiceImpl extends ServiceImpl<OptionMapper, Option>
    implements OptionService{

}




