package com.hzg.study.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package: com.hzg.study.entity
 * @FileName: UserInfo.java
 * @ClassName: UserInfo
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-04-20 15:32
 * @Version: v1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

}
