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

/**
 * Die Klasse ThemaDao kommuniziert mit der Datenbank-Tabelle Quiz_Lookup_Themengebiete. Sie ist fuer das Laden und Speichern des jeweiligen Datensaetze in und aus der Datenbank zustaendig.
 * @author Roy Beyer
 * @version 1.0
 */
public class ThemaDao {
	
	/**
	 * der vorbereitete SQL-Befehl zum Abruf aller Datensaetze aus der Datenbank
	 */
	private static String GET_ALLE_THEMEN = "select * from Quiz_Lookup_Themengebiete";
	
	/**
	 * der vorbereitete SQL-Befehl zum Abruf eines bestimmten Datensatz aus der Datenbank anhand einer Themen-Id
	 */
	private static String GET_THEMA_BY_ID = "select * from Quiz_Lookup_Themengebiete where id = ?";
	
	/**
	 * der vorbereitete SQL-Befehl zum Hinzufuegen neuer Datensaetze in die Datenbank
	 */
	private static String ADD_THEMA = "INSERT INTO Quiz_Lookup_Themengebiete (Themengebiet) VALUES (?)";

	/**
	 * die Datenquelle fuer die Verbindung mit der Datenbankbank
	 */
	 private  DataSource ds;
	 
	 /**
	  * der Standard-Konstruktor stellt beim Instanziieren die Verbindung zur Datenbank her
	  */
	 public ThemaDao () {      
	        try {
	            Context ctx = new InitialContext();
	            ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MuseumAppDB");
	        } catch (NamingException e) {
	            System.out.println("NamingException getting datasource");
	            e.printStackTrace();
	        } 
	    }
	 
	 /**
	  * holt das Themengebiet anhand der uebergebenen Id aus der Datenbank
	  * @param id die vom Webservice uebergebene Id des Themengebiets
	  * @return das abgerufene Thema
	  */
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
	            System.out.println("SQLException beim Abrufen eines Themas.");
	            e.printStackTrace();
	            return thema;
	        } finally {
	            try {
	                if (rs != null) rs.close();
	                if (stmt != null) stmt.close();
	                if (con != null) con.close();
	            } catch (Exception e) {
	                System.out.println("Exception beim Schließen der Datenbank-Verbindung.");
	            } 
	        }
	        }
	        else
	        	return null;
		}
	 
	 /**
	  * ruft alle Themengebiete aus der Datenbank ab
	  * @return die abgerufenen Themengebiete
	  */
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
	            System.out.println("SQLException beim Abruf aller Themen.");
	            e.printStackTrace();
	            return topics;
	        } finally {
	            try {
	                if (rs != null) rs.close();
	                if (stmt != null) stmt.close();
	                if (con != null) con.close();
	            } catch (Exception e) {
	                System.out.println("Exception beim Schließen der Datenbank-Verbindung.");
	            } 
	        }
		}
	 
	 /**
	  * fuegt der Datenbank ein neues Themengebiet hinzu
	  * @param t das vom Webservice uebergebene Themengebiet
	  */
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
	            System.out.println("SQLException beim Hinzufüegen neuer Themen");
	            e.printStackTrace();
	        } finally {
	            try {
	                if (rs != null) rs.close();
	                if (stmt != null) stmt.close();
	                if (con != null) con.close();
	            } catch (Exception e) {
	                System.out.println("Exception beim Schließen der SQL-Verbindung.");
	            } 
	        }
		}

}
