package com.spring.redis.repository;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spring.model.Account;

@Repository
public class AccountRedis {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	public void addAccount(String key,Long time,Account account) {
		Gson gson = new Gson();
		redisTemplate.opsForValue().set(key, gson.toJson(account), time, TimeUnit.MINUTES);
	}
	
	public void addAccount(String key,Long time,List<Account> accounts) {
		Gson gson = new Gson();
		redisTemplate.opsForValue().set(key, gson.toJson(accounts), time, TimeUnit.MINUTES);
	}
	
	public Account get(String key) {
		Gson gson = new Gson();
		Account account = null;
		String accountJson = redisTemplate.opsForValue().get(key);
		if(!StringUtils.isEmpty(accountJson)) {
			account = gson.fromJson(accountJson, Account.class);
		}
		return account;
	}
	
	public List<Account> getList(String key){
		Gson gson = new Gson();
		List<Account> list = null;
		String listJson = redisTemplate.opsForValue().get(key);
		if(!StringUtils.isEmpty(listJson)) {
			list = gson.fromJson(listJson, new TypeToken<List<Account>>(){}.getType());
		}
		return list;
	}
	
	public void delete(String key){
        redisTemplate.opsForValue().getOperations().delete(key);
    }
}
