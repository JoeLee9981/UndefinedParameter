package com.UndefinedParameter.jdbi;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.UndefinedParameter.app.core.NewsArticle;

@RegisterMapper(NewsArticleMapper.class)
public interface NewsArticleDAO {

	/**
	 * Selects everything from news article.
	 * @return
	 */
	@SqlQuery("SELECT * FROM NewsArticle")
	public List<NewsArticle> findAllNews();
	
	/**
	 * Selects an specific new article, determined by the news id.
	 * @param id
	 * @return
	 */
	@SqlQuery("SELECT * FROM NewsArticle WHERE NewsID = :id")
	public NewsArticle findNewsById(@Bind("id") int id);
	
	/**
	 * Inserts a value into NewArticle, with the specified headline and body.
	 * @param headline
	 * @param body
	 */
	@SqlUpdate("INSERT INTO NewsArticle (Headline, Body) values (:headline, :body)")
	void insert(@Bind("headline") String headline, @Bind("body") String body);
	
	void close();
}
