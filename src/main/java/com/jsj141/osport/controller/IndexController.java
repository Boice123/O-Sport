package com.jsj141.osport.controller;

import com.jsj141.osport.config.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @ResponseBody
//    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
//    private Result upload(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {
//        Result result = ResultUtil.initResult();
//        String upBase = DateUtil.getCurrentYM() + "/";
//        String distPath = baseConfig.getBasePath() + upBase;
//        String url = baseConfig.getBaseUrl() + upBase;
//        logger.debug("distPath->" + distPath);
//        try {
//            Enumeration params = multipartRequest.getParameterNames();
//            while (params.hasMoreElements()) {
//                String name = (String) params.nextElement();
//                String value = multipartRequest.getParameter(name);
//                multipartRequest.setAttribute(name, value);
//            }
//            Iterator<String> it = multipartRequest.getFileNames();
//            while (it.hasNext()) {
//                String name = it.next();
//                List<MultipartFile> files = multipartRequest.getFiles(name);
//                if (files == null || files.size() <= 0) {
//                    continue;
//                }
//                for (MultipartFile file : files) {
//                    if (StringUtil.isEmpty(file.getOriginalFilename()) || file.getSize() == 0)
//                        break;
//                    File normalFile = new File(file.getOriginalFilename());
//                    String ext = FileUtil.getFileType(normalFile);
//                    String filename = System.currentTimeMillis() + "." + ext;
//                    File path = new File(distPath);
//                    if (!path.exists()) {
//                        path.mkdirs();
//                    }
//                    String dist = distPath + "/" + filename;
//                    FileUtil.writIn(file.getInputStream(), dist);
//                    ResultUtil.setSuccess(result, "上传成功", url + "/" + filename);
//                    return result;
//                }
//            }
//        } catch (Exception e) {
//            logger.error("文档上传处理异常： ", e);
//        } finally {
//            return result;
//        }
//    }
}
