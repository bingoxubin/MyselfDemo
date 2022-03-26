package com.bingoabin.service.base;

import com.bingoabin.persistence.po.gen.User;

import java.util.List;

/**
 * @Author: xubin34
 * @Date: 2022/3/23 10:14 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public interface UserBaseService {
	List<User> getUser(int id);
}
