package com.niit.connectit.dao;

import java.util.List;

import com.niit.connectit.model.NewUser;

public interface UserDAO {
	public boolean addUSer(NewUser user);
	public List<NewUser> listUser();
	public List<NewUser> listNewUser();
	public NewUser getUserById(int userId);
	public void deleteUser(int userId);
	public void updateUser(NewUser user);
	public void update(int userId);
	public boolean userExists(String username);
	public NewUser getByUsername(String username);
	
}
