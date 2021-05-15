package com.hzg.study.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Package: com.hzg.study.service.impl
 * @FileName: MyAccessDeniedHandler.java
 * @ClassName: MyAccessDeniedHandler
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-04-21 15:00
 * @Version: v1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    private String accessDeniedUrl;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 重定向到403页面
        response.sendRedirect(accessDeniedUrl);
        // TODO 可以设置信息
        // request.getSession().setAttribute("message", "You do not have permission to access this page!");
    }

}
