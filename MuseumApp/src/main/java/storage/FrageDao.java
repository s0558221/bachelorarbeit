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

/**
 * Die Klasse FrageDao kommuniziert mit der Datenbank-Tabelle Quiz_Fragen. Sie ist fuer das Laden und Speichern des jeweiligen Datensaetze in und aus der Datenbank zustaendig. 
 * @author Roy Beyer
 * @version 1.0
 */
public class FrageDao {
	
	/**
	 * 	 der vorbereitete SQL-Befehl, der fuer den Abruf aller Datensaetze aus der Datenbank genutzt wird
	 */
	private static String GET_ALLE_FRAGEN = "select * from Quiz_Fragen";
	
	/**
	 * 	der vorbereitete SQL-Befehl, der fuer den Abruf eines bestimmten Datensaetzes aus der Datenbank anhand einer Fragen-Id genutzt wird
	 */
	private static String GET_FRAGE_BY_ID = "select * from Quiz_Fragen where id = ?";
	
	/**
	 * 	der vorbereitete SQL-Befehl, der fuer den Abruf eines bestimmten Datensaetze aus der Datenbank anhand eines Themengebiets und eines Schwierigkeitsgrades genutzt wird
	 */
	private static String GET_FRAGE_BY_THEMA_UND_SCHWIERIGKEIT = "select * from Quiz_Fragen where Id_Themengebiet = ? AND Id_Schwierigkeit = ?";
	
	/**
	 * der vorbereitete SQL-Befehl, der zum Anlegen eines neues Datensatzes in der Datenbank genutzt wird
	 */
	private static String ADD_FRAGE = "INSERT INTO Quiz_Fragen (Frage,Id_Schwierigkeit,Id_Themengebiet)VALUES(?,?,?)";
	
	/**
	 * der vorbereitete SQL-Befehel, um einen vorhandenen Datensatz in der Datenbank zu aktualisieren
	 */
	private static String UPDATE_FRAGE = "UPDATE Quiz_Fragen SET Frage = ?,Id_Schwierigkeit = ?,Id_Themengebiet = ? WHERE Id = ?";
	
	/**
	 * die Datenquelle fuer die Verbindung mit der Datenbankbank
	 */
	private  DataSource ds;
	 
	/**
	  * der Standard-Konstruktor stellt beim Instanziieren die Verbindung zur Datenbank her
	  */
	 public FrageDao () {      
	        try {
	            Context ctx = new InitialContext();
	            ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MuseumAppDB");
	        } catch (NamingException e) {
	            System.out.println("NamingException getting datasource");
	            e.printStackTrace();
	        } 
	    }
	 
	 /**
	  * liefert eine Frage anhand der Id der Frage aus der Datenbank an den Webservice zurueck
	  * @param id die Fragen-Id
	  * @return die gefundene Frage
	  */
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
	            System.out.println("SQLException beim Abfragen der Frage");
	            e.printStackTrace();
	            return frage;
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
	  * liefert alle Fragen aus der Datenbank an den Webservice anhand eines bestimmten Themengebiets und eines bestimmten Schwierigkeitsgrads
	  * @param thema die vom Webservice übergebene Id des Themengebiets
	  * @param schwierigkeit die vom Webservice übergebene Id des Schwierigkeitsgrads
	  * @return die aus der Datenbank geladenen Fragen
	  */
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
	            System.out.println("SQLException beim Abfragen der Frage anhand von Thema und Schwierigkeit.");
	            e.printStackTrace();
	            return fragen;
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
	  * liefert alle Fragen aus der Datenbank an den Webservice
	  * @return alle abgerufenen Fragen aus der Datenbank
	  */
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
	            System.out.println("SQLException beim Fragen abrufen");
	            e.printStackTrace();
	            return fragen;
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
	  * schreibt eine neue Frage in die Datenbank
	  * @param q die vom Webservice übergebene Frage
	  * @return die Id der neu hinzugefügten Frage
	  */
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
	            System.out.println("SQLException beim hinzufügen von der Frage");
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
			return id;
		}
	 
	 /**
	  * aktualisert eine vorhandene Frage in der Datenbank
	  * @param q die vom Webservice übergebene Frage
	  * @return die Id der aktualisierten Frage
	  */
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
                System.out.print(id);
	        }catch (SQLException e) {
	            System.out.println("SQLException beim Updaten der Frage");
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
			return id;
		}
}
