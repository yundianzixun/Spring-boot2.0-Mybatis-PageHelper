package com.itunion.springbootstartermybatis.service;

import com.github.pagehelper.PageInfo;
import com.itunion.springbootstartermybatis.web.dto.UserInfoDto;
import com.itunion.springbootstartermybatis.web.dto.UserInfoReqListDto;

import java.util.List;

/**
 * 用户数据服务
 * Created by lin on 2018年06月07日21:12:04
 */
public interface UserInfoService {
    /**
     * 新增用户信息
     */
    int addUserInfo(UserInfoDto record);

    /**
     * 查询所有用户信息
     */
    List<UserInfoDto> getUserInfoList();

    /**
     * 根据用户ID删除用户信息
     */
    int delUserInfoById(Integer id);

    /**
     * 根据用户ID修改用户信息
     */
    int modifyUserInfoById(UserInfoDto record);


    /**
     * 分页查询
     */
    PageInfo getUserInfoListPage(UserInfoReqListDto userInfoReqListDto);

}
