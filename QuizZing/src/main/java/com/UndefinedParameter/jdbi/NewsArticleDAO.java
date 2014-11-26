package com.UndefinedParameter.jdbi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.UndefinedParameter.app.core.NewsArticle;

public class NewsArticleDAO {

	private static final String dbPath = "database\\QuizZing";
	
	
	public static List<NewsArticle> selectAllNews() {
		
		ArrayList<NewsArticle> news = new ArrayList<NewsArticle>();
		String select = "SELECT * FROM NewsArticle";
		
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			connection.setAutoCommit(false);
			
			statement = connection.createStatement();
			ResultSet results = statement.executeQuery(select);
			
			if(results.next()) {
				int id = results.getInt("NewsID");
				String headline = results.getString("Headline");
				String body = results.getString("Body");
				news.add(new NewsArticle(id, headline, body));
			}
			results.close();
			statement.close();
			connection.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
		return news;
	}
	
	public static NewsArticle getNewsById(int newsId) {
		
		NewsArticle news = new NewsArticle();
		String select = "SELECT * FROM NewsArticle WHERE NewsID = ?";
		
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			connection.setAutoCommit(false);
			
			statement = connection.prepareStatement(select);
			statement.setInt(1, newsId);
			ResultSet results = statement.executeQuery();
			
			if(results.next()) {
				int id = results.getInt("NewsID");
				String headline = results.getString("Headline");
				String body = results.getString("Body");
				news.setId(id);
				news.setHeadline(headline);
				news.setBody(body);
			}
			results.close();
			statement.close();
			connection.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
		return news;
	}
	
}