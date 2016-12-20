package com.niit.connectit.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.niit.connectit.dao.UserDAO;
import com.niit.connectit.model.Blog;
import com.niit.connectit.model.NewUser;

@Repository
public class UserDAOImpl implements UserDAO{

	private SessionFactory sessionFactory;
	
	public boolean addUSer(NewUser user) {
		// TODO Auto-generated method stub
		user.setEnabled(true);
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<NewUser> listUser() {
		// TODO Auto-generated method stub
		List<NewUser> userList = this.sessionFactory.getCurrentSession().createQuery("from NewUser").getResultList();
		return userList;
	}

	@SuppressWarnings("unchecked")
	public NewUser getUserById(int userId) {
		// TODO Auto-generated method stub
		String hql = "from NewUser where userId = "+userId;
		List<NewUser> userList = this.sessionFactory.getCurrentSession().createQuery(hql).getResultList();
		if(userList != null && !userList.isEmpty())
		{
			return userList.get(0);
		}else{
			return null;
		}
	}

	public void deleteUser(int userId) {
		// TODO Auto-generated method stub
		NewUser userDelete = new NewUser();
		userDelete.setUserId(userId);
		this.sessionFactory.getCurrentSession().delete(userDelete);
	}

	public void updateUser(NewUser user) {
		// TODO Auto-generated method stub
		
	}

	public boolean userExists(String username) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
