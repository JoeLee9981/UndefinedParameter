package com.UndefinedParameter.jdbi;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.UndefinedParameter.app.core.Question;

@RegisterMapper(QuestionMapper.class)
public interface QuestionDAO {
	
	/*
	 * 	CreateAQuestion. Will get a question from the database.
	 */
	@SqlQuery("SELECT * FROM Question WHERE QuestionID = :questionId")
	public Question getQuestion(@Bind("questionId") long questionId);
	
	/*
	 * Get all questions
	 * TODO: Restrict this to categories
	 */
	@SqlQuery("SELECT * FROM Question ORDER BY QuestionID")
	public List<Question> getAllQuestions();
	
	
	/*
	 * 	retrieveExistingQuiz - Retrieves quiz questions from a specific existing quiz id.
	 */
	@SqlQuery("SELECT Qt.* "
			+ "FROM Question Qt, Quiz Qz, QuizQuestion Qq "
			+ "WHERE Qt.QuestionID = Qq.QuestionID "
			+ "AND Qq.QuizID = Qz.QuizID "
			+ "AND Qq.QuestionID = Qt.QuestionID "
			+ "AND Qz.QuizID = :quizId")
	public List<Question> retrieveExistingQuiz(@Bind("quizId") long quizId);
	
	/*
	 * 	CreateAQuestion. Will add a new question to the database.
	 */
	@SqlUpdate("INSERT INTO Question "
				+ "(CreatorID, GroupID, "
				+ "QuestionText, CorrectAnswer, QuestionType, WrongAnswer1, WrongAnswer2, WrongAnswer3, WrongAnswer4, Flagged, Explanation, Reference, Ordered, CorrectPosition) "
				+ "VALUES (:creatorId, :groupId, :questionText, :correctAnswer, :questionType, :wrongAnswer1, :wrongAnswer2, "
				+ ":wrongAnswer3, :wrongAnswer4, :flagged, :explanation, :reference, :ordered, :correctPos)")
	@GetGeneratedKeys
	public long createQuestion(@Bind("creatorId") long creatorId, 
							   @Bind("groupId") long groupId,
							   @Bind("questionText") String questionText,
							   @Bind("correctAnswer") String correctAnswer,
							   @Bind("questionType") String questionType,
							   @Bind("wrongAnswer1") String wrongAnswer1,
							   @Bind("wrongAnswer2") String wrongAnswer2,
							   @Bind("wrongAnswer3") String wrongAnswer3,
							   @Bind("wrongAnswer4") String wrongAnswer4,
							   @Bind("flagged") boolean flagged,
							   @Bind("explanation") String explanation,
							   @Bind("reference") String reference,
							   @Bind("ordered") boolean ordered,
							   @Bind("correctPos") int correctPos);
	
	/*
	 * 	retrieveQuiz - Retrieves questions from a specific group id.
	 */
	@SqlQuery("SELECT * FROM Question WHERE GroupID = :groupId")
	public List<Question> getQuestionsByGroupId(@Bind("groupId") long groupId);
	
	/*
	 * 	retrieveQuiz - Retrieves questions from a specific group id where the given user is the creator.
	 */
	@SqlQuery("SELECT * FROM Question WHERE GroupID = :groupId AND CreatorID = :userId")
	public List<Question> getQuestionsByUserAndGroup(@Bind("groupId") long groupId, @Bind("userId") long userId);
	
	/*
	 * 	retrieveQuiz - Retrieves questions from a specific group id excluding added questions
	 */
	@SqlQuery("SELECT * FROM Question q WHERE GroupID = :groupId AND NOT EXISTS "
			+ "(SELECT * FROM QuizQuestion qq WHERE qq.QuestionID = q.QuestionID AND qq.QuizID = :quizId)")
	public List<Question> getUnaddedQuizQuestionsByGroup(@Bind("groupId") long groupId, @Bind("quizId") long quizId);
	
