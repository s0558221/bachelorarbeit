package rest;

import java.io.IOException;
import java.util.Collection;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.Antwort;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import storage.AntwortDao;
/**
 * Die Klasse AntwortWebService nimmt Anfragen des Clients entgegen und laedt bzw. speichert Quiz-Antworten 
 * @author Roy Beyer
 * @version 1.0
 */
@Path("Antwort")
public class AntwortWebService {
	/**
	 * dient zur Kommunikation mit der Datenbank
	 */
	private AntwortDao antwortDao;
	
	/**
	 * ruft alle Antworten aus der Datenbank ab und liefert sie an den Client zurueck
	 * @return alle abgerufenen Antworten
	 */
	@GET 
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Antwort> getAlleAntworten() {
		initAntwortDao();
        return antwortDao.getAlleAntworten();
    }
	
	/**
	 * ruft die Antworten zu einer bestimmten Frage aus der Datenbank ab.
	 * @param id die Fragen-Id, die der Client gesendet hat
	 * @return die abgerufenen Antworten
	 */
	@GET 
	@Path("/FindByFragenId")
    @Produces(MediaType.APPLICATION_JSON)
	public Collection<Antwort> getAntwortByFrageId(@QueryParam("frage") int id)
	{
		initAntwortDao();
		return antwortDao.getAntwortByFrageId(id);
	}

	/**
	 * fuegt eine neue Antwort der Datenbank hinzu oder aktualisiert eine Vorhandene
	 * @param request der vom Client empfangene HTTP-Request
	 */
	@POST 
	@Path("/Add")
	public void AddAntwort(@Context HttpServletRequest request)
	{
		ObjectMapper mapper = new ObjectMapper();
		Antwort a;
		try {
			a = mapper.readValue(request.getInputStream(),Antwort.class);
		} catch (IOException e) {
			System.out.println("test");
			 throw new WebApplicationException("Die Antwort entspricht nicht dem richtigen Format.");
		}
		initAntwortDao();
		if(a.getId()==-1)
			antwortDao.addAntwort(a);
		else
			antwortDao.updateAntwort(a);
	}
	
	/**
	 * intilialisert die Klasse AntwortDao fuer die Kommunikation mit der Datenbank
	 */
	private void initAntwortDao() 
	{
		antwortDao = new AntwortDao();
        
    }
}
