


package com.UndefinedParameter.jdbi;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlBatch;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.UndefinedParameter.app.core.Quiz;

@RegisterMapper(QuizMapper.class)
public interface QuizDAO {

	
	/*
	 * Retrieve quizzes from the database, order by rating
	 * 	TODO: Restrict this to a number of quizzes by adding a range (first 100, second 100, etc)
	 */
	@SqlQuery("SELECT q.*, sg.Name AS GroupName, sg.GroupID, u.FirstName, u.LastName FROM " + 
				"Quiz q, SubGroup sg, GroupQuiz gq, User u WHERE " + 
				"q.QuizID = gq.QuizID AND " + 
				"sg.GroupID = gq.GroupID AND " + 
				"u.UserID = q.CreatorID " +
				"ORDER BY Rating / RatingCount DESC")
	public List<Quiz> retrieveTopQuizzes();
	
	/*
	 * Retrieve quizzes from the database, order by date
	 * 	TODO: Restrict this to a number of quizzes by adding a range (first 100, second 100, etc)
	 */
	@SqlQuery("SELECT q.*, sg.Name AS GroupName, sg.GroupID, u.FirstName, u.LastName FROM " +
				"Quiz q, SubGroup sg, GroupQuiz gq, User u WHERE " +
				"q.QuizID = gq.QuizID AND " + 
				"sg.GroupID = gq.GroupID AND " + 
				"u.UserID = q.CreatorID " +
				"ORDER BY DateCreated DESC")
	public List<Quiz> retrieveRecentQuizzes();
	
	/*
	 * 	Retrieve a list of quizzes from the database by group id
	 */
	@SqlQuery("SELECT quiz.*, sub.Name AS GroupName, sub.GroupID, u.FirstName, u.LastName "
				+ "FROM Quiz quiz, SubGroup sub, GroupQuiz gquiz, User u "
				+ "WHERE quiz.QuizID = gquiz.QuizID "
				+ "AND sub.GroupID = gquiz.GroupID "
				+ "AND u.UserID = quiz.CreatorID "
				+ "AND sub.GroupID = :groupId")
	public List<Quiz> retrieveQuizzesByGroup(@Bind("groupId") long groupId);
	
	/*
	 * Retrieve Quizzes by creator ID
	 */
	@SqlQuery("SELECT q.*, sg.Name AS GroupName, sg.GroupID, u.FirstName, u.LastName "
			+ "FROM Quiz q, SubGroup sg, GroupQuiz gq, User u "
			+ "WHERE q.QuizID = gq.QuizID "
			+ "AND sg.GroupID = gq.GroupID "
			+ "AND u.UserID = q.CreatorID "
			+ "AND q.CreatorID = :creatorId")
	public List<Quiz> retrieveQuizzesByCreatorId(@Bind("creatorId") long creatorId);
	
	/*
	 * Retrieve Quizzes by group id and creator Id
	 */
	@SqlQuery("SELECT q.*, sg.Name AS GroupName, sg.GroupID, u.FirstName, u.LastName "
			+ "FROM Quiz q, GroupQuiz gq, SubGroup sg, User u WHERE "
			+ "q.QuizID = gq.QuizID AND "
			+ "sg.GroupID = gq.GroupID AND "
			+ "u.UserID = q.CreatorID AND "
			+ "CreatorID = :creatorId AND "
			+ "gq.GroupID = :groupId")
	public List<Quiz> retrieveQuizzesByCreatorAndGroup(@Bind("creatorId") long creatorId, @Bind("groupId") long groupId);
	
	/*
	 * 	retrieveExistingQuizDetails - Retrieves quiz details from the database.
	 */
	@SqlQuery("SELECT q.*, sg.Name AS GroupName, sg.GroupID, u.FirstName, u.LastName "
			+ "FROM Quiz q, User u, SubGroup sg, GroupQuiz gq "
			+ "WHERE q.QuizID = gq.QuizID "
			+ "AND sg.GroupID = gq.GroupID "
			+ "AND q.CreatorID = u.UserID "
			+ "AND q.QuizID = :quizId")
	public Quiz retrieveExistingQuizDetails(@Bind("quizId") long quizId);
	
	/*
	 * 	deleteQuiz - Deletes quiz from quiz id.
	 */
	@SqlUpdate("DELETE FROM Quiz WHERE QuizID = :quizId")
	public void deleteQuiz(@Bind("quizId") long quizId);
	
	/*
	 * delete Quiz to Question Association
	 */
	@SqlUpdate("DELETE FROM QuizQuestion WHERE QuizID = :quizId AND QuestionID = :questionId")
	public void deleteQuizQuestion(@Bind("quizId") long quizId, @Bind("questionId") long questionId);
	
	@SqlUpdate("SELECT COUNT(*) FROM QuizQuestion WHERE QuizID = :quizId")
	public void countQuestions(@Bind("quizId") long quizId);
	
	/*
	 * delete Quiz to Tag association
	 */
	@SqlUpdate("DELETE FROM TagQuiz WHERE QuizID = :quizId")
	public void deleteQuizTag(@Bind("quizId") long quizId);
	
	/*
	 * 	createQuiz - Adds a quiz to the database. Assume all questions
	 * 	already exist in the database.
	 */
	@SqlUpdate("INSERT INTO Quiz (CreatorID, Name, Difficulty, Rating, Description, Time, Open) VALUES(:creatorId, :name, :diff, :rating, :desc, :time, :open)")
	@GetGeneratedKeys
	public long createQuiz(@Bind("creatorId") long creatorId, @Bind("name") String name, @Bind("diff") float diff, @Bind("rating") double rating, @Bind("desc") String desc, @Bind("time") int time, @Bind("open") boolean open);
	
