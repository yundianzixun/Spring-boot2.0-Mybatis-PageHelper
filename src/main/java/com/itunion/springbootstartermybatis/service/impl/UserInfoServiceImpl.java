package com.itunion.springbootstartermybatis.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itunion.springbootstartermybatis.mapper.UserInfoMapper;
import com.itunion.springbootstartermybatis.model.UserInfo;
import com.itunion.springbootstartermybatis.service.UserInfoService;
import com.itunion.springbootstartermybatis.web.dto.UserInfoDto;
import com.itunion.springbootstartermybatis.web.dto.UserInfoReqListDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 用户信息-服务提供方
 * Created by lin on 2018年06月07日21:48:13
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
	private final static Logger LOGGER = LoggerFactory.getLogger(UserInfoServiceImpl.class);
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public int addUserInfo(UserInfoDto record) {
        LOGGER.info("进入用户信息-服务提供方-UserInfoServiceImpl.addUserInfo[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "]");
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(record, userInfo);
        userInfo.setUpdateDate(new Date());
        return userInfoMapper.insertSelective(userInfo);
    }

    @Override
    public List<UserInfoDto> getUserInfoList() {
        LOGGER.info("进入用户信息-服务提供方-UserInfoServiceImpl.getUserInfoList[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "]");
        return userInfoMapper.getUserInfoList(null);
    }

    @Override
    public int delUserInfoById(Integer id) {
        LOGGER.info("进入用户信息-服务提供方-UserInfoServiceImpl.delUserInfoById[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "]");
        return userInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int modifyUserInfoById(UserInfoDto record) {
        LOGGER.info("进入用户信息-服务提供方-UserInfoServiceImpl.modifyUserInfoById[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "]");
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(record, userInfo);
        userInfo.setUpdateDate(new Date());
        return userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }

    @Override
    public PageInfo getUserInfoListPage(UserInfoReqListDto userInfoReqListDto) {
        LOGGER.info("进入用户信息-服务提供方-UserInfoServiceImpl.getUserInfoListPage[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "]");
        PageHelper.startPage(userInfoReqListDto.getPage(), userInfoReqListDto.getSize());
        List<UserInfoDto> userInfoDtos = userInfoMapper.getUserInfoList(userInfoReqListDto);
        PageInfo<UserInfoDto> userInfoDtoPageInfo = new PageInfo<>(userInfoDtos);
        return userInfoDtoPageInfo;
    }
}
