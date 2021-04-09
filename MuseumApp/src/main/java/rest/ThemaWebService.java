package rest;

import java.io.IOException;
import java.util.Collection;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.Thema;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import storage.ThemaDao;

/**
 * Die Klasse ThemaWebService nimmt Anfragen des Clients entgegen und laedt bzw. speichert Themengebiete
 * @author Roy Beyer
 * @version 1.0
 */
@Path("Thema")
public class ThemaWebService {
	/**
	 * dient zur Kommunikation mit der Datenbank
	 */
	private ThemaDao themaDao;
	
	/**
	 * laedt alle Themengebiete aus der Datenbank
	 * @return die agerufenen Themengebiete
	 */
	@GET 
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Thema> getAlleThemen() {
		initThemaDao();
        return themaDao.getAlleThemen();
    }
	
	/**
	 * ruft einen bestimmtes Themengebiet anhand der uebergebenen Id ab
	 * @param id die uebergebene Themen-Id
	 * @return das abgerufene Themengebiet
	 */
	@GET 
	@Path("/FindById")
    @Produces(MediaType.APPLICATION_JSON)
	public Thema getThemaById(@QueryParam("id") String id)
	{
		initThemaDao();
		return themaDao.getThemaById(id);
	}
	
	/**
	 * speichert neue Themengebiete in der Datenbank
	 * @param request der vom Client empfangene HTTP-Request
	 */
	@POST 
	@Path("/Add")
    @Produces(MediaType.APPLICATION_JSON)
	public void AddThema(@Context HttpServletRequest request)
	{
		ObjectMapper mapper = new ObjectMapper();
		Thema t;
		try {
			t = mapper.readValue(request.getInputStream(),Thema.class);
		} catch (IOException e) {
			 throw new WebApplicationException("Thema entspricht nicht dem korrekten Format.");
		}
		initThemaDao();
		themaDao.addThema(t);
	}

	/**
	 * intilialisert die Klasse ThemaDao fuer die Kommunikation mit der Datenbank
	 */
	private void initThemaDao() 
	{
		themaDao = new ThemaDao();
        
    }
}
