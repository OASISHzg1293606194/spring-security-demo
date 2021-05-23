package com.hzg.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Package: com.hzg.study.controller
 * @FileName: CSRFController.java
 * @ClassName: CSRFController
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-05-21 16:55
 * @Version: v1.0
 */
@RestController
@RequestMapping("/csrf")
public class CSRFController {

    @GetMapping("/showToken")
    public void showToken(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendRedirect("/csrf/csrf-token.html");
    }

}
