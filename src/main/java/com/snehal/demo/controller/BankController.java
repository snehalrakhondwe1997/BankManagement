package com.snehal.demo.controller;

import java.security.Provider.Service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.snehal.demo.entity.Transactions;
import com.snehal.demo.entity.User;
import com.snehal.demo.error.NoTransactionsException;
import com.snehal.demo.error.UserNotFoundException;
import com.snehal.demo.service.UserService;

@RestController
public class BankController {
	@Autowired
	UserService service;
	
	@PostMapping("/admin/save")
	public User saveUser(@RequestBody User user) {
		return service.saveUser(user);
	}
	@DeleteMapping("/admin/delete/{acno}")
	@Transactional
	public String removeUser(@PathVariable long acno)throws UserNotFoundException {
	
		return service.removeUser(acno);
	}
	@PutMapping("/admin/update/{acno}")
	public User updateUser(@PathVariable  long acno, @RequestBody User user) throws UserNotFoundException {
		return service.updateUser(acno,user);
	}

		@GetMapping("/admin/getAll/")
		public List<User> getUsers(){
			return service.getUsers();
			
		}
		@GetMapping("/admin/getuser/{acno}")
		public User getUser(@PathVariable long acno) {
			return service.getUser(acno);
		}
		@GetMapping("/admin/{n}")
		public List<Transactions> getTrans(@PathVariable int n) throws NoTransactionsException{
			return service.getTrans(n);
		}
		@PutMapping("/user/update/{acno}")
		public User updateUserPass(@PathVariable long acno,@RequestBody User user) {
			return service.updateUserPass(acno,user);
			
	}
		@GetMapping("/user/{acno}/{n}")
		public List<Transactions> getTransactions(@PathVariable("acno") long acno, @PathVariable("n") int n) throws NoTransactionsException {
			 return service.getTransactions(acno,n);
		}
		@PutMapping("/user/{acno}")
		public String depwit(@PathVariable long acno, @RequestBody Transactions tr) throws Exception {
			return service.depwit(acno, tr);
		}
		@GetMapping("/user/balance/{acno}")
		public String displayBal(@PathVariable long acno) {
			return service.displayBal(acno);
			
		
	}
}