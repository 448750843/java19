package com.turing.sm.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.turing.sm.entity.Account;
import com.turing.sm.service.AccountService;
/**
 * Account业务层实现类
 * @author 大飞哥
 *
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	
//	@Autowired
//	private SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	private SqlSessionTemplate template;

	@Override
	public int addAccount(Account acc) {
		
		//第一种方式：使用MyBatis的原生方式
		//打开会话
//		SqlSession sqlSession = sqlSessionFactory.openSession();
//		//调用方法
//		int result = sqlSession.insert("insert", acc);
//		//关闭会话
//		sqlSession.close();
//		return result;
		
		//第二种方式：使用MyBatis的模板---SqlSessionTemplate
		return template.insert("insert", acc);
	}

	/**
	 * 转账业务
	 */
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public int transAccount() {
		//张三给李四转100元
		
		//李四的钱+100
		Account lisi = findById(2);
		lisi.setAmoney(lisi.getAmoney()+100);
		int result1 = template.update("updateByPrimaryKeySelective", lisi);
		
		//搞点事情
		int a = 1/0;
		
		//张三的钱-100
		Account zhangsan = findById(1);
		zhangsan.setAmoney(zhangsan.getAmoney()-100);
		int result2 = template.update("updateByPrimaryKeySelective", zhangsan);
		
		return result1+result2;
	}

	@Override
	public Account findById(int id) {
		return template.selectOne("selectByPrimaryKey", id);
	}

}
