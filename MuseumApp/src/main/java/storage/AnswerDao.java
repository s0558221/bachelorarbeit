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

import bean.Answer;

public class AnswerDao {
	private static String GET_ALL_ANSWERS = "select * from Quiz_Antworten";
	private static String GET_ANSWER_BY_ID = "select * from Quiz_Antworten where id = ?";

	 private  DataSource ds;
	 
	 public AnswerDao () {      
	        try {
	            Context ctx = new InitialContext();
	            ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MuseumAppDB");
	        } catch (NamingException e) {
	            System.out.println("NamingException getting datasource");
	            e.printStackTrace();
	        } 
	    }
	 
	 public Answer getAnswerById(String id) {
		 Answer answer = new Answer();
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
	            stmt = con.prepareStatement(GET_ANSWER_BY_ID);
	            System.out.println("Statement= "+stmt);
	            stmt.setInt(1, i);
	            rs = stmt.executeQuery();
	            
	            while (rs.next()) {
	            	answer.setId(rs.getInt(1));
	            	answer.setText(rs.getString(2));
	            	answer.setId_frage(rs.getInt(3));
	            	answer.setIsCorrect(rs.getBoolean(4));
	            }
	            
	            return answer;
	        } catch (SQLException e) {
	            System.out.println("SQLException getting contact");
	            e.printStackTrace();
	            return answer;
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
	 
	 public Collection<Answer> getAllAnswers() {
			Collection<Answer> answers = new ArrayList<Answer>();

	        Connection con = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;

	        try {
	            con = ds.getConnection();
	            stmt = con.prepareStatement(GET_ALL_ANSWERS);
	            rs = stmt.executeQuery();
	            while (rs.next()) {
	            	Answer answer = new Answer();
	            	answer.setId(rs.getInt(1));
	            	answer.setText(rs.getString(2));
	            	answer.setId_frage(rs.getInt(3));
	            	answer.setIsCorrect(rs.getBoolean(4));
	            	answers.add(answer);
	            }
	            
	            return answers;
	        } catch (SQLException e) {
	            System.out.println("SQLException getting contact");
	            e.printStackTrace();
	            return answers;
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
