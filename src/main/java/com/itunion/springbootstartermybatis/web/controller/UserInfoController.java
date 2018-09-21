package com.itunion.springbootstartermybatis.web.controller;

import com.github.pagehelper.PageInfo;
import com.itunion.springbootstartermybatis.common.UniformResultTemplate;
import com.itunion.springbootstartermybatis.service.UserInfoService;
import com.itunion.springbootstartermybatis.web.dto.UserInfoDto;
import com.itunion.springbootstartermybatis.web.dto.UserInfoReqListDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 用户信息-移动端消费方
 * Created by lin on 2018年06月07日22:02:07
 */
@Controller
@RequestMapping("userInfo")
@Api(description = "测试移动")
public class UserInfoController {
    private Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    UserInfoService userInfoService;

    @ApiOperation(value = "getUserInfoList", notes = "查询所有用户信息")
    @RequestMapping(value = "getUserInfoList", method = RequestMethod.GET)
    @ResponseBody
    public UniformResultTemplate<List<UserInfoDto>> getUserInfoList() {
        LOGGER.info("进入用户信息-移动端消费方-UserInfoController.getUserInfoList[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "]");
        UniformResultTemplate<List<UserInfoDto>> uniformResultTemplate = new UniformResultTemplate<>();
        uniformResultTemplate.setCode(100);
        uniformResultTemplate.setMessage("查询所有用户信息成功！");
        uniformResultTemplate.setResult(userInfoService.getUserInfoList());
        return uniformResultTemplate;
    }

    @ApiOperation(value = "getUserInfoListPage", notes = "查询所有用户信息-分页")
    @ApiImplicitParam(name = "userInfoReqListDto", value = "{\"page\":\"1\",\"size\":\"2\"}")
    @RequestMapping(value = "getUserInfoListPage", method = RequestMethod.POST)
    @ResponseBody
    public UniformResultTemplate<PageInfo> getUserInfoList(@RequestBody UserInfoReqListDto userInfoReqListDto) {
        LOGGER.info("进入用户信息-移动端消费方-UserInfoController.getUserInfoListPage[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "]");
        UniformResultTemplate<PageInfo> uniformResultTemplate = new UniformResultTemplate<>();
        uniformResultTemplate.setCode(100);
        uniformResultTemplate.setMessage("查询所有用户信息成功分页！");
        uniformResultTemplate.setResult(userInfoService.getUserInfoListPage(userInfoReqListDto));
        return uniformResultTemplate;
    }

    @ApiOperation(value = "addUserInfo", notes = "新增用户信息")
    @ApiImplicitParam(name = "userInfoDto", value = "{\"userName\":\"测试用户名\",\"password\":\"000000\",\"sex\":1,\"content\":\"这里是IT实战联哦~~~\"}")
    @RequestMapping(value="addUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public UniformResultTemplate<String> addUserInfo(@RequestBody UserInfoDto userInfoDto) {

    LOGGER.info("进入用户信息-移动端消费方-UserInfoController.addUserInfo[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "]");
        UniformResultTemplate<String> uniformResultTemplate = new UniformResultTemplate<>();
        Integer num = userInfoService.addUserInfo(userInfoDto);
        if(num > 0){
            uniformResultTemplate.setCode(100);
            uniformResultTemplate.setMessage("新增用户信息成功！");
            uniformResultTemplate.setResult(num+"");
        }else{
            uniformResultTemplate.setCode(400);
            uniformResultTemplate.setMessage("新增用户信息失败！");
            uniformResultTemplate.setResult(num+"");
        }
        return uniformResultTemplate;
    }


    @ApiOperation(value="delUserInfoById", notes="根据用户ID删除用户信息")
    @ApiImplicitParam(name = "id", value = "4" , paramType="path" , dataType="Integer")
    @RequestMapping(value="delUserInfoById/{id}", method = RequestMethod.POST)
    @ResponseBody
    public UniformResultTemplate<String> deleteKdgVipItem(@PathVariable Integer id) {
        LOGGER.info("进入用户信息-移动端消费方-UserInfoController.delUserInfoById[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "]");
        UniformResultTemplate<String> uniformResultTemplate = new UniformResultTemplate<>();
        Integer num = userInfoService.delUserInfoById(id);
        if(num > 0){
            uniformResultTemplate.setCode(100);
            uniformResultTemplate.setMessage("根据用户ID删除用户信息成功！");
            uniformResultTemplate.setResult(num+"");
        }else{
            uniformResultTemplate.setCode(400);
            uniformResultTemplate.setMessage("根据用户ID删除用户信息失败！");
            uniformResultTemplate.setResult(num+"");
        }
        return uniformResultTemplate;
    }

    @ApiOperation(value = "modifyUserInfo", notes = "修改用户信息")
    @ApiImplicitParam(name = "userInfoDto", value = "{\"id\":10,\"userName\":\"测试修改用户名\",\"password\":55555,\"sex\":1,\"content\":\"这里是最新的IT实战联哦~~~\"}")
    @RequestMapping(value="modifyUserInfo", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public UniformResultTemplate<String> modifyUserInfo(@RequestBody UserInfoDto userInfoDto) {
        LOGGER.info("进入用户信息-移动端消费方-UserInfoController.modifyUserInfo[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "]");
        UniformResultTemplate<String> uniformResultTemplate = new UniformResultTemplate<>();
        Integer num = userInfoService.modifyUserInfoById(userInfoDto);
        if(num > 0){
            uniformResultTemplate.setCode(100);
            uniformResultTemplate.setMessage("修改用户信息成功！");
            uniformResultTemplate.setResult(num+"");
        }else{
            uniformResultTemplate.setCode(400);
            uniformResultTemplate.setMessage("修改用户信息失败！");
            uniformResultTemplate.setResult(num+"");
        }
        return uniformResultTemplate;
    }


}
