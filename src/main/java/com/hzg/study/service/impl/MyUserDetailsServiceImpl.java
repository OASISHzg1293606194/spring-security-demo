package com.hzg.study.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzg.study.entity.UserInfo;
import com.hzg.study.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package: com.hzg.study.service.impl
 * @FileName: MyUserDetailsServiceImpl.java
 * @ClassName: MyUserDetailsServiceImpl
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-04-20 11:51
 * @Version: v1.0
 */
@Service("userDetailsService")
public class MyUserDetailsServiceImpl implements UserDetailsService {

    private static final String USER_AUTHS = "admin,ROLE_employee,ROLE_normal";
    // private static final String USER_NAME = "Bailey";
    // private static final String USER_PASSWORD = "123";
    //
    // @Override
    // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //     List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(USER_ROLE);
    //     return new User(USER_NAME, new BCryptPasswordEncoder().encode(USER_PASSWORD), auths);
    // }


    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);

        if (ObjectUtil.isNotEmpty(userInfo)) {
            List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(USER_AUTHS);
            return new User(userInfo.getUsername(), new BCryptPasswordEncoder().encode(userInfo.getPassword()), auths);
        }

        throw new UsernameNotFoundException("用户名或密码错误!");
    }

}
