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
			+ "AND Qz.QuizID = :quizId")
	public List<Question> retrieveExistingQuiz(@Bind("quizId") long quizId);
	
	/*
	 * 	CreateAQuestion. Will add a new question to the database.
	 */
	@SqlUpdate("INSERT INTO Question "
				+ "(CreatorID, QuestionDifficulty, Rating, "
				+ "QuestionText, CorrectAnswer, QuestionType, WrongAnswer1, WrongAnswer2, WrongAnswer3, WrongAnswer4, Flagged) "
				+ "VALUES (:creatorId, :difficulty, :rating, :questionText, :correctAnswer, :questionType, :wrongAnswer1, :wrongAnswer2, "
				+ ":wrongAnswer3, :wrongAnswer4, :flagged)")
	@GetGeneratedKeys
	public long createQuestion(@Bind("creatorId") long creatorId, 
							   @Bind("difficulty") float difficulty, 
							   @Bind("rating") float rating,
							   @Bind("questionText") String questionText,
							   @Bind("correctAnswer") String correctAnswer,
							   @Bind("questionType") Question.QuestionType questionType,
							   @Bind("wrongAnswer1") String wrongAnswer1,
							   @Bind("wrongAsnwer2") String wrongAnswer2,
							   @Bind("wrongAnswer3") String wrongAnswer3,
							   @Bind("wrongAnswer4") String wrongAnswer4);
	
	/*
	 * 	retrieveQuiz - Retrieves questions from a specific group id.
	 */
	@SqlQuery("SELECT q.* FROM Question q, UserGroups u WHERE u.UserID = q.UserID AND u.GroupID = :groupId")
	public List<Question> getQuestionsByGroupId(@Bind("groupId") long groupId);
}

	/* TODO NEED TO CONVERT THIS QUERY
	public static ArrayList<Question> retrieveQuestions(int[] tagIds, int maxQuestions)
	{
		
		ArrayList<Question> questions = new ArrayList<Question>();
		
		int taglength = tagIds.length -1;
		
		// TODO: Do we want to return an empty array of questions on tags = 0?
		if(tagIds.length == 0)
			return questions;
		
		
		Connection connection = null;
		PreparedStatement statement = null;
		
		// TODO: Check to see if this behemoth query actually works.
		// Append all tag ids to the select statement.
		//for(int index : tagIds)
		//{
		//	if(index == tagIds.length - 1)
		//		select += index + ") ";
		//	else
		//		select += index + ",";
		//}
		
		// Retrieve all questions with tag ids.
		String select = "SELECT Q "
				+ "FROM Question Q, TagQuestion TQ, Tags T "
				+ "WHERE Q.QuestionID = TQ.QuestionID "
				+ "AND TQ.TagID = T.TagID "
				+ "AND TagID = (";
		for(int x = 0; x <= taglength; x++)
		{
			if(x == taglength)
			{
				select = select + "?)";
			}
			else
			{
				select = select + "?, ";
			}
		}
		
		//Connection connection = null;
		//Statement statement = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			connection.setAutoCommit(false);
			
			//statement = connection.createStatement();
			statement = connection.prepareStatement(select);
			for(int x = 0; x < taglength; x++)
			{
				statement.setInt(x+1, tagIds[0]);
			}
			
			ResultSet results = statement.executeQuery();
			//ResultSet results = statement.executeQuery(select);
			
			int x = 0;
			while(results.next()) {
				if(x == maxQuestions)
				{
					break;
				}
				int qid = results.getInt("QuestionID");
				int cid = results.getInt("CreatorID");
				int diff = results.getInt("QuestionDifficulty");
				int rate = results.getInt("Rating");
				String qText = results.getString("QuestionText");
				String answer = results.getString("CorrectAnswer");
				String qType = results.getString("QuestionType");
				String wrongA1 = results.getString("WrongAnswer1");
				String wrongA2 = results.getString("WrongAnswer2");
				String wrongA3 = results.getString("WrongAnswer3");
				String wrongA4 = results.getString("WrongAnswer4");
				Boolean flag = results.getBoolean("Flagged");
				
				ArrayList<String> wrongAnswers = new ArrayList<String>();
				if(wrongA1 != null && wrongA1.length() > 0)
					wrongAnswers.add(wrongA1);
				if(wrongA2 != null && wrongA2.length() > 0)
					wrongAnswers.add(wrongA2);
				if(wrongA3 != null && wrongA3.length() > 0)
					wrongAnswers.add(wrongA3);
				if(wrongA4 != null && wrongA4.length() > 0)
					wrongAnswers.add(wrongA4);
				questions.add(new Question(qid, cid, diff, rate, qType, qText, answer, 
						wrongAnswers, flag));
				x++;
			}
			results.close();
			statement.close();
			connection.close();
		}
		catch(Exception e) {
			logger.error("Could not retrieve questions.");
			e.printStackTrace();
		}	
		
		return questions;
	} */