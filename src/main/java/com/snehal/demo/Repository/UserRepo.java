package com.snehal.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

import com.snehal.demo.entity.User;
@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

	User findByUsername(String username);

      User findByAcno(Long acno);
      boolean existsByAcno(Long acno);

	void deleteByAcno(Long acno);
}
