package com.snehal.demo.service;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.snehal.demo.Repository.TransactionRepo;
import com.snehal.demo.Repository.UserRepo;
import com.snehal.demo.entity.TransactionType;
import com.snehal.demo.entity.Transactions;
import com.snehal.demo.entity.User;
import com.snehal.demo.error.InvalidException;
import com.snehal.demo.error.LowBalanceException;
import com.snehal.demo.error.NoTransactionsException;
import com.snehal.demo.error.UserNotFoundException;
@Service
public class UserService implements UserDetailsService {
  @Autowired
   UserRepo repo;
  @Autowired
  TransactionRepo trepo;
  @Autowired
  PasswordEncoder passwordEncoder;


@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
	User user = repo.findByUsername(username);
	if(user==null)
		throw new UsernameNotFoundException("Username not found!!");
	
	return new ServicePrinciple(user);
	
}


public User saveUser(User user) {
	user.setPassword(passwordEncoder.encode(user.getPassword()));

	return repo.save(user);
}


public String removeUser(long acno) throws UserNotFoundException {
	
	 if(!repo.existsByAcno(acno))
		
		 throw new UserNotFoundException("Username not found!!");
	 else 
		 repo.deleteByAcno(acno);
	 return "Record Deleted!";
}
public User getUser(long acno) {

	return repo.findByAcno(acno);
}
public List<Transactions> getTrans(int n) throws NoTransactionsException {
	List<Transactions> tr = trepo.findAll();
	List<Transactions> res = new ArrayList<>();
	if(tr.size()==0)
		throw new NoTransactionsException("No Transactions Found");
	
	else {
	for(int i = 0; i < n && i<tr.size(); i++) {
		res.add(tr.get(i));
	}
	return res;
}
}	
	public User updateUser(long acno, User user) throws UserNotFoundException{
	User u;
	if(repo.existsByAcno(acno)) {
		u= repo.findByAcno(acno);
	
	if(Objects.nonNull(user.getAddress())|| !"".equals(user.getAddress()))
		u.setAddress(user.getAddress());
	
	if(Objects.nonNull(user.getCellno()))
		u.setCellno(user.getCellno());
	
	if(Objects.nonNull(user.getEmail())|| !"".equals(user.getEmail()))
		u.setEmail(user.getEmail());
	return repo.save(u);
}
else
	throw new UserNotFoundException("Username not Found!!");
}



public User updateUserPass(long acno, User user) {
	
	User u= repo.findByAcno(acno);
	if(Objects.nonNull(user.getUsername()) || !"".equals(user.getUsername()))
		u.setUsername(user.getUsername());
	if(Objects.nonNull(user.getPassword()) || !"".equals(user.getPassword()))
		u.setPassword(passwordEncoder.encode(user.getPassword()));
	return repo.save(u);
}


public List<Transactions> getTransactions(long acno, int n) throws NoTransactionsException {
	
	List<Transactions> tr =  trepo.findByUserAcno(acno);
	List<Transactions> res = new ArrayList<>();
	if(tr.size()==0)
		throw new NoTransactionsException("No Transactions Found");
	
	else {
	for(int i = 0; i < n && i<tr.size(); i++) {
		res.add(tr.get(i));
	}
	return res;
}
}


public String depwit(long acno, Transactions tr) throws Exception {
	User user= repo.findByAcno(acno);
	Date d= new Date();
	tr.setTime(d);
	tr.setUser(user);
	user.getTransactions().add(tr);
	double amount, amt;
	if(tr.getTrtype().equals(TransactionType.DEPOSIT)) {
		amount = user.getBalance() + tr.getAmount();
		user.setBalance(amount);
		repo.save(user);
		trepo.save(tr);
	}
	else if(tr.getTrtype().equals(TransactionType.WITHDRAW)) {
		if(user.getBalance()<tr.getAmount()) {
			throw new LowBalanceException("Your acount balance is low");
			
		}
		amount = user.getBalance() - tr.getAmount();
		user.setBalance(amount);
		repo.save(user);
		trepo.save(tr);
	}
	else if(tr.getTrtype().equals(TransactionType.TRANSFER)){
		if(!repo.existsByAcno(tr.getRacno())) {
			throw new UserNotFoundException("Username not found");	
		}
		else {
			User reciever = repo.findByAcno(tr.getRacno());
			if(user.getBalance()<tr.getAmount()) {
				throw new LowBalanceException("Your account balance is low");
			}
			amount = user.getBalance() - tr.getAmount();
			user.setBalance(amount);
			amt = reciever.getBalance() + tr.getAmount();
			reciever.setBalance(amt);
			repo.save(reciever);
			repo.save(user);
		
			trepo.save(tr);
		}
	}
	else {
		throw new InvalidException("Invalid transaction type");
	}
	return "Transaction Successful";
}

public String displayBal(long acno) {
	User user = repo.findByAcno(acno);
	return "Your account balance is Rs."+user.getBalance();
}


public List<User> getUsers() {
	return repo.findAll();
}


}
