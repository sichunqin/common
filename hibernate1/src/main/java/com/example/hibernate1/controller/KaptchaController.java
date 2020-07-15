package com.example.hibernate1.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;


@Controller
public class KaptchaController {
    @Resource
    private Producer captchaProducer = null;

    @RequestMapping("/logon/get-kaptcha")
    public void getKaptchaImage(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        byte[] captchaChallengeAsJpeg;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            String createText = captchaProducer.createText();
            httpServletRequest.getSession().setAttribute(KAPTCHA_SESSION_KEY, createText);
            BufferedImage challenge = captchaProducer.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        try(ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream()) {
            responseOutputStream.write(captchaChallengeAsJpeg);
        }
    }

    @PostMapping(value = "/logon/verify-kaptcha")
    @ResponseBody
    public String verifyKapcha(HttpServletRequest request, @RequestParam(value = "kaptcha") String kaptchaReceived) {
        if (kaptchaReceived == null || !kaptchaReceived.equals(request.getSession().getAttribute(KAPTCHA_SESSION_KEY))) {
            return "kaptcha_error";
        }
        return "success";
    }
    Boolean show = true;
    @RequestMapping("/login")
    public String success(HttpServletRequest request,
                          String userName,
                          String password,
            Map<String,Object> map)
    {

        map.put("title", "MultiTetancy Login");
        String msg;
        if(userName == null){

            return "login";

        }
        if(userName.equals("1")){

            return "redirect:/main.html";
        }
        else{
            map.put("mes", "error");
            return "login";

        }

    }

    @RequestMapping("/")
    public String index(HttpServletRequest request,
                          Map<String,Object> map)
    {
        return "login";
    }
    @RequestMapping("/main.html")
    public String main(HttpServletRequest request,
                        Map<String,Object> map)
    {
        return "main";
    }

}