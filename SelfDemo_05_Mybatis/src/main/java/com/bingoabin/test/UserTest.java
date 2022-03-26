package com.bingoabin.test;

import com.bingoabin.persistence.po.gen.User;
import com.bingoabin.service.base.UserBaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: xubin34
 * @Date: 2022/3/23 10:25 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
	@Resource
	private UserBaseService userBaseService;

	@Test
	public void test(){
		List<User> user = userBaseService.getUser(1);
		System.out.println(user);
	}
}
