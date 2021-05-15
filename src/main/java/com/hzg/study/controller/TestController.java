package com.hzg.study.controller;

import com.hzg.study.entity.UserInfo;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.hzg.study.controller
 * @FileName: TestController.java
 * @ClassName: TestController
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-04-18 0:08
 * @Version: v1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring Security!";
    }

    @GetMapping("/index")
    public void index(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendRedirect("/index.html");
    }

    @GetMapping("/queryOne")
    public String queryOne() {
        return "queryOne";
    }

    @GetMapping("/queryList")
    public String queryList() {
        return "queryList";
    }

    @GetMapping("/selectAll")
    public String selectAll() {
        return "selectAll";
    }

    @GetMapping("/update")
    public String update() {
        return "update";
    }

    @GetMapping("/testSecured")
    @Secured({"ROLE_normal", "ROLE_admin"})
    public String testSecured() {
        return "testSecured";
    }

    @GetMapping("/testPreAuthorize")
    // @PreAuthorize("hasRole('ROLE_ admin')")
    @PreAuthorize("hasAnyAuthority('admin')")
    public String testPreAuthorize() {
        return "testPreAuthorize";
    }

    @GetMapping("/testPostAuthorize")
    // @PostAuthorize("hasRole('ROLE_normal')")
    @PostAuthorize("hasAnyAuthority('admins')")
    public String testPostAuthorize() {
        System.out.println("方法执行后再进行权限验证");
        return "testPostAuthorize";
    }

    @GetMapping("/testPostFilter")
    @PreAuthorize("hasAnyAuthority('admin')")
    @PostFilter("filterObject.username == 'Lily'")
    public List<UserInfo> testPostFilter() {
        List<UserInfo> userInfoList = new ArrayList<>();
        userInfoList.add(new UserInfo(1L, "Lily", "0207"));
        userInfoList.add(new UserInfo(2L, "Bailey", "123"));
        return userInfoList;
    }

    @PostMapping("/testPreFilter")
    @PreAuthorize("hasAnyAuthority('admin')")
    @PreFilter("filterObject.id % 2 == 0")
    public List<UserInfo> testPreFilter(@RequestBody List<UserInfo> userInfoList) {
        userInfoList.forEach(u -> {
            System.out.println(u.toString());
        });
        return userInfoList;
    }

}
