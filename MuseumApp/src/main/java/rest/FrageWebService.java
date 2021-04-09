package rest;

import java.io.IOException;
import java.util.Collection;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.Frage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import storage.FrageDao;

/**
 * Die Klasse FrageWebService nimmt Anfragen des Clients entgegen und laedt bzw. speichert Quiz-Fragen 
 * @author Roy Beyer
 * @version 1.0
 */
@Path("Frage")
public class FrageWebService {
	
	/**
	 * dient zur Kommunikation mit der Datenbank
	 */
	private FrageDao frageDao;
	
	/**
	 * rufte alle vorhandenen Fragen aus der Datenbank ab und liefert sie an Client zurück
	 * @return die abgerufenen Fragen
	 */
	@GET 
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Frage> getAlleFragen() {
		initFrageDao();
        return frageDao.getAlleFragen();
    }
	
	/**
	 * ruft eine Frage aus der Datenbank ab, die der uebergebene Id entspricht
	 * @param id die uebergebene Fragen-id
	 * @return die abgerufene Frage
	 */
	@GET 
	@Path("/FindById")
    @Produces(MediaType.APPLICATION_JSON)
	public Frage getFrageById(@QueryParam("id") String id)
	{
		initFrageDao();
		return frageDao.getFrageById(id);
	}
	
	/**
	 * ruft Fragen entsprechend des uebergebenen Themengebiets und Schwierigkeitsgrads aus der Datenbank ab und gibt sie an Client zurueck
	 * @param thema das uebergebene Themengebiet
	 * @param schwierigkeit der uebergebene Schwierigkeitsgrad
	 * @return die abgerufenen Fragen
	 */
	@GET 
	@Path("/FindByThemaUndSchwierigkeit")
    @Produces(MediaType.APPLICATION_JSON)
	public Collection<Frage> getFragenByThemaUndSchwierigkeit(@QueryParam("thema") String thema,@QueryParam("schwierigkeit") String schwierigkeit)
	{
		initFrageDao();
		return frageDao.getFragenByTopicIdAndDifficultyId(thema,schwierigkeit);
	}
	
	/**
	 * fuegt eine neue Frage der Datenbank hinzu oder aktualisiert eine vorhandene Frage
	 * @param request der vom Client empfangene HTTP-Request
	 * @return die Id der bearbeiteten Frage
	 */
	@POST 
	@Path("/Add")
    @Produces(MediaType.APPLICATION_JSON)
	public int AddFrage(@Context HttpServletRequest request)
	{
		int id = 0;
		ObjectMapper mapper = new ObjectMapper();
		Frage q = new Frage();
		try {
			q = mapper.readValue(request.getInputStream(),Frage.class);
		} catch (IOException e) {
			 throw new WebApplicationException("Die Frage entspricht nicht dem richtigen Format.");
		}
		initFrageDao();
		if(q.getId()==-1)
			id = frageDao.addFrage(q);
		else
			id = frageDao.updateFrage(q);
		return id;
	}

	/**
	 * intilialisert die Klasse FrageDao fuer die Kommunikation mit der Datenbank
	 */
	private void initFrageDao() 
	{
		frageDao = new FrageDao();
        
    }
}
