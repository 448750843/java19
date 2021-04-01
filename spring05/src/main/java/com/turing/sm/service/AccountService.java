package com.turing.sm.service;

import com.turing.sm.entity.Account;

/**
 * Account业务层接口
 * @author 大飞哥
 *
 */
public interface AccountService {

	int addAccount(Account acc);
	
	int transAccount();
	
	Account findById(int id);
}
