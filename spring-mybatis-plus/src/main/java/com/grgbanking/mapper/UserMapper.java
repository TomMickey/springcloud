package com.grgbanking.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.grgbanking.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
