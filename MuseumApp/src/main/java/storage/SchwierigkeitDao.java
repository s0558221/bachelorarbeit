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

import bean.Schwierigkeit;

/**
 * Die Klasse SchwierigkeitDao kommuniziert mit der Datenbank-Tabelle Quiz_Lookup_Schwierigkeiten. Sie ist fuer das Laden und Speichern des jeweiligen Datensaetze in und aus der Datenbank zustaendig.
 * @author Roy Beyer
 * @version 1.0
 */
public class SchwierigkeitDao {
	
	/**
	 * der vorbereitete SQL-Befehl, der fuer den Abruf aller Datensaetze aus der Datenbank genutzt wird
	 */
	private static String GET_ALLE_SCHWIERIGKEITEN = "select * from Quiz_Lookup_Schwierigkeiten";
	
	/**
	 * der vorbereitete SQL-Befehl, der fuer den Abruf eines bestimmten Datensaetzes aus der Datenbank genutzt wird
	 */
	private static String GET_SCHWIERIGKEIT_BY_ID = "select * from Quiz_Lookup_Schwierigkeiten where id = ?";

	/**
	 * die Datenquelle fuer die Verbindung mit der Datenbankbank
	 */
	 private  DataSource ds;
	 
	 /**
	  * der Standard-Konstruktor stellt beim Instanziieren die Verbindung zur Datenbank her
	  */
	 public SchwierigkeitDao () {      
	        try {
	            Context ctx = new InitialContext();
	            ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MuseumAppDB");
	        } catch (NamingException e) {
	            System.out.println("NamingException getting datasource");
	            e.printStackTrace();
	        } 
	    }
	 
	 /**
	  * ruft einen bestimmten Datensatz anhand seiner Id aus der Datenbank ab und gibt ihn an den Webservice zurueck
	  * @param id die Id des Schwierigkeitsgrads
	  * @return der Schwierigkeitsgrad
	  */
	 public Schwierigkeit getSchwierigkeitById(String id) {
		 Schwierigkeit schwierigkeit = new Schwierigkeit();
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
	            stmt = con.prepareStatement(GET_SCHWIERIGKEIT_BY_ID);
	            System.out.println("Statement= "+stmt);
	            stmt.setInt(1, i);
	            rs = stmt.executeQuery();
	            
	            while (rs.next()) {
	            	schwierigkeit.setId(rs.getInt(1));
	            	schwierigkeit.setText(rs.getString(2));
	            }
	            
	            return schwierigkeit;
	        } catch (SQLException e) {
	            System.out.println("SQLException beim Schwierigkeit abfragen");
	            e.printStackTrace();
	            return schwierigkeit;
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
	  * ruft alle Datensaetze aus der Datenbanktabelle ab und gibt sie an den Webservice zurueck
	  * @return eine Sammlung aller Schwierigkeitsgrade
	  */
	 public Collection<Schwierigkeit> getAlleSchwierigkeiten() {
			Collection<Schwierigkeit> schwierigkeiten = new ArrayList<Schwierigkeit>();

	        Connection con = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;

	        try {
	            con = ds.getConnection();
	            stmt = con.prepareStatement(GET_ALLE_SCHWIERIGKEITEN);
	            rs = stmt.executeQuery();
	            while (rs.next()) {
	            	Schwierigkeit schwierigkeit = new Schwierigkeit();
	            	schwierigkeit.setId(rs.getInt(1));
	            	schwierigkeit.setText(rs.getString(2));
	            	schwierigkeiten.add(schwierigkeit);
	            }
	            
	            return schwierigkeiten;
	        } catch (SQLException e) {
	            System.out.println("SQLException beim Schwierigkeiten abfragen");
	            e.printStackTrace();
	            return schwierigkeiten;
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
