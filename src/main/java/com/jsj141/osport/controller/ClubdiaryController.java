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
import java.util.*;

@Controller
@RequestMapping("/clubdiary")
public class ClubdiaryController {
    private final Logger logger = LoggerFactory.getLogger(ClubdiaryController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ClubService clubService;

    @Autowired
    private ClubdiaryService clubdiaryService;

    @Autowired
    private DiaryfirstevalService diaryfirstevalService;

    @Autowired
    private DiarysecondevalService diarysecondevalService;


    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    Result save(@RequestParam(value="clubid") String clubid ,
                @RequestParam(value="clubdiarytitle") String clubdiarytitle ,
                @RequestParam(value="clubdiarycontent") String clubdiarycontent,
                @RequestParam(value="clubdiaryimg") String clubdiaryimg,
                Clubdiary clubdiary,
                BindingResult bindingResult,
                HttpServletRequest request) {
        Result lastResult = ResultUtil.initResult();

        User loginUser = (User) WebUtils.getSessionAttribute(request, "loginUser");
        clubdiary.setClubdiaryid(UUID.randomUUID().toString());
        clubdiary.setClubdiarytitle(clubdiarytitle);
        clubdiary.setClubdiarycontent(clubdiarycontent);
        clubdiary.setClubdiaryimg(clubdiaryimg);
        clubdiary.setUsername(loginUser.getUsername());
        clubdiary.setClubid(clubid);
        clubdiary.setUserid(loginUser.getUserid());
        clubdiary.setUsername(loginUser.getUsername());
        clubdiaryService.save(clubdiary);
        lastResult.setCode(0);
        lastResult.setMsg("添加成功");
        return lastResult;
    }

    /**
     * 根据Clubid获取部落动态
     * @param clubid
     * @param clubdiary
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllByClubid", method = RequestMethod.POST)
    Result getAllByClubid(@RequestParam(value="clubid") String clubid,
                        Clubdiary clubdiary,
                        HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        clubdiary.setClubid(clubid);
        List<Clubdiary> clubDiaryList= clubdiaryService.getByClubid(clubdiary);
        for(int i=0; i < clubDiaryList.size(); i++) {
            //设置Club信息
            String cid = clubDiaryList.get(i).getClubid();
            Club c = new Club();
            c.setClubid(cid);
            Club cc = clubService.getByClubid(c);
            clubDiaryList.get(i).setClub(cc);
            //设置用户信息
            String uid = clubDiaryList.get(i).getUserid();
            User u = new User();
            u.setUserid(uid);
            User user = userService.get(u);
            clubDiaryList.get(i).setUser(user);
            //设置评论信息
            String cdid = clubDiaryList.get(i).getClubdiaryid();
            List<Diaryfirsteval> evalList = diaryfirstevalService.listdesc(cdid, "evaltime");
            for(int j=0; j < evalList.size(); j++) {
                //设置User信息
                String id = evalList.get(j).getUserid();
                User ue = new User();
                ue.setUserid(id);
                User uu = userService.get(ue);
                evalList.get(j).setUser(uu);
                //设置二级评论信息
                String firstevalid = evalList.get(j).getEvalid();
                List<Diarysecondeval> list = diarysecondevalService.getByFirstevalid(firstevalid);
                evalList.get(j).setSecondevalList(list);
            }
            clubDiaryList.get(i).setDiaryfirsteval(evalList);
        }
        result.setCode(0);
        result.setData(clubDiaryList);
        result.setMsg("获取部落全部动态成功");
        return result;
    }

    /**
     * 根据Clubid获取部落动态,分页
     * @param clubid
     * @param start
     * @param size
     * @param order
     * @param clubdiary
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllByClubidPaginaton", method = RequestMethod.POST)
    Result getAllByClubidPaginaton(
            @RequestParam(value="clubid") String clubid,
            @RequestParam(value="start") int start,
            @RequestParam(value="size") int size,
            @RequestParam(value="order") String order,
            Clubdiary clubdiary,
            HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        List<Clubdiary> clubdiaryList= clubdiaryService.listdesc(start, size, order, clubid);
        result.setCode(0);
        result.setData(clubdiaryList);
        result.setMsg("获取部落全部动态成功");
        return result;
    }

    /**
     * 根据clubid获取部落全部动态
     * @param start
     * @param size
     * @param order
     * @param clubid
     * @param clubdiary
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllClubDiary", method = RequestMethod.POST)
    Result getAllClubDiary(
            @RequestParam(value = "start") int start,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "order") String order,
            @RequestParam(value = "clubid") String clubid,
            Clubdiary clubdiary,
            HttpServletRequest request){
        Result result = ResultUtil.initResult();
        List<Clubdiary> clubDiaryList= clubdiaryService.listdesc(start, size, order, clubid);
        result.setCode(0);
        result.setData(clubDiaryList);
        result.setMsg("获取部落全部动态成功");
        return result;
    }

    /**
     * 获取参与的部落动态,排序，分页
     * @param userid
     * @param club
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getJoinClubDiary", method = RequestMethod.POST)
    Result getJoinClubDiary(@RequestParam(value="userid") String userid,
                            @RequestParam(value="start") int start,
                            @RequestParam(value="size") int size,
                            @RequestParam(value="order") String order,
                           Club club,
                           HttpServletRequest request){
        Result result = ResultUtil.initResult();
        List<Clubdiary> clubDiaryList= clubdiaryService.getByUserid(start, size, order, userid);
        for(int i=0; i < clubDiaryList.size(); i++) {
            //设置Club信息
            String cid = clubDiaryList.get(i).getClubid();
            Club c = new Club();
            c.setClubid(cid);
            Club cc = clubService.getByClubid(c);
            clubDiaryList.get(i).setClub(cc);
            //设置用户信息
            String uid = clubDiaryList.get(i).getUserid();
            User u = new User();
            u.setUserid(uid);
            User user = userService.get(u);
            clubDiaryList.get(i).setUser(user);
            //设置评论信息
            String cdid = clubDiaryList.get(i).getClubdiaryid();
            List<Diaryfirsteval> evalList = diaryfirstevalService.listdesc(cdid, "evaltime");
            for(int j=0; j < evalList.size(); j++) {
                //设置User信息
                String id = evalList.get(j).getUserid();
                User ue = new User();
                ue.setUserid(id);
                User uu = userService.get(ue);
                evalList.get(j).setUser(uu);
                //设置二级评论信息
                String firstevalid = evalList.get(j).getEvalid();
//            Diarysecondeval dsd = new Diarysecondeval();
//            dsd.setFirstevalid(eid);
                List<Diarysecondeval> list = diarysecondevalService.getByFirstevalid(firstevalid);
                evalList.get(j).setSecondevalList(list);
            }
            clubDiaryList.get(i).setDiaryfirsteval(evalList);
        }
        result.setCode(0);
        result.setData(clubDiaryList);
        result.setMsg("获取参与的部落全部动态成功");
        return result;
    }

    /**
     * 获取参与的部落所有动态，主要用于获取动态总数
     * @param userid
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getJoinClubDiaryCount", method = RequestMethod.POST)
    Result getJoinClubDiaryCount(@RequestParam(value="userid") String userid,
                            HttpServletRequest request){
        Result result = ResultUtil.initResult();
        List<Clubdiary> clubDiaryList= clubdiaryService.getCountByUserid(userid);
        result.setCode(0);
        result.setData(clubDiaryList);
        result.setMsg("获取参与的所有动态成功");
        return result;
    }


    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    Result delete(@RequestParam(value="clubdiaryid") String clubdiaryid,
                          Clubdiary clubdiary,
                          HttpServletRequest request) {
        Result result = ResultUtil.initResult();

        clubdiary.setClubdiaryid(clubdiaryid);
        clubdiaryService.delete(clubdiary);
        result.setCode(0);
        result.setMsg("删除成功");
        return result;
    }

    /**
     * 批量删除CLubdiary
     * @param batchdelete
     * @param clubdiary
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/batchdelete", method = RequestMethod.POST)
    Result batchdeleteTripInfo(@RequestParam(value="batchdelete") String[] batchdelete,
                               Clubdiary clubdiary,
                               HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        for(int i = 0;i< batchdelete.length; i++){
            String clubactivityid = batchdelete[i];
            clubdiary.setClubdiaryid(clubactivityid);
            clubdiaryService.delete(clubdiary);
        }
        result.setCode(0);
        result.setMsg("删除成功");
        return result;
    }
}