	/*
	 * Update a quiz
	 */
	@SqlUpdate("UPDATE Question SET QuestionText = :questionText, CorrectAnswer = :correctAnswer, WrongAnswer1 = :wrongAnswer1, "
			+ "WrongAnswer2 = :wrongAnswer2, WrongAnswer3 = :wrongAnswer3, WrongAnswer4 = :wrongAnswer4, Flagged = 0, FlaggedReason = 'None', Explanation = :explanation, "
			+ "Reference = :reference, Ordered = :ordered, CorrectPosition = :correctPosition WHERE QuestionId = :questionid")
	public void updateQuestion(@Bind("questionText") String questionText, 
							   @Bind("correctAnswer") String correctAnswer, 
							   @Bind("wrongAnswer1") String wrongAnswer1,
							   @Bind("wrongAnswer2") String wrongAnswer2,
							   @Bind("wrongAnswer3") String wrongAnswer3,
							   @Bind("wrongAnswer4") String wrongAnswer4,
							   @Bind("explanation") String explanation,
							   @Bind("reference") String reference,
							   @Bind("ordered") boolean ordered,
							   @Bind("correctPosition") int correctPosition,
							   @Bind("questionid") long questionId);
	
	@SqlQuery("SELECT CategoryID FROM Category WHERE CategoryType = :category")
	public long getCategoryId(@Bind("category") String category);
	
	@SqlUpdate("INSERT INTO Category (CategoryType) VALUES (:category)")
	@GetGeneratedKeys
	public long createCategory(@Bind("category") String category);
	
	@SqlQuery("SELECT CategoryType FROM Category c, Question q, QuestionCategory qc WHERE "
			+ "c.CategoryID = qc.CategoryID AND "
			+ "q.QuestionID = qc.QuestionID AND "
			+ "q.QuestionID = :questionId")
	public List<String> getCategoriesByQuestion(@Bind("questionId") long questionId);
	
	@SqlUpdate("INSERT INTO QuestionCategory (QuestionID, CategoryID) VALUES (:questionId, :categoryId)")
	@GetGeneratedKeys
	public long addCategoryToQuestion(@Bind("questionId") long questionId, @Bind("categoryId") long categoryId);
	
	@SqlUpdate("DELETE FROM QuestionCategory WHERE QuestionID = :questionId")
	public void removeCategories(@Bind("questionId") long questionId);
	
	@SqlQuery("SELECT CategoryType FROM Category ORDER BY CategoryType")
	public List<String> getAllCategories();
	
	@SqlQuery("SELECT cat.CategoryType FROM (SELECT CategoryType, COUNT(*) AS count "
			+  "FROM innodb.Category c, innodb.Question que, innodb.QuestionCategory qc, Quiz q, QuizQuestion qq "
			+  "WHERE c.CategoryID = qc.CategoryID "
			+  "AND que.QuestionID = qc.QuestionID "
			+  "AND que.QuestionID = qq.QuestionID "
			+  "AND qq.QuizID = q.QuizID "
			+  "GROUP BY CategoryType ORDER BY count DESC) AS cat")
	public List<String> getTopCategories();
	
	/********************************** Question Flag Section **********************************************************/
	
	@SqlUpdate("UPDATE Question SET Flagged = 1, FlaggedReason = :reason WHERE QuestionID = :questionId")
	public void flagQuestion(@Bind("reason") String reason, @Bind("questionId") long questionId);
	
	@SqlUpdate("UPDATE Question SET Flagged = 0, FlaggedReason = 'None' WHERE QuestionID = :questionId")
	public void unflagQuestion(@Bind("questionId") long questionId);
	
	@SqlQuery("SELECT * FROM Question q WHERE Flagged = 1 AND GroupID = :groupId")
	public List<Question> findFlaggedQuestionsByGroup(@Bind("groupId") long groupId);
	
