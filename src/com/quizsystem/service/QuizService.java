package com.quizsystem.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.quizsystem.entity.Answer;
import com.quizsystem.entity.Question;
import com.quizsystem.entity.Quiz;

public class QuizService extends BaseService{
	
	public List<Quiz> getQuizList(int id) throws SQLException{
		List<Quiz> qList = new ArrayList<Quiz>();
		String q = "select * from quiz where professorid=?";
		PreparedStatement ps = con.prepareStatement(q);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			qList.add(new Quiz(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getInt(6) ));
		}
		return qList;
		
	}

	public void addQuiz(Quiz q) throws SQLException {
		String query ="select max(id) from quiz ";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		int id=0;
		if(rs.next()){
			id=rs.getInt(1);
		}
		
		query = "insert into quiz values (?,?,?,?,?,?)";
		ps = con.prepareStatement(query);
		ps.setInt(1, id+1);
		ps.setString(2, q.getName());
		ps.setInt(3, q.getnOfQues()); 
		ps.setInt(4, q.getDuration());
		ps.setString(5, q.getShowAns());
		ps.setInt(6, q.getProfId());	
		ps.execute();
	}

	public Quiz getQuizTuple(int quizId) throws SQLException {
		Quiz q = null;
		String query = "select * from quiz where id=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, quizId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			q = new Quiz(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getInt(6));
		}
		return q;
	}

	public void updateQuiz(Quiz quiz) throws SQLException {
		String query = "update quiz set name=?, numquestions=?, duration=?, showanswers=? where id=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1,quiz.getName());
		ps.setInt(2, quiz.getnOfQues());
		ps.setInt(3, quiz.getDuration());
		ps.setString(4, quiz.getShowAns());
		ps.setInt(5, quiz.getId());
		ps.executeUpdate();
		
	}
	
	public int addQuestion(int qId, String description) throws SQLException {
		//getting max(id)
		String query="select max(id) from question";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		int id = 0;
		if(rs.next()){
			id = rs.getInt(1);
		}
		
		// inserting Question into db
		query = "insert into question values (?,?,?,null)";
		ps = con.prepareStatement(query);
		ps.setInt(1, id+1);
		ps.setInt(2, qId);
		ps.setString(3, description);
		ps.execute();
		
		return id+1;
		
	}
	
	public List<Question> getQuestionList(int quizId) throws SQLException {
		//getting Question list from db for quizId
		List<Question> quesList = new ArrayList<Question>();
		String query = "select * from question where quizid=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, quizId);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			query = "select * from answer where questionid=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, rs.getInt(1));
			ResultSet rs1 = ps.executeQuery();
			List<Answer> answerList = new ArrayList<Answer>();
			while(rs1.next()){
				answerList.add(new Answer(rs1.getInt(1), rs1.getInt(2), rs1.getString(3), rs1.getString(4)));
			}
			quesList.add(new Question(rs.getInt(1), rs.getInt(2), rs.getString(3), answerList));
		}
		return quesList;
	}

	public Question getQuestionDetails(int questionId) throws SQLException {
		String query = "select * from question where id=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, questionId);
		ResultSet rs = ps.executeQuery();
		Question q = null;
		if(rs.next()){
			query = "select * from answer where questionid=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, questionId);
			ResultSet rs1 = ps.executeQuery();
			List<Answer> ansList = new ArrayList<>();
			while(rs1.next()){
				ansList.add(new Answer(rs1.getInt(1), rs1.getInt(2), rs1.getString(3), rs1.getString(4)));
			}
			q = new Question(rs.getInt(1), rs.getInt(2), rs.getString(3), ansList);
		}
		return q;
	}

	public void updateQuestion(Question q) throws SQLException {
		String query = "update question set description=? where id=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, q.getDescription());
		ps.setInt(2, q.getId());
		ps.executeUpdate();
		
	}

	public void deleteQuestion(int id) throws SQLException {
		String query = "delete from question where id=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, id);
		ps.execute();
		
	}

	public List<Integer> getAnsIds(int questionId) throws SQLException {
		String query = "select id from answer where questionid=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, questionId);
		ResultSet rs = ps.executeQuery();
		List<Integer> ansIds = new ArrayList<>();
		while(rs.next()){
			ansIds.add(rs.getInt(1));
		}
		return ansIds;
	}

	public void updateAnswer(Integer i, String answerDescription, String correctAnswer) throws SQLException {
		String query = "update answer set answer=?, correctness=? where id=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, answerDescription);
		ps.setString(2, correctAnswer);
		ps.setInt(3, i);
		ps.executeUpdate();
		
	}

	public void addAnswer(int questionId, String newAnswer) throws SQLException {
		//getting max id
		String query = "select max(id) from Answer";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		int id=0;
		if(rs.next()){
			id=rs.getInt(1);
		}
		
		//adding answer
		query ="insert into answer values (?,?,?,'N')";
		ps = con.prepareStatement(query);
		ps.setInt(1, id+1);
		ps.setInt(2, questionId);
		ps.setString(3, newAnswer);
		ps.execute();
		
	}

	public List<Quiz> getQuizList() throws SQLException {
		String query = "select * from quiz";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		List<Quiz> quizList = new ArrayList<>();
		while(rs.next()){
			quizList.add(new Quiz(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getInt(6) ));
		}
		return quizList;
	}

	public List<Answer> getAnswerList(int questionId) throws SQLException {
		String query = "select * from answer where questionid=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, questionId);
		ResultSet rs = ps.executeQuery();
		List<Answer> ansList = new ArrayList<>();
		while(rs.next()){
			ansList.add(new Answer(rs.getInt(1), rs.getInt(2), rs.getString(3), "N"));
		}
		return ansList;
	}
	
	public List<Integer> getCorrectAnswers(int questionId) throws SQLException {
		String query = "select id  from answer where questionid=? and correctness='Y'";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, questionId);
		ResultSet rs = ps.executeQuery();
		List<Integer> corrAns = new ArrayList<>();
		while(rs.next()){
			corrAns.add(rs.getInt(1));
		}
		return corrAns;
	}

	public String getAnswerDesc(Integer i) throws SQLException {
		String query = "select answer from answer where id=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, i);
		ResultSet rs = ps.executeQuery();
		String ansDesc = null;
		if(rs.next()){
			ansDesc = rs.getString(1);
		}
		return ansDesc;
	}

	public void saveResult(int quizId, int studentid, int score) throws SQLException {
		String query = "select max(id) from results";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		int id = 0;
		if(rs.next()){
			id = rs.getInt(1);
		}
		
		query = "insert into results values (?,?,?,?)";
		ps = con.prepareStatement(query);
		ps.setInt(1, id+1);
		ps.setInt(2, quizId);
		ps.setInt(3, studentid);
		ps.setInt(4, score);
		ps.execute();		
		
	}

	public Integer getQuizAttempt(int id, int studentId) throws SQLException {
		String query = "select score from results where quizid=? and studentid=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, id);
		ps.setInt(2, studentId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			// that means the quiz is attempted
			return rs.getInt(1);
		}
		return null;
	}

	
	
	/*public void addAns(String answer, int questionId) throws SQLException {
		//getting max id
		String query = "select max(id) from Answer";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		int id=0;
		if(rs.next()){
			id=rs.getInt(1);
		}
		
		//adding answer
		query ="insert into answer values (?,?,?)";
		ps = con.prepareStatement(query);
		ps.setInt(1, id+1);
		ps.setInt(2, questionId);
		ps.setString(3, answer);
		
	}

	public List<Answer> getAnswerList(int questionId) throws SQLException {
		String query = "select * from answer where questionid=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, questionId);
		ResultSet rs = ps.executeQuery();
		List<Answer> ansList = new ArrayList<>();
		while(rs.next()){
			ansList.add(new Answer(rs.getInt(1), rs.getInt(2), rs.getString(3)));
		}
		
		return ansList;
	}*/

}
