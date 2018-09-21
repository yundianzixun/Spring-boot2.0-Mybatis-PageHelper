# 简介

该项目主要利用Spring boot2.0 +Mybatis + PageHelper实现增删改查和分页查询功能，快速搭建一套和数据库交互的项目。

*   源码地址
    *   GitHub：[https://github.com/yundianzixun/Spring-boot2.0-Mybatis-PageHelper](https://github.com/yundianzixun/Spring-boot2.0-Mybatis-PageHelper)
*   联盟公众号：IT实战联盟
*   我们社区：[https://100boot.cn](https://100boot.cn)

**小工具一枚，欢迎使用和Star支持，如使用过程中碰到问题，可以提出Issue，我会尽力完善该Starter**

# 版本基础

*   Spring Boot：2.0.4
*  Mybatis：3.4.5
*  Druid：1.1.10
*  PageHelper：4.1.6
### 操作步骤
#### 第一步：下载SpringBoot2.0+Swagger2项目
*   GitHub地址：https://github.com/yundianzixun/spring-boot-starter-swagger2
*   参考文档：https://www.jianshu.com/p/61db1a6ca425
备注：微服务架构实战系列每整合一个中间件都会放到GitHub上，可根据教程一步一步学习微服务架构。

#### 第二步：添加maven依赖（完整依赖）
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.itunion</groupId>
	<artifactId>spring-boot-starter-mybatis</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>jar</packaging>

	<name>spring-boot-starter-mybatis</name>
	<description>Demo project for Spring Boot</description>

	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.0.4.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
		<java.version>1.8</java.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>1.3.2</version>
		</dependency>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis</artifactId>
			<version>3.4.5</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis-spring</artifactId>
			<version>1.3.1</version>
		</dependency>
		<dependency>
			<groupId>com.github.pagehelper</groupId>
			<artifactId>pagehelper</artifactId>
			<version>4.1.6</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/com.alibaba/druid -->
		<dependency>
			<groupId>com.alibaba</groupId>
			<artifactId>druid</artifactId>
			<version>1.1.10</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger2</artifactId>
			<version>2.7.0</version>
		</dependency>
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger-ui</artifactId>
			<version>2.7.0</version>
		</dependency>
		<dependency>
			<groupId>org.apache.tomcat.embed</groupId>
			<artifactId>tomcat-embed-jasper</artifactId>
		</dependency>
		<!-- 打war包用 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
			<!--<scope>provided</scope>-->
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
	</dependencies>

	<build>
		<!-- 打war包用 -->
		<finalName>demo</finalName>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
			<!-- 打war包用,maven打包的时候告诉maven不需要web.xml,否刚会报找不到web.xml错误 -->
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-war-plugin</artifactId>
				<version>2.1.1</version>
				<configuration>
					<failOnMissingWebXml>false</failOnMissingWebXml>
				</configuration>
			</plugin>
			<!-- -->
		</plugins>
	</build>
</project>

```
#### 第三步：application.properties 配置（完整配置）
```
server.port=8081

server.servlet.context-path=/Demo

server.tomcat.accesslog.enabled=true

druid.driver=com.mysql.jdbc.Driver
druid.url=jdbc:mysql://127.0.0.1:3306/数据库名称?serverTimezone=Asia/Shanghai&allowMultiQueries=true
druid.username=数据库用户名
druid.password=数据库密码
druid.init-size=1
druid.min-idel=1
druid.max-active=5
druid.login.timeout.seconds=30
druid.query.timeout.seconds=30

swagger.enable=true
```
#### 第四步：增加mybatis-config.xml文件
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <settings>
        <setting name="cacheEnabled" value="true"/>
        <setting name="lazyLoadingEnabled" value="true"/>
        <setting name="aggressiveLazyLoading" value="true"/>
        <setting name="useGeneratedKeys" value="true"/>
        <setting name="defaultExecutorType" value="SIMPLE"/>
        <setting name="defaultStatementTimeout" value="600"/>
        <setting name="callSettersOnNulls" value="true"/>
    </settings>
    <plugins>
        <plugin interceptor="com.github.pagehelper.PageHelper">
            <property name="dialect" value="sqlserver"/>
        </plugin>
    </plugins>
    <mappers>
        <mapper resource="mappers/UserInfoMapper.xml"/>
    </mappers>
</configuration>

```
## 备注
  * PageHelper是分页插件
  * UserInfoMapper.xml 是我们本篇文章要实现丢用户的增删改查映射文件

#### 第五步：创建数据库连接池DataSource（DatasourceConfig.java）
```
package com.itunion.springbootstartermybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.itunion.springbootstartermybatis.mapper")
public class DatasourceConfig {
    private static Logger log = LoggerFactory.getLogger(DatasourceConfig.class);
    @Value("${druid.driver}")
    private String driverClassName;
    @Value("${druid.url}")
    private String url;
    @Value("${druid.username}")
    private String username;
    @Value("${druid.password}")
    private String password;
    @Value("${druid.init-size}")
    private int initSize;
    @Value("${druid.min-idel}")
    private int minIdel;
    @Value("${druid.max-active}")
    private int maxActive;
    @Value("${druid.login.timeout.seconds}")
    private int loginTimeoutSeconds;
    @Value("${druid.query.timeout.seconds}")
    private int queryTimeoutSeconds;

    @Bean
    public DataSource dataSource() {
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(driverClassName);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setInitialSize(initSize);
        ds.setMinIdle(minIdel);
        ds.setMaxActive(maxActive);
        ds.setLoginTimeout(loginTimeoutSeconds);
        ds.setQueryTimeout(queryTimeoutSeconds);
        return ds;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        final SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        sqlSessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        sqlSessionFactory.setFailFast(true);
        return sqlSessionFactory.getObject();
    }

    public DataSourceTransactionManager dataSourceTransactionManager() {
        log.debug("> transactionManager");
        return new DataSourceTransactionManager(dataSource());
    }


    @PostConstruct
    public void postConstruct() {
        log.info("jdbc settings={}", this);
    }
}

```

#### 第六步：创建UserInfoMapper.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.itunion.springbootstartermybatis.mapper.UserInfoMapper">
  <resultMap id="BaseResultMap" type="com.itunion.springbootstartermybatis.model.UserInfo">
    <id column="id" jdbcType="INTEGER" property="id" />
    <result column="user_name" jdbcType="VARCHAR" property="userName" />
    <result column="password" jdbcType="VARCHAR" property="password" />
    <result column="sex" jdbcType="VARCHAR" property="sex" />
    <result column="content" jdbcType="VARCHAR" property="content" />
    <result column="create_date" jdbcType="TIMESTAMP" property="createDate" />
    <result column="update_date" jdbcType="TIMESTAMP" property="updateDate" />
  </resultMap>
  <sql id="Base_Column_List">
    id, user_name, password, sex, content, create_date, update_date
  </sql>
  <select id="selectByPrimaryKey" parameterType="java.lang.Integer" resultMap="BaseResultMap">
    select 
    <include refid="Base_Column_List" />
    from user_info
    where id = #{id,jdbcType=INTEGER}
  </select>
  <delete id="deleteByPrimaryKey" parameterType="java.lang.Integer">
    delete from user_info
    where id = #{id,jdbcType=INTEGER}
  </delete>
  <insert id="insert" parameterType="com.itunion.springbootstartermybatis.model.UserInfo">
    insert into user_info (id, user_name, password, 
      sex, content, create_date, 
      update_date)
    values (#{id,jdbcType=INTEGER}, #{userName,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, 
      #{sex,jdbcType=VARCHAR}, #{content,jdbcType=VARCHAR}, #{createDate,jdbcType=TIMESTAMP}, 
      #{updateDate,jdbcType=TIMESTAMP})
  </insert>
  <insert id="insertSelective" parameterType="com.itunion.springbootstartermybatis.model.UserInfo">
    insert into user_info
    <trim prefix="(" suffix=")" suffixOverrides=",">
      <if test="id != null">
        id,
      </if>
      <if test="userName != null">
        user_name,
      </if>
      <if test="password != null">
        password,
      </if>
      <if test="sex != null">
        sex,
      </if>
      <if test="content != null">
        content,
      </if>
      <if test="createDate != null">
        create_date,
      </if>
      <if test="updateDate != null">
        update_date,
      </if>
    </trim>
    <trim prefix="values (" suffix=")" suffixOverrides=",">
      <if test="id != null">
        #{id,jdbcType=INTEGER},
      </if>
      <if test="userName != null">
        #{userName,jdbcType=VARCHAR},
      </if>
      <if test="password != null">
        #{password,jdbcType=VARCHAR},
      </if>
      <if test="sex != null">
        #{sex,jdbcType=VARCHAR},
      </if>
      <if test="content != null">
        #{content,jdbcType=VARCHAR},
      </if>
      <if test="createDate != null">
        #{createDate,jdbcType=TIMESTAMP},
      </if>
      <if test="updateDate != null">
        #{updateDate,jdbcType=TIMESTAMP},
      </if>
    </trim>
  </insert>
  <update id="updateByPrimaryKeySelective" parameterType="com.itunion.springbootstartermybatis.model.UserInfo">
    update user_info
    <set>
      <if test="userName != null">
        user_name = #{userName,jdbcType=VARCHAR},
      </if>
      <if test="password != null">
        password = #{password,jdbcType=VARCHAR},
      </if>
      <if test="sex != null">
        sex = #{sex,jdbcType=VARCHAR},
      </if>
      <if test="content != null">
        content = #{content,jdbcType=VARCHAR},
      </if>
      <if test="createDate != null">
        create_date = #{createDate,jdbcType=TIMESTAMP},
      </if>
      <if test="updateDate != null">
        update_date = #{updateDate,jdbcType=TIMESTAMP},
      </if>
    </set>
    where id = #{id,jdbcType=INTEGER}
  </update>
  <update id="updateByPrimaryKey" parameterType="com.itunion.springbootstartermybatis.model.UserInfo">
    update user_info
    set user_name = #{userName,jdbcType=VARCHAR},
      password = #{password,jdbcType=VARCHAR},
      sex = #{sex,jdbcType=VARCHAR},
      content = #{content,jdbcType=VARCHAR},
      create_date = #{createDate,jdbcType=TIMESTAMP},
      update_date = #{updateDate,jdbcType=TIMESTAMP}
    where id = #{id,jdbcType=INTEGER}
  </update>


    <select id="getUserInfoList" parameterType="com.itunion.springbootstartermybatis.web.dto.UserInfoReqListDto" resultType="com.itunion.springbootstartermybatis.web.dto.UserInfoDto">
        select
        id, user_name as userName, password, sex, content, create_date as createDate, update_date as updateDate
        from user_info
    </select>
</mapper>
```
* 实现了对用户表的增删改查和批量查询sql语句

#### 第七步：编写UserInfo实体类
```
package com.itunion.springbootstartermybatis.model;

import java.util.Date;

public class UserInfo {
    private Integer id;

    private String userName;

    private String password;

    private String sex;

    private String content;

    private Date createDate;

    private Date updateDate;

    此处省略get/set方法
}

```
#### 第八步：编写UserInfoMapper
```
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

```

#### 第九步：编写UserInfoService接口和接口实现
#####UserInfoService.java
```
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
```
#####UserInfoServiceImpl.java
```
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

```

#### 第十步：编写UserInfoController
```
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

```

#### 第十一步：SpringBootApplication 启动
```
package com.itunion.springbootstartermybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringBootStarterMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStarterMybatisApplication.class, args);
	}
}
```
#### 第十一步：访问Swagger接口地址
```
http://127.0.0.1:8080/Demo/api
```
如下图所示：
![微服务架构实战篇（三）：Spring boot2.0 + Mybatis实现增删改查功能.jpg](https://upload-images.jianshu.io/upload_images/8122772-b7e7f7f2c3a4d72f.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

* 端口号已自己配置为准

## 贡献者

*   [IT实战联盟-Line](https://www.jianshu.com/u/283f93ada597)
*   [IT实战联盟-咖啡](https://www.jianshu.com/u/29d607600e98)


#### 更多精彩内容可以关注“IT实战联盟”公众号哦~~~

![image](http://upload-images.jianshu.io/upload_images/8122772-b78dee4c5818c874?imageMogr2/auto-orient/strip%7CimageView2/2/w/500)
