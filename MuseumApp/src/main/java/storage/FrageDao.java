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

import bean.Frage;

public class FrageDao {
	
	private static String GET_ALLE_FRAGEN = "select * from Quiz_Fragen";
	private static String GET_FRAGE_BY_ID = "select * from Quiz_Fragen where id = ?";
	private static String GET_FRAGE_BY_THEMA_UND_SCHWIERIGKEIT = "select * from Quiz_Fragen where Id_Themengebiet = ? AND Id_Schwierigkeit = ?";
	private static String ADD_FRAGE = "INSERT INTO Quiz_Fragen (Frage,Id_Schwierigkeit,Id_Themengebiet)VALUES(?,?,?)";
	private static String UPDATE_FRAGE = "UPDATE Quiz_Fragen SET Frage = ?,Id_Schwierigkeit = ?,Id_Themengebiet = ? WHERE Id = ?";
	
	private  DataSource ds;
	 
	 public FrageDao () {      
	        try {
	            Context ctx = new InitialContext();
	            ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MuseumAppDB");
	        } catch (NamingException e) {
	            System.out.println("NamingException getting datasource");
	            e.printStackTrace();
	        } 
	    }
	 
	 public Frage getFrageById(String id) {
			Frage frage = new Frage();
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
	            stmt = con.prepareStatement(GET_FRAGE_BY_ID);
	            stmt.setInt(1, i);
	            rs = stmt.executeQuery();
	            
	            while (rs.next()) {
	            	frage.setId(rs.getInt(1));
	            	frage.setText(rs.getString(2));
	            	frage.setSchwierigkeit(rs.getInt(3));
	            	frage.setThema(rs.getInt(4));
	            }
	            
	            return frage;
	        } catch (SQLException e) {
	            System.out.println("SQLException getting frage");
	            e.printStackTrace();
	            return frage;
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
	 
	 public Collection<Frage> getFragenByTopicIdAndDifficultyId(String thema, String schwierigkeit) {
		 Collection<Frage> fragen = new ArrayList<Frage>();
			
			int tid = -1;
			if(!thema.equals("") )
			tid = Integer.parseInt(thema);
			
			int did = -1;
			if(!schwierigkeit.equals("") )
			did = Integer.parseInt(schwierigkeit);
			
			Connection con = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;
	        if(tid!=-1&&did!=-1)
	        {
	        try {
	            con = ds.getConnection();
	            stmt = con.prepareStatement(GET_FRAGE_BY_THEMA_UND_SCHWIERIGKEIT);
	            stmt.setInt(1, tid);
	            stmt.setInt(2, did);
	            rs = stmt.executeQuery();
	            
	            while (rs.next()) {
	            	Frage frage = new Frage();
	            	frage.setId(rs.getInt(1));
	            	frage.setText(rs.getString(2));
	            	frage.setSchwierigkeit(rs.getInt(3));
	            	frage.setThema(rs.getInt(4));
	            	fragen.add(frage);
	            }
	            
	            return fragen;
	        } catch (SQLException e) {
	            System.out.println("SQLException getting fragen");
	            e.printStackTrace();
	            return fragen;
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
	 
	 public Collection<Frage> getAlleFragen() {
			Collection<Frage> fragen = new ArrayList<Frage>();

	        Connection con = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;

	        try {
	            con = ds.getConnection();
	            stmt = con.prepareStatement(GET_ALLE_FRAGEN);
	            rs = stmt.executeQuery();
	            while (rs.next()) {
	            	Frage frage = new Frage();
	            	frage.setId(rs.getInt(1));
	            	frage.setText(rs.getString(2));
	            	frage.setSchwierigkeit(rs.getInt(3));
	            	frage.setThema(rs.getInt(4));
	            	fragen.add(frage);
	            }
	            
	            return fragen;
	        } catch (SQLException e) {
	            System.out.println("SQLException beim fragen erhalten");
	            e.printStackTrace();
	            return fragen;
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
	 
	 public int addFrage(Frage q) {
		 	Connection con = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;
	        int id = 0;
	        try {
	            con = ds.getConnection();
	            stmt = con.prepareStatement(ADD_FRAGE,Statement.RETURN_GENERATED_KEYS);
	            stmt.setString(1, q.getText());
	            stmt.setInt(2, q.getSchwierigkeit());
	            stmt.setInt(3, q.getThema());
	            stmt.executeUpdate();
	            rs = stmt.getGeneratedKeys();
                if(rs.next())
                {
                    id = rs.getInt(1);
                }
	        }catch (SQLException e) {
	            System.out.println("SQLException beim hinzufügen von frage");
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
	 
	 public int updateFrage(Frage q) {
		 	Connection con = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;
	        int id = 0;
	        try {
	            con = ds.getConnection();
	            stmt = con.prepareStatement(UPDATE_FRAGE,Statement.RETURN_GENERATED_KEYS);
	            stmt.setString(1, q.getText());
	            stmt.setInt(2, q.getSchwierigkeit());
	            stmt.setInt(3, q.getThema());
	            stmt.setInt(4, q.getId());
	            stmt.executeUpdate();
	            rs = stmt.getGeneratedKeys();
                if(rs.next())
                {
                    id = rs.getInt(1);
                }
	        }catch (SQLException e) {
	            System.out.println("SQLException beim Updaten der Frage");
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
