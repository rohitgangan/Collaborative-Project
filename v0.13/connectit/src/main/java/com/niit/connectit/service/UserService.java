package com.niit.connectit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niit.connectit.daoimpl.UserDAOImpl;
import com.niit.connectit.model.NewUser;

@Service
public class UserService {
	@Autowired
	private UserDAOImpl userDAOImpl;
	
	@Transactional
	public boolean addUSer(NewUser user) {
		if(userDAOImpl.userExists(user.getUsername())){
			return false;
		}else{
			userDAOImpl.addUSer(user);
			return true;
		}
	}
	
	@Transactional
	public List<NewUser> listUser() {
		return userDAOImpl.listUser();
		
	}
	
	@Transactional
	public NewUser getUserById(int userId) {
		return userDAOImpl.getUserById(userId);
		
	}
	
	@Transactional
	public void deleteUser(int userId){
		userDAOImpl.deleteUser(userId);
	}
	
	@Transactional
	public void updateUser(NewUser user) {
		userDAOImpl.updateUser(user);
	}
	
	@Transactional
	public NewUser getByUsername(String username) {
		return userDAOImpl.getByUsername(username);
		
	}
	
	@Transactional
	public List<NewUser> listNewUser() {
		return userDAOImpl.listNewUser();
	}
	
	@Transactional
	public void update(int userId) {
		userDAOImpl.update(userId);
	}
}
