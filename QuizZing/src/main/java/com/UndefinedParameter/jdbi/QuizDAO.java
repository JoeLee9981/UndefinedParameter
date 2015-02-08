


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
	 * 	Retrieve a list of quizzes from the database by group id
	 */
	@SqlQuery("SELECT quiz.* "
				+ "FROM Quiz quiz, SubGroup sub, GroupQuiz gquiz "
				+ "WHERE quiz.QuizID = gquiz.QuizID "
				+ "AND sub.GroupID = gquiz.GroupID "
				+ "AND sub.GroupID = :groupId")
	public List<Quiz> retrieveQuizzesByGroup(@Bind("groupId") long groupId);
	
	/*
	 * Retrieve Quizzes by creator ID
	 */
	@SqlQuery("SELECT * FROM Quiz WHERE CreatorID = :creatorId")
	public List<Quiz> retrieveQuizzesByCreatorId(@Bind("creatorId") long creatorId);
	
	/*
	 * Retrieve Quizzes by group id and creator Id
	 */
	@SqlQuery("SELECT * FROM Quiz q, GroupQuiz gq, SubGroup sg WHERE "
			+ "q.QuizID = gq.QuizID AND "
			+ "sg.GroupID = gq.GroupID AND "
			+ "CreatorID = :creatorId AND "
			+ "gq.GroupID = :groupId")
	public List<Quiz> retrieveQuizzesByCreatorAndGroup(@Bind("creatorId") long creatorId, @Bind("groupId") long groupId);
	
	/*
	 * 	retrieveExistingQuizDetails - Retrieves quiz details from the database.
	 */
	@SqlQuery("SELECT * FROM Quiz WHERE QuizID = :quizId")
	public Quiz retrieveExistingQuizDetails(@Bind("quizId") long quizId);
	
	/*
	 * 	deleteQuiz - Deletes quiz from quiz id.
	 */
	@SqlUpdate("DELETE FROM Quiz WHERE QuizID = :quizId")
	public void deleteQuiz(@Bind("quizId") long quizId);
	
	/*
	 * delete Quiz to Question Association
	 */
	@SqlUpdate("DELETE FROM QuizQuestion WHERE QuizID = :quizId")
	public void deleteQuizQuestion(@Bind("quizId") long quizId);
	
	/*
	 * delete Quiz to Tag association
	 */
	@SqlUpdate("DELETE FROM TagQuiz WHERE QuizID = :quizId")
	public void deleteQuizTag(@Bind("quizId") long quizId);
	
	/*
	 * 	createQuiz - Adds a quiz to the database. Assume all questions
	 * 	already exist in the database.
	 */
	@SqlUpdate("INSERT INTO Quiz (CreatorID, Name, Difficulty, Rating, Description, Time) VALUES(:creatorId, :name, :diff, :rating, :desc, :time)")
	@GetGeneratedKeys
	public long createQuiz(@Bind("creatorId") long creatorId, @Bind("name") String name, @Bind("diff") float diff, @Bind("rating") double rating, @Bind("desc") String desc, @Bind("time") int time);
	
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
	public void addQuestion(@Bind("quizId") long quizId, @Bind("questionId") long questionId);
	
	/*
	 * Links a quiz to a group
	 */
	@SqlUpdate("INSERT INTO GroupQuiz (QuizID, GroupID) VALUES(:quizId, :groupId)")
	public void linkToGroup(@Bind("quizId") long quizId, @Bind("groupId") long groupId);
	
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



