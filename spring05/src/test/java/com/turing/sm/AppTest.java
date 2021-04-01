package com.turing.sm;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.turing.sm.config.SpringConfig;
import com.turing.sm.entity.Account;
import com.turing.sm.service.AccountService;

import junit.framework.TestCase;

public class AppTest 
    extends TestCase
{
    //测试添加
	@Test
	public void testAdd(){
		//创建上下文对象
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
		//获取bean
		AccountService accountService = ctx.getBean(AccountService.class);
		//调用添加
		Account acc = new Account();
		acc.setAname("刘德华");
		acc.setAmoney(200);
		accountService.addAccount(acc);
		System.out.println("添加成功");
	}
	
	//测试转账
	@Test
	public void testTransAccount(){
		//创建上下文对象
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
		//获取bean
		AccountService accountService = ctx.getBean(AccountService.class);
		//调用转账
		accountService.transAccount();
		System.out.println("转账成功");
	}
}
