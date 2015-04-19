package com.UndefinedParameter.views;

import io.dropwizard.views.View;

import java.util.List;

public class CategoriesView extends View {

	List<String> categories;
	
	public CategoriesView(List<String> categories) {
		super("../includes/categories.ftl");
		this.categories = categories;
	}
	
	public List<String> getCategories() {
		return this.categories;
	}
}
