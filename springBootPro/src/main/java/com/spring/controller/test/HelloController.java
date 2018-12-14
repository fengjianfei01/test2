package com.spring.controller.test;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.vo.UserVo;

@RestController
@RequestMapping("/test")
public class HelloController {
	
	@Value("${spring.redis.host}")
	private String redisIp;
	
//	@Value("${thread.name}")
//	private String threadName;

	/**
	 * @RequestMapping("/") 和 @RequestMapping 是有区别的
     * 如果不写参数，则为全局默认页，加入输入404页面，也会自动访问到这个页面。
     * 如果加了参数“/”，则只认为是根页面。
	 * @return
	 */
	@RequestMapping("/")
	public String index() {
		return "Hello Docker!";
	}
	
	@RequestMapping("/hello")
	public String hello() {
		//System.out.println("threadName:"+threadName);
		return "Hello World";
	}
	
	@RequestMapping("/getUserVo")
	public UserVo getUser() {
		UserVo userVo = new UserVo();
		userVo.setId(1);
		userVo.setIdNumber("1234567890");
		userVo.setName("test测试");
		userVo.setPhone("12345678901");
		userVo.setCreateDate(new Date());
		return userVo;
	}
	
	
	@RequestMapping("/getUserVoByPath/{name}")
	public UserVo getUserByPath(@PathVariable String name) {
		UserVo userVo = new UserVo();
		userVo.setId(1);
		userVo.setIdNumber("1234567890");
		userVo.setName("test测试");
		if(!StringUtils.isEmpty(name)) {
			userVo.setName(name);
		}
		userVo.setPhone("12345678901");
		userVo.setCreateDate(new Date());
		return userVo;
	}
	
	@RequestMapping("/getUserVoByParam")
	public UserVo getUserByParam(@RequestParam String name,@RequestParam int count,String param) {
		System.out.println("redisIp:"+redisIp+",name:"+name+",count:"+count+",param:"+param);
		UserVo userVo = new UserVo();
		userVo.setId(1);
		userVo.setIdNumber("1234567890");
		userVo.setName("test测试");
		if(!StringUtils.isEmpty(name)) {
			userVo.setName(name);
		}
		userVo.setPhone("12345678901");
		return userVo;
	}
	
	
}
