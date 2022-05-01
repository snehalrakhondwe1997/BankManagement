package com.snehal.demo.Repository;

import java.util.List;

import javax.transaction.Transaction;


import org.springframework.data.jpa.repository.JpaRepository;

import com.snehal.demo.entity.Transactions;

public interface TransactionRepo  extends JpaRepository<Transactions, Integer>{

	List<Transactions> findByUserAcno(long acno);
	
	

}