	@SqlQuery("SELECT * FROM Question q WHERE Flagged = 1 AND GroupID = :groupId AND CreatorID = :userId")
	public List<Question> findFlaggedQuestionsByUser(@Bind("groupId") long groupId, @Bind("userId") long userId);
	
	
	/********************************** Quiz Quality Ratings Query *****************************************************/
	
	@SqlUpdate("UPDATE Question SET Rating = Rating + :rating, RatingCount = RatingCount + 1 WHERE QuestionID = :questionId")
	public void rateQuestionQuality(@Bind("rating") int rating, @Bind("questionId") long questionId);
	
	@SqlUpdate("UPDATE Question SET Rating = Rating + :rating WHERE QuestionID = :questionId")
	public void updateQuestionQualityRating(@Bind("rating") int rating, @Bind("questionId") long questionId);
	
	@SqlQuery("SELECT UserRating FROM QuestionRating WHERE QuestionID = :questionId and UserID = :userId")
	public int getQuestionRating(@Bind("questionId") long questionId, @Bind("userId") long userId);
	
	@SqlUpdate("INSERT INTO QuestionRating (QuestionID, UserID, UserRating) VALUES(:questionId, :userId, :rating)")
	@GetGeneratedKeys
	public long insertQuestionRating(@Bind("questionId") long questionId, @Bind("userId") long userId, @Bind("rating") int rating);
	
	@SqlUpdate("UPDATE QuestionRating SET UserRating = :rating WHERE UserID = :userId AND QuestionID = :questionId")
	public void updateQuestionRating(@Bind("userId") long userId, @Bind("questionId") long questionId, @Bind("rating") int rating);
	
	/********************************* Quiz Difficulty Ratings Query *******************************************************/
	
	@SqlUpdate("UPDATE Question SET QuestionDifficulty = QuestionDifficulty + :rating, DifficultyCount = DifficultyCount + 1 WHERE QuestionID = :questionId")
	public void rateQuestionDifficulty(@Bind("rating") int rating, @Bind("questionId") long questionId);
	
	@SqlUpdate("UPDATE Question SET QuestionDifficulty = QuestionDifficulty + :rating WHERE QuestionID = :questionId")
	public void updateQuestionDifficultyRating(@Bind("rating") int rating, @Bind("questionId") long questionId);
	
	@SqlQuery("SELECT UserDifficulty FROM QuestionDifficulty WHERE QuestionID = :questionId and UserID = :userId")
	public int getQuestionDifficulty(@Bind("questionId") long questionId, @Bind("userId") long userId);
	
	@SqlUpdate("INSERT INTO QuestionDifficulty (QuestionID, UserID, UserDifficulty) VALUES(:questionId, :userId, :rating)")
	@GetGeneratedKeys
	public long insertQuestionDifficulty(@Bind("questionId") long questionId, @Bind("userId") long userId, @Bind("rating") int rating);
	
	@SqlUpdate("UPDATE QuestionDifficulty SET UserDifficulty = :rating WHERE UserID = :userId AND QuestionID = :questionId")
	public void updateQuestionDifficulty(@Bind("userId") long userId, @Bind("questionId") long questionId, @Bind("rating") int rating);
	
	/*********************************** CATEGORY SECTION *******************************************************/
	
	@SqlQuery("SELECT SUM(scores.Score) / COUNT(scores.Score) FROM (SELECT qs.Score AS Score FROM Category c, QuestionCategory qc, Question que, QuizQuestion qq, Quiz quiz, QuizScore qs "
			+ "WHERE c.CategoryType = :category "
			+ "AND c.CategoryID = qc.CategoryID "
			+ "AND qc.QuestionID = que.QuestionID "
			+ "AND que.QuestionID = qq.QuestionID "
			+ "AND qq.QuizID = quiz.QuizID "
			+ "AND qs.QuizID = quiz.QuizID "
			+ "AND qs.UserID = :userId "
			+ "GROUP BY (qs.QuizScoreID)) scores")
	public double getScoresByCategoryAndUser(@Bind("category") String category, @Bind("userId") long userId);
}
