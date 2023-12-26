package com.example.warehouse.Filter;

import com.alibaba.fastjson.JSON;
import com.example.warehouse.utils.WarehouseConstants;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class DengluFilter implements Filter {
    private StringRedisTemplate stringRedisTemplate;

    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        List li=new ArrayList<>();
        li.add("/captcha/captchaImage");
        li.add("/login");
        li.add("/loginout");
        li.add("/product/img-upload");
        String servletPath = request.getServletPath();
        if(li.contains(servletPath)||servletPath.contains("/img/upload")){
            filterChain.doFilter(request,response);
        }else{
            String header = request.getHeader(WarehouseConstants.HEADER_TOKEN_NAME);
            if(StringUtils.hasText(header)&&header.equals(stringRedisTemplate.opsForValue().get("token"))){
                filterChain.doFilter(request,response);
            }else{
                response.setContentType("application/json");
                PrintWriter writer = response.getWriter();
                String s="redis没有找到对应数据,请求失败";
                writer.write(JSON.toJSONString(s));
                writer.flush();
                writer.close();
            }
        }
    }
}
