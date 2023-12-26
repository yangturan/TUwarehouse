package com.example.warehouse.controller;

import com.example.warehouse.mapper.LoginMapper;
import com.example.warehouse.pojo.result.Result;
import com.example.warehouse.pojo.users.CurrentUser;
import com.example.warehouse.pojo.users.LoginUser;
import com.example.warehouse.pojo.users.User;
import com.example.warehouse.service.LoginService;
import com.example.warehouse.utils.DigestUtil;
import com.example.warehouse.utils.TokenUtils;
import com.example.warehouse.utils.WarehouseConstants;
import com.google.code.kaptcha.Producer;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

@Controller
public class LoginController {

    @Resource
    //redis的模板类
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    //图形验证码配置类实现了该接口
    private Producer producer;

    @Autowired
    private LoginService loginService;

    @Autowired
    private TokenUtils tokenUtils;

//生成验证码方法
    @RequestMapping("/captcha/captchaImage")
    public void captcha(HttpServletResponse response) {
        ServletOutputStream out = null;
        String text = producer.createText();
        BufferedImage image = producer.createImage(text);
        response.setContentType("image/jpeg");
        stringRedisTemplate.opsForValue().set(text, "", 900, TimeUnit.SECONDS);
        try {
            out = response.getOutputStream();
            ImageIO.write(image, "jpg", out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

//登录方法
    @RequestMapping("/login")
    @ResponseBody
    public Result login(@RequestBody LoginUser loginUser){
        //验证验证码是否正确
        String verificationCode = loginUser.getVerificationCode();
        if(!stringRedisTemplate.hasKey(verificationCode)){
            return Result.err(Result.CODE_ERR_BUSINESS,"验证码不正确");
        }
        //获取后端查询的user
        User loginer = loginService.login(loginUser.getUserCode());
        //将前端用户密码进行md5加密
        String userPwd = DigestUtil.hmacSign(loginUser.getUserPwd());
        if(loginer==null){
            return Result.err(Result.CODE_ERR_BUSINESS,"账户不存在");
        } else if(loginer.getUserState().equals("1")&&
                userPwd.equals(loginer.getUserPwd())){
            //创建CurrentUser对象
            CurrentUser currentUser=
                    new CurrentUser(loginer.getUserId(),loginer.getUserCode(),loginer.getUserName());
            //创建token，在loginSign方法中，已经将token颁发给了redis
            String token=tokenUtils.loginSign(currentUser,userPwd);
            //向前端颁发token
            return Result.ok("登录成功",token);
        }else {
            return Result.err(Result.CODE_ERR_BUSINESS,"密码错误或用户未审核");
        }
    }

//退出登录方法
    @RequestMapping("/logout")
    @ResponseBody
    public Result logout(){
        stringRedisTemplate.delete("token");
        return Result.ok("退出系统！");
    }
}
