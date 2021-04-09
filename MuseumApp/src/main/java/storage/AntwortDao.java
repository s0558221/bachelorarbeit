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

/**
 * Die Klasse AntwortDao kommuniziert mit der Datenbank-Tabelle Quiz_Antworten. Sie ist fuer das Laden und Speichern des jeweiligen Datensaetze in und aus der Datenbank zustaendig.
 * @author Roy Beyer
 * @version 1.0
 */
public class AntwortDao {
	
	/**
	 * der vorbereitete SQL-Befehl, der fuer den Abruf aller Datensaetze aus der Datenbank genutzt wird
	 */
	private static String GET_ALLE_ANTWORTEN = "select * from Quiz_Antworten";
	
	/**
	 * der vorbereitete SQL-Befehl, der fuer den Abruf aller Datensaetze aus der Datenbank, die zu einer bestimmten Frage gehoeren, genutzt wird
	 */
	private static String GET_ANTWORT_BY_FRAGE_ID = "select * from Quiz_Antworten where Id_Frage = ?";
	
	/**
	 * der vorbereitete SQL-Befehl, der eine neue Antwort in die Datenbank eintraegt
	 */
	private static String ADD_ANTWORT = "INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig)VALUES(?,?,?)";
	
	/**
	 * der vorbereitete SQL-Befehl, der eine vorhandene Antwort in der Datenbank aktualisiert
	 */
	private static String UPDATE_ANTWORT = "UPDATE Quiz_Antworten SET Antwort = ?,Id_Frage = ?,IstRichtig = ? WHERE Id = ?";
	
	/**
	 * die Datenquelle fuer die Verbindung mit der Datenbankbank
	 */
	 private  DataSource ds;
	 
	 /**
	  * der Standard-Konstruktor stellt beim Instanziieren die Verbindung zur Datenbank her
	  */
	 public AntwortDao () {      
	        try {
	            Context ctx = new InitialContext();
	            ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MuseumAppDB");
	        } catch (NamingException e) {
	            System.out.println("NamingException getting datasource");
	            e.printStackTrace();
	        } 
	    }
	 
	 /**
	  * liefert alle Antworten zu einer Frage anhand der Id der Frage aus der Datenbank an den Webservice zurueck
	  * @param id die Id der Frage
	  * @return alle geladenene Antworten
	  */
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
	            System.out.println("SQLException beim Abfragen der Antwort");
	            e.printStackTrace();
	            return antworten;
	        } finally {
	            try {
	                if (rs != null) rs.close();
	                if (stmt != null) stmt.close();
	                if (con != null) con.close();
	            } catch (Exception e) {
	                System.out.println("Exception beim Schließen der Datenbank-Verbindung");
	            } 
	        }
		}
	 
	 /**
	  * liefert alle Antworten aus der Datenbank an den Webservice zurueck
	  * @return alle Antworten
	  */
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
	            System.out.println("SQLException beim Abfragen aller Antworten");
	            e.printStackTrace();
	            return antworten;
	        } finally {
	            try {
	                if (rs != null) rs.close();
	                if (stmt != null) stmt.close();
	                if (con != null) con.close();
	            } catch (Exception e) {
	                System.out.println("Exception beim Schließen der Datenbank-Verbindung");
	            } 
	        }
		}
	 
	 /**
	  * legt eine neue Antwort in der Datenbank ab
	  * @param a die vom Webservice uebergebene Antwort
	  */
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
	            System.out.println("SQLException beim Antwort hinzufuegen");
	            e.printStackTrace();
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
	  * aktualisiert eine Antwort in der Datenbank
	  * @param a die vom Webservice uebergebene Antwort
	  */
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
	            System.out.println("SQLException beim Aktualisieren der Antwort");
	            e.printStackTrace();
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
}
