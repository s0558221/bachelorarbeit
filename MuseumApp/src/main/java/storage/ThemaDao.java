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

import bean.Thema;

public class ThemaDao {
	private static String GET_ALLE_THEMEN = "select * from Quiz_Lookup_Themengebiete";
	private static String GET_THEMA_BY_ID = "select * from Quiz_Lookup_Themengebiete where id = ?";
	private static String ADD_THEMA = "INSERT INTO Quiz_Lookup_Themengebiete (Themengebiet) VALUES (?)";

	 private  DataSource ds;
	 
	 public ThemaDao () {      
	        try {
	            Context ctx = new InitialContext();
	            ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MuseumAppDB");
	        } catch (NamingException e) {
	            System.out.println("NamingException getting datasource");
	            e.printStackTrace();
	        } 
	    }
	 
	 public Thema getThemaById(String id) {
		 Thema thema = new Thema();
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
	            stmt = con.prepareStatement(GET_THEMA_BY_ID);
	            System.out.println("Statement= "+stmt);
	            stmt.setInt(1, i);
	            rs = stmt.executeQuery();
	            
	            while (rs.next()) {
	            	thema.setId(rs.getInt(1));
	            	thema.setText(rs.getString(2));
	            }
	            
	            return thema;
	        } catch (SQLException e) {
	            System.out.println("SQLException getting contact");
	            e.printStackTrace();
	            return thema;
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
	 
	 public Collection<Thema> getAlleThemen() {
			Collection<Thema> topics = new ArrayList<Thema>();

	        Connection con = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;

	        try {
	            con = ds.getConnection();
	            stmt = con.prepareStatement(GET_ALLE_THEMEN);
	            rs = stmt.executeQuery();
	            while (rs.next()) {
	            	Thema thema = new Thema();
	            	thema.setId(rs.getInt(1));
	            	thema.setText(rs.getString(2));
	            	topics.add(thema);
	            }
	            
	            return topics;
	        } catch (SQLException e) {
	            System.out.println("SQLException getting themen");
	            e.printStackTrace();
	            return topics;
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
	 
	 public void addThema(Thema t) {
		 	Connection con = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;

	        try {
	            con = ds.getConnection();
	            stmt = con.prepareStatement(ADD_THEMA);
	            stmt.setString(1, t.getText());
	            stmt.executeUpdate();
	            
	        }catch (SQLException e) {
	            System.out.println("SQLException getting contact");
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
		}

}
