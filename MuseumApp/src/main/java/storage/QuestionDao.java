package storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	            System.out.println("Statement= "+stmt);
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
}
