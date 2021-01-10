package com.grgbanking.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.UUID;

@Data
@TableName("tb_user")
public class User {
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    private String userName;
    private String password;
}
