package com.niit.connectit.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.connectit.dao.UserDAO;
import com.niit.connectit.model.NewUser;

@Repository
public class UserDAOImpl implements UserDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean addUSer(NewUser user) {
		// TODO Auto-generated method stub
		user.setEnabled(false);
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
		NewUser u = getUserById(user.getUserId());
		u.setUsername(user.getUsername());
		u.setPassword(user.getPassword());
		u.setFirstName(user.getFirstName());
		u.setLastName(user.getLastName());
		u.setEmailId(user.getEmailId());
		sessionFactory.getCurrentSession().update(u);
	}

	@SuppressWarnings("unchecked")
	public boolean userExists(String username) {
		// TODO Auto-generated method stub
		String hql = "from NewUser where username='"+username+"'";
		List<NewUser> users = this.sessionFactory.getCurrentSession().createQuery(hql).getResultList();
		return users.size()>0 ? true : false;
	}

	@SuppressWarnings("unchecked")
	public NewUser getByUsername(String username) {
		// TODO Auto-generated method stub
		String hql = "from NewUser where username='"+username+"'";
		List<NewUser> users = this.sessionFactory.getCurrentSession().createQuery(hql).getResultList();
		if(users != null && !users.isEmpty())
		{
			return users.get(0);
		}else{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<NewUser> listNewUser() {
		// TODO Auto-generated method stub
		String hql = "from NewUser where enabled = 0";
		List<NewUser> userList = this.sessionFactory.getCurrentSession().createQuery(hql).getResultList();
		return userList;
	}

	public void update(int userId) {
		// TODO Auto-generated method stub
		String hql="update NewUser set enabled=1 where userId=" +userId;
		sessionFactory.getCurrentSession().createQuery(hql).executeUpdate();
	}
	
	
	
}
