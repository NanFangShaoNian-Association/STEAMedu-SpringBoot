package com.nfsn.service;

import com.nfsn.model.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nfsn.model.vo.CartVO;

import java.util.List;

/**
* @author Tuanzi
* @description 针对表【cart】的数据库操作Service
* @createDate 2023-02-09 16:30:52
*/
public interface CartService extends IService<Cart> {

    List<CartVO> getCartList();

    void deleteCartList(List<String> ids);
}
