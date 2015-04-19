package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.List;

import com.UndefinedParameter.app.core.GroupMember;
import com.UndefinedParameter.app.core.OrgMember;

public class GroupMemberView extends View {

	List<GroupMember> members;
	
	public GroupMemberView(List<GroupMember> members) {
		super("group_members.ftl");
		this.members = members;
	}

	public List<GroupMember> getMembers() {
		return this.members;
	}
}
