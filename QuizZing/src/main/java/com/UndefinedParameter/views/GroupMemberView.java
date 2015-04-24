package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.List;

import com.UndefinedParameter.app.core.GroupMember;
import com.UndefinedParameter.app.core.User;

public class GroupMemberView extends View {

	List<GroupMember> members;
	User user;
	
	public GroupMemberView(User user, List<GroupMember> members) {
		super("group_members.ftl");
		this.members = members;
		this.user = user;
	}

	public List<GroupMember> getMembers() {
		return this.members;
	}
	
	public User getUser() {
		return this.user;
	}
}
