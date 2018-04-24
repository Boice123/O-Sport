package com.jsj141.osport.controller;

import com.jsj141.osport.domain.*;
import com.jsj141.osport.service.*;
import com.jsj141.osport.util.Result;
import com.jsj141.osport.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Controller
@RequestMapping("/clubuseractivity")
public class ClubuseractivityController {
    private final Logger logger = LoggerFactory.getLogger(ClubuseractivityController.class);

    @Autowired
    private ClubdiaryService clubdiaryService;

    @Autowired
    private ClubactivityService clubactivityService;

    @Autowired
    private ClubService clubService;

    @Autowired
    private ClubuseractivityService clubuseractivityService;



    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    Result save(@RequestParam(value="clubactivityid") String clubactivityid ,
                Clubuseractivity clubuseractivity,
                BindingResult bindingResult,
                HttpServletRequest request) {
        Result lastResult = ResultUtil.initResult();

        User loginUser = (User) WebUtils.getSessionAttribute(request, "loginUser");

        //检验当前用户是否已经参加活动
        clubuseractivity.setClubactivityid(clubactivityid);
        clubuseractivity.setUserid(loginUser.getUserid());
        Clubuseractivity record = clubuseractivityService.getByUseridNClubactivityId(clubuseractivity);
        if(record == null) {
            //参加活动
            clubuseractivity.setClubuseractivityid(UUID.randomUUID().toString());
            clubuseractivityService.save(clubuseractivity);
            clubactivityService.updatePeople(clubactivityid);
            lastResult.setCode(0);
            lastResult.setMsg("参加活动成功");
        }else {
            lastResult.setCode(1);
            lastResult.setMsg("您已经参加这个活动了");
        }
        return lastResult;



    }

    /**
     * 根据Clubid获取部落活动
     * @param clubid
     * @param clubactivity
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllByClubid", method = RequestMethod.POST)
    Result getAllByClubid(@RequestParam(value="clubid") String clubid,
                       Clubactivity clubactivity,
                       HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        clubactivity.setClubid(clubid);
        List<Clubactivity> clubactivtyList= clubactivityService.getByClubid(clubactivity);
        if(clubactivtyList.size() != 0 ) {
            result.setCode(0);
            result.setData(clubactivtyList);
            result.setMsg("获取部落全部活动成功");
        }else {
            result.setCode(1);
            result.setMsg("部落没有活动数据");
        }
        return result;
    }
}
