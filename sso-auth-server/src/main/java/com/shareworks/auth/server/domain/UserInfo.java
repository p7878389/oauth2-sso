package com.shareworks.auth.server.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shareworks.auth.server.enums.UserStatusEnums;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "user_info")
public class UserInfo {
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @TableField(value = "user_name")
    private String userName;

    @TableField(value = "`password`")
    private String password;

    @TableField(value = "email")
    private String email;

    @TableField(value = "`status`")
    private UserStatusEnums status;

    @TableField(value = "last_login_time")
    private Date lastLoginTime;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "modify_time")
    private Date modifyTime;
}
