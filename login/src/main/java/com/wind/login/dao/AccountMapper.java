package com.wind.login.dao;

import com.wind.login.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Account record);

    Account selectByPrimaryKey(Long id);

    List<Account> selectAll();

    int updateByPrimaryKey(Account record);
}