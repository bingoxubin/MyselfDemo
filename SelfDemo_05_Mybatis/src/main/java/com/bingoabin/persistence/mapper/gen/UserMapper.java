package com.bingoabin.persistence.mapper.gen;

import com.bingoabin.persistence.po.gen.User;
import com.bingoabin.persistence.po.gen.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int insertBatch(@Param("records") List<User> records);
}