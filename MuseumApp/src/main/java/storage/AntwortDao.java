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

import bean.Antwort;

public class AntwortDao {
	private static String GET_ALLE_ANTWORTEN = "select * from Quiz_Antworten";
	private static String GET_ANTWORT_BY_FRAGE_ID = "select * from Quiz_Antworten where Id_Frage = ?";
	private static String ADD_ANTWORT = "INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig)VALUES(?,?,?)";
	private static String UPDATE_ANTWORT = "UPDATE Quiz_Antworten SET Antwort = ?,Id_Frage = ?,IstRichtig = ? WHERE Id = ?";
	
	 private  DataSource ds;
	 
	 public AntwortDao () {      
	        try {
	            Context ctx = new InitialContext();
	            ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MuseumAppDB");
	        } catch (NamingException e) {
	            System.out.println("NamingException getting datasource");
	            e.printStackTrace();
	        } 
	    }
	 
	 public  Collection<Antwort> getAntwortByFrageId(int id) {
		 Collection<Antwort> antworten = new ArrayList<Antwort>();

	        Connection con = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;

	        try {
	            con = ds.getConnection();
	            stmt = con.prepareStatement(GET_ANTWORT_BY_FRAGE_ID);
	            stmt.setInt(1, id);
	            rs = stmt.executeQuery();
	            while (rs.next()) {
	            	Antwort antwort = new Antwort();
	            	antwort.setId(rs.getInt(1));
	            	antwort.setText(rs.getString(2));
	            	antwort.setId_frage(rs.getInt(3));
	            	antwort.setIstKorrekt(rs.getBoolean(4));
	            	antworten.add(antwort);
	            }
	            
	            return antworten;
	        } catch (SQLException e) {
	            System.out.println("SQLException getting antwort");
	            e.printStackTrace();
	            return antworten;
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
	 
	 public Collection<Antwort> getAlleAntworten() {
			Collection<Antwort> antworten = new ArrayList<Antwort>();

	        Connection con = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;

	        try {
	            con = ds.getConnection();
	            stmt = con.prepareStatement(GET_ALLE_ANTWORTEN);
	            rs = stmt.executeQuery();
	            while (rs.next()) {
	            	Antwort antwort = new Antwort();
	            	antwort.setId(rs.getInt(1));
	            	antwort.setText(rs.getString(2));
	            	antwort.setId_frage(rs.getInt(3));
	            	antwort.setIstKorrekt(rs.getBoolean(4));
	            	antworten.add(antwort);
	            }
	            
	            return antworten;
	        } catch (SQLException e) {
	            System.out.println("SQLException getting antworten");
	            e.printStackTrace();
	            return antworten;
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
	 
	 public void addAntwort(Antwort a) {
		 	Connection con = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;
	        try {
	            con = ds.getConnection();
	            stmt = con.prepareStatement(ADD_ANTWORT);
	            stmt.setString(1, a.getText());
	            stmt.setInt(2, a.getId_frage());
	            stmt.setBoolean(3, a.getIstKorrekt());
	            stmt.executeUpdate();
	        }catch (SQLException e) {
	            System.out.println("SQLException posting antwort");
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
	 
	 public void updateAntwort(Antwort a) {
		 	Connection con = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;
	        try {
	            con = ds.getConnection();
	            stmt = con.prepareStatement(UPDATE_ANTWORT);
	            stmt.setString(1,a.getText());
	            stmt.setInt(2, a.getId_frage());
	            stmt.setBoolean(3, a.getIstKorrekt());
	            stmt.setInt(4, a.getId());
	            stmt.executeUpdate();
	        }catch (SQLException e) {
	            System.out.println("SQLException updating antwort");
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
