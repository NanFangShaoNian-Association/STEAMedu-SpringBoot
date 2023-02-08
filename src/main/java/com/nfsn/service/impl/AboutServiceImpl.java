package com.nfsn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfsn.model.entity.About;
import com.nfsn.service.AboutService;
import com.nfsn.mapper.AboutMapper;
import org.springframework.stereotype.Service;

/**
* @author Tuanzi
* @description 针对表【about】的数据库操作Service实现
* @createDate 2023-02-07 14:24:32
*/
@Service
public class AboutServiceImpl extends ServiceImpl<AboutMapper, About>
    implements AboutService{

}