	/*
	 * 	linkToQuestions - Helper method will link quiz to its questions
	 * 	in the database.
	 */
	@SqlBatch("INSERT INTO TagQuestion (QuestionID, TagID) VALUES (:questionId, tagId)")
	public void linkToQuestions(@Bind("questionId") long questionId, @Bind("tagId") List<Long> tagIds);
	
	
	/*
	 * Adds questions into a quiz
	 */
	@SqlUpdate("INSERT INTO QuizQuestion (QuizId, QuestionId) VALUES(:quizId, :questionId)")
	@GetGeneratedKeys
	public long addQuestion(@Bind("quizId") long quizId, @Bind("questionId") long questionId);
	
	/*
	 * Delete a question from a quiz
	 */
	@SqlUpdate("DELETE FROM QuizQuestion WHERE QuizID = :quizId AND QuestionID = :questionId")
	@GetGeneratedKeys
	public long removeQuestionFromQuiz(@Bind("quizId") long quizId, @Bind("questionId") long questionId);
	
	/*
	 * Links a quiz to a group
	 */
	@SqlUpdate("INSERT INTO GroupQuiz (QuizID, GroupID) VALUES(:quizId, :groupId)")
	public void linkToGroup(@Bind("quizId") long quizId, @Bind("groupId") long groupId);
	
	@SqlUpdate("UPDATE Quiz SET QuestionCount = QuestionCount + 1 WHERE QuizID = :quizId")
	public void incrementQuestionCount(@Bind("quizId") long quizId);
	
	@SqlUpdate("UPDATE Quiz SET QuestionCount = QuestionCount - 1 WHERE QuizID = :quizId")
	public void decrementQuestionCount(@Bind("quizId") long quizId);
	
	/********************************** Quiz Quality Ratings Query *****************************************************/
	
	@SqlUpdate("UPDATE Quiz SET Rating = Rating + :rating, RatingCount = RatingCount + 1 WHERE QuizID = :quizId")
	public void rateQuizQuality(@Bind("rating") int rating, @Bind("quizId") long quizId);
	
	@SqlUpdate("UPDATE Quiz SET Rating = Rating + :rating WHERE QuizID = :quizId")
	public void updateQuizQualityRating(@Bind("rating") int rating, @Bind("quizId") long quizId);
	
	@SqlQuery("SELECT UserRating FROM QuizRating WHERE QuizID = :quizId and UserID = :userId")
	public int getQuizRating(@Bind("quizId") long quizId, @Bind("userId") long userId);
	
	@SqlUpdate("INSERT INTO QuizRating (QuizID, UserID, UserRating) VALUES(:quizId, :userId, :rating)")
	@GetGeneratedKeys
	public long insertQuizRating(@Bind("quizId") long quizId, @Bind("userId") long userId, @Bind("rating") int rating);
	
	@SqlUpdate("UPDATE QuizRating SET UserRating = :rating WHERE UserID = :userId AND QuizID = :quizId")
	public void updateQuizRating(@Bind("userId") long userId, @Bind("quizId") long quizId, @Bind("rating") int rating);
	
	/********************************* Quiz Difficulty Ratings Query *******************************************************/
	
	@SqlUpdate("UPDATE Quiz SET Difficulty = Difficulty + :rating, DifficultyCount = DifficultyCount + 1 WHERE QuizID = :quizId")
	public void rateQuizDifficulty(@Bind("rating") int rating, @Bind("quizId") long quizId);
	
	@SqlUpdate("UPDATE Quiz SET Difficulty = Difficulty + :rating WHERE QuizID = :quizId")
	public void updateQuizDifficultyRating(@Bind("rating") int rating, @Bind("quizId") long quizId);
	
	@SqlQuery("SELECT UserRating FROM QuizDifficulty WHERE QuizID = :quizId and UserID = :userId")
	public int getQuizDifficulty(@Bind("quizId") long quizId, @Bind("userId") long userId);
	
	@SqlUpdate("INSERT INTO QuizDifficulty (QuizID, UserID, UserRating) VALUES(:quizId, :userId, :rating)")
	@GetGeneratedKeys
	public long insertQuizDifficulty(@Bind("quizId") long quizId, @Bind("userId") long userId, @Bind("rating") int rating);
	
	@SqlUpdate("UPDATE QuizDifficulty SET UserRating = :rating WHERE UserID = :userId AND QuizID = :quizId")
	public void updateQuizDifficulty(@Bind("userId") long userId, @Bind("quizId") long quizId, @Bind("rating") int rating);
}
	
	/*
	 *   LEAVING THIS COMMENTED FOR NOW IN CASE THE ABOVE DOESN'T WORK
	 * 
	 * 
	private static void linkToQuestions(Quiz quiz)
	{
		int result = -1;
		long quizID = quiz.getQuizId();
		Question[] questions = quiz.getQuestions();
		
		// Using UNION to add multiple rows at a time to the database.
		String insert = "INSERT INTO TagQuestion "
				+ "SELECT " + quizID + " AS QuizID, " + questions[0].getQuestionId() + " AS QuestionID ";
		
		for(int i = 1; i < questions.length; i++)
			insert += "UNION SELECT " + quizID + ", " + questions[i].getQuestionId() + " ";
		
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			connection.setAutoCommit(false);
			
			statement = connection.createStatement();
			result = statement.executeUpdate(insert);
			
			connection.commit();
			
			statement.close();
			connection.close();	
		}
		catch(Exception e){
			String errorMsg = "Quiz " + quizID + " could not be linked to its questions. result = " + result; 
			logger.error(errorMsg);
			e.printStackTrace();
		}
	} */



