package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.List;

import com.UndefinedParameter.app.core.User;

public class CategoriesView extends View {

	List<String> categories;
	User user;
	
	public CategoriesView(User user, List<String> categories) {
		super("../includes/categories.ftl");
		this.categories = categories;
		this.user = user;
	}
	
	public CategoriesView(String ftl, List<String> categories) {
		super(ftl);
		this.categories = categories;
	}
	
	public List<String> getCategories() {
		return this.categories;
	}
	
	public User getUser() {
		return user;
	}
}
