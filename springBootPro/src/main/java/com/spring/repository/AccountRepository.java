package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
