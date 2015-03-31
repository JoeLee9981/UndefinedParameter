package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.List;

import com.UndefinedParameter.app.core.OrgMember;

public class GroupMemberView extends View {

	List<OrgMember> members;
	
	public GroupMemberView(List<OrgMember> members) {
		super("group_members.ftl");
		this.members = members;
	}

	public List<OrgMember> getMembers() {
		return this.members;
	}
}