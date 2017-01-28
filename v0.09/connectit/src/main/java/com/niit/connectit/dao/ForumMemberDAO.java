package com.niit.connectit.dao;

import java.util.List;

import com.niit.connectit.model.ForumMember;

public interface ForumMemberDAO {

	public boolean addForumMember(ForumMember forumMember);
	public List<ForumMember> listAllMembersByForum(int forumId);
	public void deleteForumMember(int forumMemberId);
}
