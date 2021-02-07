package storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.Question;

public class QuestionDao {
	
	private static String GET_ALL_QUESTIONS = "select * from Quiz_Fragen";
	private static String GET_QUESTION_BY_ID = "select * from Quiz_Fragen where id = ?";
	private static String GET_QUESTION_BY_TOPICID_AND_DIFFICULTYID = "select * from Quiz_Fragen where Id_Themengebiet = ? AND Id_Schwierigkeit = ?";
	private static String ADD_QUESTION = "INSERT INTO Quiz_Fragen (Frage,Id_Schwierigkeit,Id_Themengebiet)VALUES(?,?,?) RETURNING id";
	private static String UPDATE_QUESTION = "UPDATE Quiz_Fragen SET Frage = ?,Id_Schwierigkeit = ?,Id_Themengebiet = ? WHERE Id = ? RETURNING id";
	
	private  DataSource ds;
	 
	 public QuestionDao () {      
	        try {
	            Context ctx = new InitialContext();
	            ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MuseumAppDB");
	        } catch (NamingException e) {
	            System.out.println("NamingException getting datasource");
	            e.printStackTrace();
	        } 
	    }
	 
	 public Question getQuestionById(String id) {
			Question question = new Question();
			int i = -1;
			if(!id.equals("") )
			i = Integer.parseInt(id);
			
			Connection con = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;
	        if(i!=-1)
	        {
	        try {
	            con = ds.getConnection();
	            stmt = con.prepareStatement(GET_QUESTION_BY_ID);
	            stmt.setInt(1, i);
	            rs = stmt.executeQuery();
	            
	            while (rs.next()) {
	            	question.setId(rs.getInt(1));
	            	question.setText(rs.getString(2));
	            	question.setDifficulty(rs.getInt(3));
	            	question.setTopic(rs.getInt(4));
	            }
	            
	            return question;
	        } catch (SQLException e) {
	            System.out.println("SQLException getting contact");
	            e.printStackTrace();
	            return question;
	        } finally {
	            try {
	                if (rs != null) rs.close();
	                if (stmt != null) stmt.close();
	                if (con != null) con.close();
	            } catch (Exception e) {
	                System.out.println("Exception in closing DB resources");
	            } 
	        }
	        }
	        else
	        	return null;
		}
	 
	 public Collection<Question> getQuestionsByTopicIdAndDifficultyId(String topicid, String difficultyid) {
		 Collection<Question> questions = new ArrayList<Question>();
			
			int tid = -1;
			if(!topicid.equals("") )
			tid = Integer.parseInt(topicid);
			
			int did = -1;
			if(!difficultyid.equals("") )
			did = Integer.parseInt(difficultyid);
			
			Connection con = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;
	        if(tid!=-1&&did!=-1)
	        {
	        try {
	            con = ds.getConnection();
	            stmt = con.prepareStatement(GET_QUESTION_BY_TOPICID_AND_DIFFICULTYID);
	            stmt.setInt(1, tid);
	            stmt.setInt(2, did);
	            rs = stmt.executeQuery();
	            
	            while (rs.next()) {
	            	Question question = new Question();
	            	question.setId(rs.getInt(1));
	            	question.setText(rs.getString(2));
	            	question.setDifficulty(rs.getInt(3));
	            	question.setTopic(rs.getInt(4));
	            	questions.add(question);
	            }
	            
	            return questions;
	        } catch (SQLException e) {
	            System.out.println("SQLException getting contact");
	            e.printStackTrace();
	            return questions;
	        } finally {
	            try {
	                if (rs != null) rs.close();
	                if (stmt != null) stmt.close();
	                if (con != null) con.close();
	            } catch (Exception e) {
	                System.out.println("Exception in closing DB resources");
	            } 
	        }
	        }
	        else
	        	return null;
		}
	 
	 public Collection<Question> getAllQuestions() {
			Collection<Question> questions = new ArrayList<Question>();

	        Connection con = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;

	        try {
	            con = ds.getConnection();
	            stmt = con.prepareStatement(GET_ALL_QUESTIONS);
	            rs = stmt.executeQuery();
	            while (rs.next()) {
	            	Question question = new Question();
	            	question.setId(rs.getInt(1));
	            	question.setText(rs.getString(2));
	            	question.setDifficulty(rs.getInt(3));
	            	question.setTopic(rs.getInt(4));
	            	questions.add(question);
	            }
	            
	            return questions;
	        } catch (SQLException e) {
	            System.out.println("SQLException getting contact");
	            e.printStackTrace();
	            return questions;
	        } finally {
	            try {
	                if (rs != null) rs.close();
	                if (stmt != null) stmt.close();
	                if (con != null) con.close();
	            } catch (Exception e) {
	                System.out.println("Exception in closing DB resources");
	            } 
	        }
		}
	 
	 public int addQuestion(Question q) {
		 	Connection con = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;
	        int id = 0;
	        try {
	            con = ds.getConnection();
	            stmt = con.prepareStatement(ADD_QUESTION, Statement.RETURN_GENERATED_KEYS);
	            stmt.setString(1, q.getText());
	            stmt.setInt(2, q.getDifficulty());
	            stmt.setInt(3, q.getTopic());
	            stmt.executeUpdate();
	            
	            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                	id = (int) generatedKeys.getLong(1);
	                }
	                else {
	                    throw new SQLException("Creating question failed, no ID obtained.");
	                }
	            }
	        }catch (SQLException e) {
	            System.out.println("SQLException getting question");
	            e.printStackTrace();
	        } finally {
	            try {
	                if (rs != null) rs.close();
	                if (stmt != null) stmt.close();
	                if (con != null) con.close();
	            } catch (Exception e) {
	                System.out.println("Exception in closing DB resources");
	            } 
	        }
			return id;
		}
	 
	 public int updateQuestion(Question q) {
		 	Connection con = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;
	        int id = 0;
	        try {
	            con = ds.getConnection();
	            stmt = con.prepareStatement(UPDATE_QUESTION, Statement.RETURN_GENERATED_KEYS);
	            stmt.setString(1, q.getText());
	            stmt.setInt(2, q.getDifficulty());
	            stmt.setInt(3, q.getTopic());
	            stmt.setInt(4, q.getId());
	            stmt.executeUpdate();
	            
	            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                	id = (int) generatedKeys.getLong(1);
	                }
	                else {
	                    throw new SQLException("Creating question failed, no ID obtained.");
	                }
	            }
	        }catch (SQLException e) {
	            System.out.println("SQLException getting question");
	            e.printStackTrace();
	        } finally {
	            try {
	                if (rs != null) rs.close();
	                if (stmt != null) stmt.close();
	                if (con != null) con.close();
	            } catch (Exception e) {
	                System.out.println("Exception in closing DB resources");
	            } 
	        }
			return id;
		}
}
