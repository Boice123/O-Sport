package com.jsj141.osport.controller;

import com.jsj141.osport.domain.Shop;
import com.jsj141.osport.domain.Train;
import com.jsj141.osport.service.TrainService;
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
import java.util.UUID;
import java.util.List;

@Controller
@RequestMapping("/train")
public class TrainController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private TrainService trainService;

    /**
     * 保存train信息
     * @param trainname
     * @param traindescription
     * @param trainnotice
     * @param trainprice
     * @param maxpeople
     * @param trainimg
     * @param train
     * @param bindingResult
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    Result save(@RequestParam(value="trainname") String trainname,
                @RequestParam(value="traindescription") String traindescription,
                @RequestParam(value="trainnotice") String trainnotice,
                @RequestParam(value="trainprice") Double trainprice,
                @RequestParam(value="maxpeople") Integer maxpeople,
                @RequestParam(value="trainimg") String trainimg,
                Train train,
                BindingResult bindingResult,
                HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        Shop loginShop = (Shop) WebUtils.getSessionAttribute(request, "loginShop");
        train.setTrainid(UUID.randomUUID().toString());
        train.setShopid(loginShop.getShopid());
        train.setMaxpeople(maxpeople);
        train.setTraindescription(traindescription);
        train.setTrainimg(trainimg);
        train.setTrainnotice(trainnotice);
        train.setTrainname(trainname);
        train.setTrainprice(trainprice);
        result = trainService.save(train);
        return result;
    }

    /**
     * 修改train信息
     * @param trainname
     * @param traindescription
     * @param trainnotice
     * @param trainprice
     * @param maxpeople
     * @param trainimg
     * @param train
     * @param bindingResult
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    Result update(@RequestParam(value="trainname") String trainname,
                @RequestParam(value="traindescription") String traindescription,
                @RequestParam(value="trainnotice") String trainnotice,
                @RequestParam(value="trainprice") Double trainprice,
                @RequestParam(value="maxpeople") Integer maxpeople,
                @RequestParam(value="trainimg") String trainimg,
                  Train train,
                BindingResult bindingResult,
                HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        train.setMaxpeople(maxpeople);
        train.setTraindescription(traindescription);
        train.setTrainimg(trainimg);
        train.setTrainnotice(trainnotice);
        train.setTrainname(trainname);
        train.setTrainprice(trainprice);
        result = trainService.update(train);
        return result;
    }


    /**
     * 获取train信息
     * @param trainid
     * @param train
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    Result gettrainInfo(String trainid, Train train, HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        train.setTrainid(trainid);
        result = trainService.getTrainInfo(train);
        return result;
    }

    /**
     * 根据shopid，获取当前店铺的Train数量信息
     * @param shopid
     * @param train
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getCount", method = RequestMethod.POST)
    Result getCount(String shopid, Train train, HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        result = trainService.getTrainCount(shopid);
        return result;
    }

    /**
     * 删除train信息
     * @param trainid
     * @param train
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    Result deletetrainInfo(String trainid, Train train, HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        train.setTrainid(trainid);
        result = trainService.deleteTrainInfo(train);
        return result;
    }

    /**
     * 获得Train列表,按日期排序
     * @param size: 获取多少条数据
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getTrainList", method = RequestMethod.POST)
    Result getTrainList(@RequestParam(value="size") int size,
                       @RequestParam(value="order") String order,
                       HttpServletRequest request) {
        Result result = ResultUtil.initResult();
        List<Train> trainList = trainService.listdesc(0, size, order);
        ResultUtil.setSuccess(result, "获得Train列表排序信息成功", trainList);
        return result;
    }
}
