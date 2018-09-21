package com.itunion.springbootstartermybatis.mapper;



import com.itunion.springbootstartermybatis.model.UserInfo;
import com.itunion.springbootstartermybatis.web.dto.UserInfoReqListDto;
import com.itunion.springbootstartermybatis.web.dto.UserInfoDto;


import java.util.List;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    /**
     * 获取用户信息集合
     * @return
     */
    List<UserInfoDto> getUserInfoList(UserInfoReqListDto userInfoReqListDto);
}