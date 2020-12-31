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

import bean.Difficulty;

public class DifficultyDao {
	private static String GET_ALL_DIFFICULTIES = "select * from Quiz_Lookup_Schwierigkeiten";
	private static String GET_DIFFICULTY_BY_ID = "select * from Quiz_Lookup_Schwierigkeiten where id = ?";

	 private  DataSource ds;
	 
	 public DifficultyDao () {      
	        try {
	            Context ctx = new InitialContext();
	            ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MuseumAppDB");
	        } catch (NamingException e) {
	            System.out.println("NamingException getting datasource");
	            e.printStackTrace();
	        } 
	    }
	 
	 public Difficulty getDifficultyById(String id) {
		 Difficulty difficulty = new Difficulty();
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
	            stmt = con.prepareStatement(GET_DIFFICULTY_BY_ID);
	            System.out.println("Statement= "+stmt);
	            stmt.setInt(1, i);
	            rs = stmt.executeQuery();
	            
	            while (rs.next()) {
	            	difficulty.setId(rs.getInt(1));
	            	difficulty.setText(rs.getString(2));
	            }
	            
	            return difficulty;
	        } catch (SQLException e) {
	            System.out.println("SQLException getting difficulties");
	            e.printStackTrace();
	            return difficulty;
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
	 
	 public Collection<Difficulty> getAllDifficulties() {
			Collection<Difficulty> difficulties = new ArrayList<Difficulty>();

	        Connection con = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;

	        try {
	            con = ds.getConnection();
	            stmt = con.prepareStatement(GET_ALL_DIFFICULTIES);
	            rs = stmt.executeQuery();
	            while (rs.next()) {
	            	Difficulty difficulty = new Difficulty();
	            	difficulty.setId(rs.getInt(1));
	            	difficulty.setText(rs.getString(2));
	            	difficulties.add(difficulty);
	            }
	            
	            return difficulties;
	        } catch (SQLException e) {
	            System.out.println("SQLException getting difficulties");
	            e.printStackTrace();
	            return difficulties;
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
