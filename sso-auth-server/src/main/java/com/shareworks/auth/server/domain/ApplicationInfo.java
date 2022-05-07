package com.shareworks.auth.server.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "application_info")
public class ApplicationInfo {
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @TableField(value = "app_id")
    private String appId;

    @TableField(value = "app_secret")
    private String appSecret;

    @TableField(value = "redirect_uri")
    private String redirectUri;

    @TableField(value = "grant_types")
    private String grantTypes;

    @TableField(value = "`status`")
    private String status;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "modify_time")
    private Date modifyTime;
}
