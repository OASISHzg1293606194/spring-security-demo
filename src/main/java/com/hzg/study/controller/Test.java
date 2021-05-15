package com.hzg.study.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Package: com.hzg.study.controller
 * @FileName: Test.java
 * @ClassName: Test
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-04-18 13:34
 * @Version: v1.0
 */
public class Test {

    public static void main(String[] args) {
        String password = "root";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        // 密码加密
        String encodePswd = bCryptPasswordEncoder.encode(password);
        System.out.printf("加密后的密码：%s", encodePswd);
        System.out.println();
        // 判断明文密码与加密密码加密前的密码是否匹配
        boolean matches = bCryptPasswordEncoder.matches(password, encodePswd);
        System.out.printf("比较结果：%s", matches);
    }

}
