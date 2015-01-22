package com.UndefinedParameter.jdbi;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.UndefinedParameter.app.core.NewsArticle;

public class NewsArticleMapper implements ResultSetMapper<NewsArticle> {

	public NewsArticle map(int index, ResultSet r, StatementContext ctx)
			throws SQLException {
		NewsArticle news = new NewsArticle();
		
		news.setId(r.getLong("NewsID"));
		news.setHeadline(r.getString("Headline"));
		news.setBody(r.getString("Body"));
		
		return news;
	}
	
}