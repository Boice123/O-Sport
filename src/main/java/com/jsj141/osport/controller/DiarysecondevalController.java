package com.jsj141.osport.controller;

import com.jsj141.osport.domain.Diaryfirsteval;
import com.jsj141.osport.domain.Diarysecondeval;
import com.jsj141.osport.domain.User;
import com.jsj141.osport.service.DiaryfirstevalService;
import com.jsj141.osport.service.DiarysecondevalService;
import com.jsj141.osport.service.UserService;
import com.jsj141.osport.util.Result;
import com.jsj141.osport.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/diarysecondeval")
public class DiarysecondevalController {
    private final Logger logger = LoggerFactory.getLogger(DiarysecondevalController.class);

    @Autowired
    private DiaryfirstevalService diaryfirstevalService;

    @Autowired
    private DiarysecondevalService diarysecondevalService;

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    Result save(@RequestParam(value="fromusername") String fromusername,
                @RequestParam(value="tousername") String tousername,
                @RequestParam(value="firstevalid") String firstevalid,
                @RequestParam(value="content") String content,
//                Diaryfirsteval diaryfirsteval,
                Diarysecondeval diarysecondeval) {
        Result result = ResultUtil.initResult();
        diarysecondeval.setEvalid(UUID.randomUUID().toString());
        diarysecondeval.setContent(content);
        diarysecondeval.setFromusername(fromusername);
        diarysecondeval.setTousername(tousername);
        diarysecondeval.setFirstevalid(firstevalid);
        result = diarysecondevalService.save(diarysecondeval);
        return result;
    }


    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    Result delete(@RequestParam(value="evalid") String evalid,
                  Diarysecondeval diarysecondeval) {
        Result result = ResultUtil.initResult();
        diarysecondeval.setEvalid(evalid);
        result = diarysecondevalService.delete(diarysecondeval);
        return result;
    }

    /**
     * 获取评价列表排列
     * @param order
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    Result getList(@RequestParam(value="clubdiaryid") String clubdiaryid,
                   @RequestParam(value="order") String order,
                   HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        List<Diaryfirsteval> evalList = diaryfirstevalService.listdesc(clubdiaryid, order);
        for(int i=0; i < evalList.size(); i++) {
            //设置User信息
            String uid = evalList.get(i).getUserid();
            User user = new User();
            user.setUserid(uid);
            User uu = userService.get(user);
            evalList.get(i).setUser(uu);
        }
        ResultUtil.setSuccess(result, "获得部落一级评价列表排序信息成功", evalList);
        return result;
    }
}
