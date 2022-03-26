package com.bingoabin.service.base.impl;

import com.bingoabin.persistence.mapper.gen.UserMapper;
import com.bingoabin.persistence.po.gen.User;
import com.bingoabin.persistence.po.gen.UserExample;
import com.bingoabin.service.base.UserBaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: xubin34
 * @Date: 2022/3/23 10:14 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
@Service
public class UserBaseServiceImpl implements UserBaseService {
	@Resource
	private UserMapper userMapper;

	@Override
	public List<User> getUser(int id) {
		UserExample example = new UserExample();
		example.createCriteria().andIdEqualTo(id);
		List<User> users = userMapper.selectByExample(example);
		return users;
	}
}

