package rest;

import java.util.Collection;

import bean.Schwierigkeit;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import storage.SchwierigkeitDao;

/**
 * Die Klasse SchwierigkeitWebService nimmt Anfragen des Clients entgegen und laedt Schwierigkeitsgrade
 * @author Roy Beyer
 * @version 1.0
 */
@Path("Schwierigkeit")
public class SchwierigkeitWebService {
	/**
	 * dient zur Kommunikation mit der Datenbank
	 */
	private SchwierigkeitDao schwierigkeitDao;
	
	/**
	 * ruft alle Schwierigkeitsgrade aus der Datenbank ab
	 * @return die abgerufenen Schwierigkeitsgrade
	 */
	@GET 
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Schwierigkeit> getAllDifficulties() {
		initSchwierigkeitDao();
        return schwierigkeitDao.getAlleSchwierigkeiten();
    }
	
	/**
	 * ruft einen bestimmten Schwierigkeitsgrad anhand der uebergebenen Id ab
	 * @param id die uebergebene Schwierigkeiten-Id
	 * @return die abgerufene Schwierigkeit
	 */
	@GET 
	@Path("/FindById")
    @Produces(MediaType.APPLICATION_JSON)
	public Schwierigkeit getSchwierigkeitById(@QueryParam("id") String id)
	{
		initSchwierigkeitDao();
		return schwierigkeitDao.getSchwierigkeitById(id);
	}

	/**
	 * intilialisert die Klasse SchwierigkeitDao fuer die Kommunikation mit der Datenbank
	 */
	private void initSchwierigkeitDao() 
	{
		schwierigkeitDao = new SchwierigkeitDao();
        
    }
}
