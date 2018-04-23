## 秒杀系统（ssm）
[跟着慕课网学习秒杀系统](https://www.imooc.com/u/2145618/courses?sort=publish)

### java高并发秒杀API之业务分析与Dao

### 效果展示
![](https://github.com/YLDarren/Seckill/blob/master/img/dao1.png)
![](https://github.com/YLDarren/Seckill/blob/master/img/dao2.png)
![](https://github.com/YLDarren/Seckill/blob/master/img/dao3.png)
![](https://github.com/YLDarren/Seckill/blob/master/img/dao4.png)

### 相关技术介绍

1、MySQL
  表设计
  sql技巧
  事务和行集锁
  
2、MyBatis
  DAO层设计与开发
  MyBatis合理使用
  MyBatis与Spring整合
 
3、Spring
  Spring IOC整合Service
  声明式事务运用

4、Spring MVC
  Restful接口设计和使用
  框架运作流程
  Controller开发技巧

5、前端
  交互设计
  Bootstrap
  jQuery

6、高并发
  高并发点和高并发分析
  优化思路并实现
  
### 基于Maven创建项目
```
mvn -v 检测maven的版本
mvn archetype:generate -DgroupId=org -DartifactId=seckill -DarchetypeArtifactId=maven-archetype-webapp
这是用命令行创建maven项目
你也可以直接在eclipse中创建maven项目，我就是在eclipse中创建的项目，是war包
创建好后会报错，是因为缺少文件，在webapp下补全WEB-INF这个目录，在再这个目录下创建一个web.xml文件
```
### 编写pom.xml文件文件
``` pom.xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>xin.mlangd.seckill</groupId>
	<artifactId>seckill</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>war</packaging>


	<!-- 添加servlet-api，jsp-api -->
	<dependencies>
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.11</version>
			<scope>test</scope>
		</dependency>
		<!-- 补全项目依赖 -->
		<!-- 1、日志；java日志：slf4j,log4j,logback,common-logging slf4j是规范/接口 日志实现：log4j,logback,common-logging 
			使slf4j+logback -->
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-api</artifactId>
			<version>1.7.24</version>
		</dependency>
		<dependency>
			<groupId>ch.qos.logback</groupId>
			<artifactId>logback-core</artifactId>
			<version>1.1.1</version>
		</dependency>
		<dependency>
			<groupId>ch.qos.logback</groupId>
			<artifactId>logback-classic</artifactId>
			<version>1.1.9</version>
		</dependency>
		<!-- 实现slf4j接口并整合 -->
		<dependency>
			<groupId>ch.qos.logback</groupId>
			<artifactId>logback-classic</artifactId>
			<version>1.1.1</version>
		</dependency>

		<!-- 2、数据库相关的依赖 -->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>5.1.6</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>c3p0</groupId>
			<artifactId>c3p0</artifactId>
			<version>0.9.1.2</version>
		</dependency>
		<!-- DAO:框架依赖 -->
		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis</artifactId>
			<version>3.3.0</version>
		</dependency>
		<!-- mybatis自身实现的spring整合 -->
		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis-spring</artifactId>
			<version>1.2.3</version>
		</dependency>

		<!-- 3、servlet web相关依赖 -->
		<dependency>
			<groupId>taglibs</groupId>
			<artifactId>standard</artifactId>
			<version>1.1.2</version>
		</dependency>
		<dependency>
			<groupId>jstl</groupId>
			<artifactId>jstl</artifactId>
			<version>1.2</version>
		</dependency>
		<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-databind</artifactId>
			<version>2.5.4</version>
		</dependency>
		<!-- <dependency> <groupId>javax.servlet</groupId> <artifactId>javax.servlet-api</artifactId> 
			<version>3.1.0</version> <scope>provided</scope> </dependency> -->
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>servlet-api</artifactId>
			<version>2.5</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jsp-api</artifactId>
			<version>2.0</version>
			<scope>provided</scope>
		</dependency>
		
		<!-- 4、spring依赖 -->
		<!-- 1)spring核心依赖 -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-core</artifactId>
			<version>4.1.7.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-beans</artifactId>
			<version>4.1.7.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>4.1.7.RELEASE</version>
		</dependency>
		<!-- 2)spring dao层依赖 -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-jdbc</artifactId>
			<version>4.1.7.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-tx</artifactId>
			<version>4.1.7.RELEASE</version>
		</dependency>
		<!-- 3)spring web相关依赖 -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-web</artifactId>
			<version>4.1.7.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>4.1.7.RELEASE</version>
		</dependency>
		<!-- 4)spring test相关依赖 -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-test</artifactId>
			<version>4.1.7.RELEASE</version>
		</dependency>
		
	</dependencies>
	<build>
		<plugins>
			<!-- 设置编译版本为1.8 -->
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<configuration>
					<source>1.8</source>
					<target>1.8</target>
					<encoding>UTF-8</encoding>
				</configuration>
			</plugin>

			<!-- maven内置 的tomcat7插件 -->
			<plugin>
          		<groupId>org.apache.tomcat.maven</groupId>
          		<artifactId>tomcat7-maven-plugin</artifactId>
          		<version>2.2</version>
        	</plugin>

		</plugins>
		
	</build>

</project>
```

### 秒杀业务分析
 1、秒杀系统业务流程
![](https://github.com/YLDarren/Seckill/blob/master/img/dao5.png)
 2、用户针对库存业务分析 
![](https://github.com/YLDarren/Seckill/blob/master/img/dao6.png)
 3、什么是购买行为 
![](https://github.com/YLDarren/Seckill/blob/master/img/dao7.png)
 4、为什么需要事务 
![](https://github.com/YLDarren/Seckill/blob/master/img/dao8.png)

### 实现哪些秒杀功能
1、秒杀接口暴露
2、执行秒杀
3、相关查询

### 数据库初始化脚本 schema.sql
``` schema.sql
-- 数据库初始化脚本

-- 创建数据库
create database seckill;

-- 使用数据库
use seckill;

-- 创建秒杀库存表
create table seckill(
	seckill_id bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
	name varchar(120) NOT NULL COMMENT '商品名称',
	number int NOT NULL COMMENT '库存数量',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '秒杀创建时间',
	start_time timestamp NOT NULL COMMENT '秒杀开启时间',
	end_time timestamp NOT NULL COMMENT '秒杀结束时间',
	PRIMARY KEY(seckill_id),
	key idx_start_time(start_time),
	key idx_end_time(end_time),
	key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

-- 初始化数据
insert into 
	seckill(name,number,start_time,end_time)
values
	('5000元秒杀iphoneX',100,'2018-04-15 00:00:00','2018-04-16 00:00:00'),
	('1000元秒杀红米note',1000,'2018-04-18 00:00:00','2018-04-23 00:00:00'),
	('4000元秒杀华为p10',800,'2018-04-16 00:00:00','2018-04-30 00:00:00'),
	('200元秒杀小米4',300,'2018-04-20 00:00:00','2018-05-3 00:00:00'),
	('1000元秒杀mac air',900,'2018-04-16 00:00:00','2018-05-16 00:00:00');
	
insert into 
	seckill(name,number,start_time,end_time)
values
	('0元秒杀戴尔鼠标',100,'2018-04-30 00:00:00','2018-05-16 00:00:00')
	
-- 秒杀成功明细表
-- 用户登录认证相关的信息
create table success_killed(
	seckill_id bigint NOT NULL COMMENT '商品库存id',	
	user_phone bigint NOT NULL COMMENT '用户手机号',
	state tinyint NOT NULL DEFAULT -1 COMMENT '状态表示：-1：无效   0：成功  1：已付款   2：已发货',
	create_time timestamp NOT NULL COMMENT '秒杀成功创建时间',
	PRIMARY KEY(seckill_id,user_phone),
	key idx_create_time(create_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';
```

### DAO实体和接口编码
``` seckillDao.java
package org.seckill.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.seckill.entiy.Seckill;

//秒杀dao层接口
public interface SeckillDao {

	/**
	 * 减库存
	 * @param seckillId
	 * @param killTime
	 * @return
	 */
	int reduceNumber(@Param("seckillId") long seckillId , @Param("killTime") Date killTime);
	
	/**
	 * 通过seckillId查询单个秒杀库存
	 * @param seckillId
	 * @return
	 */
	Seckill queryById(long seckillId);
	
	/**
	 * 根据偏移量查询所有的秒杀库存
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<Seckill> queryAll(@Param("offset") int offset , @Param("limit") int limit);
	
}

``` 
``` SuccessKilledDao.java
package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entiy.SuccessKilled;

//成功秒杀的dao接口
public interface SuccessKilledDao {
	
	/**
	 * 插入购买明细，可过滤重复
	 * @param seckillId
	 * @param userPhone
	 * @return
	 */
	int insertSuccessKilled(@Param("seckillId") long seckillId ,@Param("userPhone") long userPhone);
	
	/**
	 * 根据seckillId查询成功秒杀并携带秒杀产品对象
	 * @param seckillId
	 * @return
	 */
	SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId , @Param("userPhone") long userPhone );
	
	
}

```

### mybatis的配置文件
``` mybatis-config.xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>
	<!-- 配置全局属性 -->
	<settings>
		<!--使用jdbc的getGeneratekeys获取自增主键值-->
        <setting name="useGeneratedKeys" value="true"/>
        <!--使用列别名替换列名　　默认值为true
        select name as title(实体中的属性名是title) form table;
        开启后mybatis会自动帮我们把表中name的值赋到对应实体的title属性中
        -->
        <setting name="useColumnLabel" value="true"/>

        <!--开启驼峰命名转换Table:create_time到 Entity(createTime)-->
        <setting name="mapUnderscoreToCamelCase" value="true"/>
	</settings>
	
</configuration>
```

### mybatis的映射文件
``` SeckillDao.xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="org.seckill.dao.SeckillDao">
	
	<update id="reduceNumber" >
		update 
			seckill
		set 
			number = number - 1
		where seckill_id = #{seckillId}
		and start_time <![CDATA[ <= ]]> #{killTime}
		and end_time >= #{killTime}
		and number > 0;
	</update>
	
	<select id="queryById" resultType="org.seckill.entiy.Seckill" parameterType="long">
		select seckill_id,name,number,create_time,start_time,end_time
		from seckill
		where seckill_id = #{seckillId};
	</select>
	
	<select id="queryAll" resultType="org.seckill.entiy.Seckill">
		select seckill_id,name,number,create_time,start_time,end_time
		from seckill
		order by create_time desc
		limit #{offset} , #{limit};
	</select>
	
</mapper>
```
``` SuccessKilledDao.xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="org.seckill.dao.SuccessKilledDao">
	
	<insert id="insertSuccessKilled">
		<!-- 主键冲突，会报错 -->
		insert ignore into success_killed(seckill_id,user_phone,state)
		values(#{seckillId},#{userPhone},1);
	</insert>
	
	<select id="queryByIdWithSeckill" resultType="SuccessKilled">
		<!-- 根据seckillId查询成功秒杀并携带秒杀产品对象 -->
		select
			sk.seckill_id,
			sk.user_phone,
			sk.state,
			sk.create_time,
			s.seckill_id "seckill.seckill_id",
			s.name "seckill.name",
			s.number "seckill.number",
			s.create_time "seckill.create_time",
			s.start_time "seckill.start_time",
			s.end_time "seckill.end_time"
		from success_killed sk
		inner join seckill s on sk.seckill_id = s.seckill_id
		where sk.seckill_id = #{seckillId} and sk.user_phone = #{userPhone}; 
	</select>
	
</mapper>
```
### mybatis整合spring配置
``` spring-dao.xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:context="http://www.springframework.org/schema/context" xmlns:p="http://www.springframework.org/schema/p"
	xmlns:aop="http://www.springframework.org/schema/aop" xmlns:tx="http://www.springframework.org/schema/tx"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.0.xsd
	http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.0.xsd
	http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.0.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.0.xsd
	http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util-4.0.xsd">

	<!-- 配置整合mybatis -->
	
	<!-- 1、配置数据库相关参数 -->
	<context:property-placeholder location="classpath:jdbc.properties"/>
	
	<!-- 2、数据库连接池 -->
	<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
		<!-- 配置连接池属性 -->
		<property name="driverClass" value="${jdbc.driverClass}" />
		<property name="jdbcUrl" value="${jdbc.url}" />
		<property name="user" value="${jdbc.username}" />
		<property name="password" value="${jdbc.password}" />
		
		<!--c3p0私有属性-->
        <property name="maxPoolSize" value="30"/>
        <property name="minPoolSize" value="10"/>
        <!--关闭连接后不自动commit-->
        <property name="autoCommitOnClose" value="false"/>

        <!--获取连接超时时间-->
        <property name="checkoutTimeout" value="1000"/>
        <!--当获取连接失败重试次数-->
        <property name="acquireRetryAttempts" value="2"/>
        
	</bean>
	
	<!-- 3、配置SqlSessionFactory对象 -->
	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <!--往下才是mybatis和spring真正整合的配置-->
        <!--注入数据库连接池-->
        <property name="dataSource" ref="dataSource"/>
        <!--配置mybatis全局配置文件:mybatis-config.xml-->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <!--扫描entity包,使用别名,多个用;隔开-->
        <property name="typeAliasesPackage" value="org.seckill.entiy"/>
        <!--扫描sql配置文件:mapper需要的xml文件-->
        <property name="mapperLocations" value="classpath:mapper/*.xml"/>
    </bean>
	
	
	<!-- 4、配置扫描dao接口包,动态实现dao接口,注入到spring容器中 -->
	<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <!--注入SqlSessionFactory-->
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
        <!-- 给出需要扫描的Dao接口-->
        <property name="basePackage" value="org.seckill.dao"/>
    </bean>
		
</beans>
```

### dao层测试
``` SeckillDaoTest
package org.seckill.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entiy.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SeckillDaoTest {
	
	//注入dao实现类依赖
	@Resource
	private SeckillDao seckillDao;
	
	@Test
	public void testQueryAll() {
		long id = 1000;
		Seckill seckill = seckillDao.queryById(id);
		System.out.println(seckill.getName());
		System.out.println(seckill);
	}
	
	@Test
	public void queryAll() {
		List<Seckill> seckills = seckillDao.queryAll(0, 10);
		
		for(Seckill s : seckills) {
			System.out.println(s);
		}
	}
	
	
	
	@Test
	public void testReduceNumber() {
		Date killTime = new Date();
		int num = seckillDao.reduceNumber(1004L, killTime);
		System.out.println(num);
	}
}

```

``` SuccessKilledDaoTest
package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entiy.SuccessKilled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")

public class SuccessKilledDaoTest {

	@Autowired
	private SuccessKilledDao successKilledDao;
	
	@Test
	public void testInsertSuccessKilled() {
		
		int num = successKilledDao.insertSuccessKilled(1000L, 10753224349L);
		
		System.out.println(num);
	}

	@Test
	public void testQueryByIdWithSeckill() {
		SuccessKilled s = successKilledDao.queryByIdWithSeckill(1000L,10753224349L);
		
		System.out.println(s);
	}

}

```

### java高并发秒杀API之Service层

### 秒杀Service接口设计

``` SeckillService.java
package org.seckill.service;

import java.util.List;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entiy.Seckill;
import org.seckill.exception.SeckillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.RepeatKillException;

/**
 * 业务接口：站在"使用者"角度设计接口 三个方面：方法定义粒度，参数，返回类型（return 类型）
 * 
 * @author admin
 *
 */
public interface SeckillService {

	/**
	 * 查询所有秒杀记录
	 * 
	 * @return
	 */
	List<Seckill> getSeckillList();

	/**
	 * 查询单个秒杀记录
	 * 
	 * @param seckillId
	 * @return
	 */
	Seckill getById(long seckillId);

	/**
	 * 暴露秒杀接口 秒杀开启时输出秒杀接口地址， 否则输出系统时间和秒杀时间
	 * 
	 * @param seckillId
	 * @return
	 */
	Exposer exportSeckillUrl(long seckillId);

	/**
	 * 执行秒杀
	 * 
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 */
	SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, RepeatKillException , SeckillCloseException;

}

```

### 配置并使用spring声明式事务

``` spring-service.xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:context="http://www.springframework.org/schema/context" xmlns:p="http://www.springframework.org/schema/p"
	xmlns:aop="http://www.springframework.org/schema/aop" xmlns:tx="http://www.springframework.org/schema/tx"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.0.xsd
	http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.0.xsd
	http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.0.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.0.xsd
	http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util-4.0.xsd">

	<!-- 扫描service包下所有使用注解的类型 -->
	<context:component-scan base-package="org.seckill.service" />
	
	<!-- 配置事务管理器 -->
	<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<!-- 注入数据库连接池 -->
		<property name="dataSource" ref="dataSource"></property>
	</bean>
	
	<!-- 配置基于注解的声明式事务
		默认使用注解管理事务行为
	 -->
	<tx:annotation-driven transaction-manager="transactionManager"/>
</beans>
```

### java高并发秒杀API之Service层

### 配置web.xml
``` web.xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://java.sun.com/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
                      http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
         version="3.0"
         metadata-complete="true">
	<display-name>seckill</display-name>
	<welcome-file-list>
		<welcome-file>index.html</welcome-file>
		<welcome-file>index.htm</welcome-file>
		<welcome-file>index.jsp</welcome-file>
		<welcome-file>default.html</welcome-file>
		<welcome-file>default.htm</welcome-file>
		<welcome-file>default.jsp</welcome-file>
	</welcome-file-list>

	<!-- 配置DispatcherServlet -->
	
	<servlet>
		<servlet-name>seckill-dispatcher</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<!-- 配置springMVC需要加载的文件
			spring-dao.xml spring-service.xml spring-web.xml
			Mybatis->spring->springMVC
		 -->
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>classpath:spring/spring-*.xml</param-value>
		</init-param>
	</servlet>
	<servlet-mapping>
		<servlet-name>seckill-dispatcher</servlet-name>
		<!-- 默认匹配所有的请求 -->
		<url-pattern>/</url-pattern>
	</servlet-mapping>
</web-app>
```


### 整合配置spring-mvc配置
``` spring-web.xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:p="http://www.springframework.org/schema/p"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:mvc="http://www.springframework.org/schema/mvc"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.0.xsd
        http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-4.0.xsd
        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.0.xsd">

	<!-- 配置springMVC -->
	<!-- 1、开启SpringMVC注解模式 -->
	<!-- 简化配置：
		(1)自动注册：DefaultAnnotationHandlerMapping,AnnotationMethodHandlerAdapter
		(2)提供了一些列的功能：数据绑定，数字和日期的format @NumberFormat,@DataTimeFormat
		xml,json的默认读写支持
	 -->
	<mvc:annotation-driven />
	
	<!-- 2、静态资源默认servlet配置 
		1、加入对静态资源的处理
		2、允许使用'/'做整体映射
	-->
	<mvc:default-servlet-handler/>


	<!-- 3、配置jsp 显示ViewResolver -->
	<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="viewClass" value="org.springframework.web.servlet.view.JstlView"></property>
		<property name="prefix" value="/WEB-INF/jsp/"></property>
		<property name="suffix" value=".jsp"></property>
	</bean>
	
	
	<!-- 4、扫描web相关的bean -->
	<context:component-scan base-package="org.seckill.web" />
	
</beans>
```

### SeckillController类的编写
``` SeckillController.java
package org.seckill.web;

import java.util.Date;
import java.util.List;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.dto.SeckillResult;
import org.seckill.entiy.Seckill;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/seckill") // 模块 url:/模块/资源/{id}/细分
public class SeckillController {

	@Autowired
	private SeckillService seckillService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		// 获取列表页
		List<Seckill> list = seckillService.getSeckillList();

		model.addAttribute("list", list);
		// list.jsp + model = ModelAndView
		return "list";
	}

	@RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
		// 详情页
		if (seckillId == null) {
			return "redirect:/seckill/list";
		}

		Seckill seckill = seckillService.getById(seckillId);

		if (seckill == null) {
			return "forward:/seckill/list";
		}

		model.addAttribute("seckill", seckill);
		return "detail";
	}

	@RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId) {
		// 暴露秒杀接口
		SeckillResult<Exposer> result;
		
		try {
			Exposer exposer = seckillService.exportSeckillUrl(seckillId);
			
			System.out.println("exposer-->"+exposer);
			
			result = new SeckillResult<Exposer>(true, exposer);
		} catch (Exception e) {
			System.out.println(e);
			result = new SeckillResult<Exposer>(false, e.getMessage());
		}
		
		return result;
	}

	@RequestMapping(value = "/{seckillId}/{md5}/execution", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
			@PathVariable("md5") String md5, @CookieValue(value = "KillPhone", required = false) Long phone) {

		if (phone == null) {
			return new SeckillResult<SeckillExecution>(false, "用户未注册");
		}

		SeckillResult<SeckillExecution> result;

		try {
			SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, phone, md5);
			return new SeckillResult<SeckillExecution>(true, seckillExecution);

		} catch (RepeatKillException e) {
			System.out.println(e.getMessage());

			SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnum.REPEAT_KILL);
			return new SeckillResult<SeckillExecution>(true, seckillExecution);

		} catch (SeckillCloseException e) {
			System.out.println(e.getMessage());

			SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnum.END);
			return new SeckillResult<SeckillExecution>(true, seckillExecution);

		} catch (Exception e) {
			System.out.println(e.getMessage());

			SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnum.INNER_ERROR);
			return new SeckillResult<SeckillExecution>(true, seckillExecution);
		}

	}

	@RequestMapping(value = "/time/now", method = RequestMethod.GET)
	@ResponseBody
	public SeckillResult<Long> time() {
		// 获取当前系统的时间

		Date date = new Date();

		return new SeckillResult<Long>( true , date.getTime());
	}
}

```









  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
