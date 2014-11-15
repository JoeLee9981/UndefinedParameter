package jdbi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.UndefinedParameter.app.core.NewsArticle;

public class NewsArticleDAO {

	private static final String dbPath = "database\\tempDB";
	
	
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
			
			while(results.next()) {
				int id = results.getInt("id");
				String headline = results.getString("headline");
				String body = results.getString("body");
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
	
}
