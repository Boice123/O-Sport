package com.jsj141.osport.controller;

import com.jsj141.osport.config.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class IndexController {
    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "captcha", method = RequestMethod.GET)
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        ServletOutputStream out = null;
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        response.setContentType("image/jpeg");
        String capText = Constant.FACADE.getCaptchaProducer().createText();
        logger.debug("capText->" + capText);
        request.getSession().setAttribute("code", capText);
        logger.debug("存入session->");
        BufferedImage bi = Constant.FACADE.getCaptchaProducer().createImage(capText);
        try {
            out = response.getOutputStream();
            ImageIO.write(bi, "jpg", out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
